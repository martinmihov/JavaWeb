package org.softuni.accounting.areas.requests.services;

import org.softuni.accounting.areas.requests.domain.entities.Request;
import org.softuni.accounting.areas.requests.domain.models.binding.RequestBindingModel;
import org.softuni.accounting.areas.requests.domain.models.view.RequestViewModel;

import javax.validation.Valid;
import java.util.List;

public interface RequestService {

    void deleteRequest(Long id);

    void saveRequest(@Valid RequestBindingModel saveRequestModel);

    Request findById(Long id);

    RequestViewModel findRequestById(Long id);

    List<RequestViewModel> getAllRequests();

}
