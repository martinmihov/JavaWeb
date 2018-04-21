package org.softuni.accounting.areas.products.domain.models.binding;

import org.softuni.accounting.areas.products.domain.entities.enums.ServiceType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ServiceProdAddBindingModel {

    private static final int DESCRIPTION_MIN_LENGTH = 3;
    private static final int DESCRIPTION_MAX_LENGTH = 254;

    private static final String DESCRIPTION_SIZE_MESSAGE = "Description must be between 3 and 254 symbols in length";
    private static final String DESCRIPTION_EMPTY_MESSAGE = "Description cannot be empty";
    private static final String SERVICE_TYPE_UNSPECIFIED_MESSAGE = "ServiceProd Type cannot be unspecified";

    private String description;
    private ServiceType serviceType;

    public ServiceProdAddBindingModel() {
    }

    @NotEmpty(message = DESCRIPTION_EMPTY_MESSAGE)
    @Size(min = DESCRIPTION_MIN_LENGTH, max = DESCRIPTION_MAX_LENGTH, message = DESCRIPTION_SIZE_MESSAGE)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = SERVICE_TYPE_UNSPECIFIED_MESSAGE)
    public ServiceType getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

}
