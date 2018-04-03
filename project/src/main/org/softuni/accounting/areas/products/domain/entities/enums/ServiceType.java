package org.softuni.accounting.areas.products.domain.entities.enums;

public enum ServiceType {

    CONSULTING,
    VAT,
    ACCOUNTING;


    public static ServiceType parseFromStrimg(String serviceType) {
        return ServiceType.valueOf(serviceType.toUpperCase());
    }
}
