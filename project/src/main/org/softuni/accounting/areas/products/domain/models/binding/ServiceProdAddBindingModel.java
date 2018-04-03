package org.softuni.accounting.areas.products.domain.models.binding;

import org.softuni.accounting.areas.products.domain.entities.enums.ServiceType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ServiceProdAddBindingModel {

    private BigDecimal price;
    private String description;
    private ServiceType serviceType;

    public ServiceProdAddBindingModel() {
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotEmpty(message = "Description cannot be empty")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "ServiceProd Type cannot be unspecified")
    public ServiceType getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

}
