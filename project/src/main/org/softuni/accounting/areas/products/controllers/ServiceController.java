package org.softuni.accounting.areas.products.controllers;

import org.softuni.accounting.areas.products.domain.entities.enums.ServiceType;
import org.softuni.accounting.areas.products.domain.models.view.ServiceProdViewModel;
import org.softuni.accounting.areas.products.services.ServiceProdService;
import org.softuni.accounting.controllers.BaseController;
import org.softuni.accounting.utils.constants.ServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/services")
public class ServiceController extends BaseController {

    private final ServiceProdService serviceProdService;

    @Autowired
    public ServiceController(ServiceProdService serviceProdService) {
        this.serviceProdService = serviceProdService;
    }

    @GetMapping("/all")
    public ModelAndView allServices(){
        return this.view("services/services.html");
    }

    @GetMapping("/vat-services")
    public ModelAndView vatServices(){
        List<ServiceProdViewModel> services = this.serviceProdService.getAllServicesByType(ServiceType.VAT);
        Object[] objects = new Object[]{services,ServiceConstants.VAT_SERVICE};
        return this.view("services/service-type-list.html",objects,"services","serviceType");
    }

    @GetMapping("/accounting-services")
    public ModelAndView accountingServices(){
        List<ServiceProdViewModel> services = this.serviceProdService.getAllServicesByType(ServiceType.ACCOUNTING);
        Object[] objects = new Object[]{services,ServiceConstants.ACCOUNTING_SERVICE};
        return this.view("services/service-type-list.html",objects,"services","serviceType");
    }

    @GetMapping("/personnel-services")
    public ModelAndView personnelServices(){
        List<ServiceProdViewModel> services = this.serviceProdService.getAllServicesByType(ServiceType.CONSULTING);
        Object[] objects = new Object[]{services,ServiceConstants.CONSULT_SERVICE};
        return this.view("services/service-type-list.html",objects,"services","serviceType");
    }

}
