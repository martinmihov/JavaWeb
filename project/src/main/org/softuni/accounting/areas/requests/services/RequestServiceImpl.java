package org.softuni.accounting.areas.requests.services;

import org.softuni.accounting.areas.requests.domain.entities.Request;
import org.softuni.accounting.areas.requests.domain.models.binding.RequestSendBindingModel;
import org.softuni.accounting.areas.requests.repositories.RequestRepository;
import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.softuni.accounting.areas.users.repositories.UserRepository;
import org.softuni.accounting.utils.parser.interfaces.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
@Transactional
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final ModelParser modelParser;


    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository, UserRepository userRepository, ModelParser modelParser) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void saveMessage(@Valid RequestSendBindingModel saveRequestModel) {
        Request request = this.modelParser.convert(saveRequestModel, Request.class);
        User user = this.userRepository.findByEmail(saveRequestModel.getSenderEmail());
        if (user != null) {
            request.setSenderUser(user);
            user.addRequest(request);
            this.requestRepository.save(request);
        }
    }
}
