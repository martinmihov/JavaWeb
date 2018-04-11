package org.softuni.accounting.areas.requests.services;

import org.softuni.accounting.areas.requests.domain.entities.Reply;
import org.softuni.accounting.areas.requests.domain.entities.Request;
import org.softuni.accounting.areas.requests.domain.models.binding.ReplyBindingModel;
import org.softuni.accounting.areas.requests.domain.models.view.ReplyViewModel;
import org.softuni.accounting.areas.requests.domain.models.view.RequestViewModel;
import org.softuni.accounting.areas.requests.repositories.ReplyRepository;
import org.softuni.accounting.utils.parser.interfaces.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private final ModelParser modelParser;
    private final RequestService requestService;
    private final ReplyRepository replyRepository;

    @Autowired
    public ReplyServiceImpl(ModelParser modelParser, RequestService requestService, ReplyRepository replyRepository) {
        this.modelParser = modelParser;
        this.requestService = requestService;
        this.replyRepository = replyRepository;
    }

    @Override
    public void saveReply(@Valid ReplyBindingModel model) {
        Reply reply = this.modelParser.convert(model, Reply.class);
        Request request = this.requestService.findById(model.getRequest().getId());
        if (request != null) {
            reply.setRequest(request);
            request.addReply(reply);
            request.setIsReplied(true);
            this.replyRepository.save(reply);
        }
    }

    @Override
    public LinkedList<ReplyViewModel> getRepliesByRequest(RequestViewModel model) {
        Request req = this.modelParser.convert(model, Request.class);
        LinkedList<Reply> repliesByRequest = this.replyRepository.getRepliesByRequest(req);
        LinkedList<ReplyViewModel> replies = new LinkedList<>();
        for (Reply reply : repliesByRequest) {
            ReplyViewModel replyViewModel = this.modelParser.convert(reply, ReplyViewModel.class);
            replies.add(replyViewModel);
        }
        return replies;
    }

    @Override
    public Map<RequestViewModel, LinkedList<ReplyViewModel>> getAllRequestsReplies(Set<RequestViewModel> requests) {
        Map<RequestViewModel, LinkedList<ReplyViewModel>> conversation = new HashMap<>();
        for (RequestViewModel req : requests) {
            LinkedList<ReplyViewModel> replies = this.getRepliesByRequest(req);
            conversation.put(req, replies);
        }
        return conversation;
    }
}
