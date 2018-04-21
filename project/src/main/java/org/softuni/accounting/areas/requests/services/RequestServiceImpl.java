package org.softuni.accounting.areas.requests.services;

import org.softuni.accounting.areas.requests.domain.entities.Request;
import org.softuni.accounting.areas.requests.domain.models.binding.RequestBindingModel;
import org.softuni.accounting.areas.requests.domain.models.view.RequestViewModel;
import org.softuni.accounting.areas.requests.repositories.RequestRepository;
import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.softuni.accounting.areas.users.services.UserService;
import org.softuni.accounting.utils.parser.interfaces.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserService userService;
    private final ModelParser modelParser;


    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository,
                              UserService userService,
                              ModelParser modelParser) {

        this.requestRepository = requestRepository;
        this.userService = userService;
        this.modelParser = modelParser;
    }

    @Override
    public void saveRequest(@Valid RequestBindingModel saveRequestModel) {

        Request request = this.modelParser.convert(saveRequestModel, Request.class);
        User user = this.userService.findByEmail(saveRequestModel.getSenderEmail());

        if (user != null) {
            request.setSenderUser(user);
            user.addRequest(request);
            this.requestRepository.save(request);
        }
    }

    @Override
    public List<RequestViewModel> getAllRequests() {

        List<Request> requests = this.requestRepository.findAll();
        List<RequestViewModel> allRequests = new ArrayList<>();

        for (Request request : requests) {
            RequestViewModel model = this.modelParser.convert(request, RequestViewModel.class);
            allRequests.add(model);
        }
        return allRequests;
    }

    @Override
    public RequestViewModel findRequestById(Long id) {

        Optional<Request> reqCandidate = this.requestRepository.findById(id);

        return reqCandidate.map(request ->
                this.modelParser.convert(request, RequestViewModel.class)).orElse(null);

    }

    @Override
    public void deleteRequest(Long id) {
        this.requestRepository.deleteById(id);
    }

    @Override
    public Request findById(Long id) {

        Optional<Request> reqCandidate = this.requestRepository.findById(id);

        return reqCandidate.map(request ->
                this.modelParser.convert(request, Request.class)).orElse(null);

    }
}
