package org.softuni.accounting.areas.requests.services;

import org.softuni.accounting.areas.requests.domain.models.binding.RequestSendBindingModel;

import javax.validation.Valid;

public interface RequestService {

    void saveMessage(@Valid RequestSendBindingModel saveRequestModel);
}
