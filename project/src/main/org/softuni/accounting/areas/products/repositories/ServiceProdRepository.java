package org.softuni.accounting.areas.products.repositories;

import org.softuni.accounting.areas.products.domain.entities.ServiceProd;
import org.softuni.accounting.areas.products.domain.entities.enums.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProdRepository extends JpaRepository<ServiceProd,Long> {

    List<ServiceProd> findAllByServiceTypeEquals(ServiceType serviceType);

}
