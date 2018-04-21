package org.softuni.accounting.areas.products.domain.entities;

import org.softuni.accounting.areas.products.domain.entities.enums.ServiceType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "services")
public class ServiceProd implements Serializable {

    private Long id;
    private String description;
    private ServiceType serviceType;

    public ServiceProd() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "description", columnDefinition = "TEXT",nullable = false)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "service_type",nullable = false)
    public ServiceType getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
}
