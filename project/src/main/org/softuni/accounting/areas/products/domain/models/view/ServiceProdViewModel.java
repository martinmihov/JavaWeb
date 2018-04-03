package org.softuni.accounting.areas.products.domain.models.view;

import org.softuni.accounting.areas.products.domain.entities.enums.ServiceType;

import java.math.BigDecimal;

public class ServiceProdViewModel {

    private Long id;
    private BigDecimal price;
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

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
