package org.softuni.accounting.areas.products.services;

import org.softuni.accounting.areas.products.domain.entities.enums.ServiceType;
import org.softuni.accounting.areas.products.domain.models.binding.ServiceProdAddBindingModel;
import org.softuni.accounting.areas.products.domain.models.view.ServiceProdViewModel;

import javax.validation.Valid;
import java.util.List;

public interface ServiceProdService {

    void deleteService(Long id);

    void addService(@Valid ServiceProdAddBindingModel addServiceModel);

    ServiceProdViewModel getServiceToDelete(Long id);

    List<ServiceProdViewModel> getAllServices();

    List<ServiceProdViewModel> getAllServicesByType(ServiceType type);

    List<ServiceProdViewModel> searchServiceProd(String searchInDescription);
}
