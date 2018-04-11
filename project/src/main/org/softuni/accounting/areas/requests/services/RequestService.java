package org.softuni.accounting.areas.requests.services;

import org.softuni.accounting.areas.requests.domain.entities.Request;
import org.softuni.accounting.areas.requests.domain.models.binding.RequestSendBindingModel;
import org.softuni.accounting.areas.requests.domain.models.view.RequestViewModel;

import javax.validation.Valid;
import java.util.List;

public interface RequestService {

    void saveRequest(@Valid RequestSendBindingModel saveRequestModel);

    List<RequestViewModel> getAllRequests();

    RequestViewModel findRequestById(Long id);

    void deleteRequest(Long id);

    Request findById(Long id);
}
