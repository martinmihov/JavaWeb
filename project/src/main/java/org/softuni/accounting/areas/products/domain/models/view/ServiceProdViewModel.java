package org.softuni.accounting.areas.products.domain.models.view;

import org.softuni.accounting.areas.products.domain.entities.enums.ServiceType;

public class ServiceProdViewModel {

    private Long id;

    private String description;

    private ServiceType serviceType;

    public ServiceProdViewModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ServiceType getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
}
