package org.softuni.accounting;

import org.softuni.accounting.areas.products.domain.entities.ServiceProd;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountingApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountingApplication.class, args);
        ServiceProd product = new ServiceProd();


    }
}
