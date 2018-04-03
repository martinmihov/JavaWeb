package org.softuni.accounting.areas.products.services;

import org.softuni.accounting.areas.products.domain.entities.ServiceProd;
import org.softuni.accounting.areas.products.domain.entities.enums.ServiceType;
import org.softuni.accounting.areas.products.domain.models.binding.ServiceProdAddBindingModel;
import org.softuni.accounting.areas.products.domain.models.view.ServiceProdViewModel;
import org.softuni.accounting.areas.products.repositories.ServiceProdRepository;
import org.softuni.accounting.utils.parser.interfaces.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServiceProdServiceImpl implements ServiceProdService {

    private final ServiceProdRepository serviceProdRepository;
    private final ModelParser modelParser;

    @Autowired
    public ServiceProdServiceImpl(ServiceProdRepository serviceProdRepository, ModelParser modelParser) {
        this.serviceProdRepository = serviceProdRepository;
        this.modelParser = modelParser;
    }

    @Override
    public List<ServiceProdViewModel> getAllServices() {
        List<ServiceProd> as = this.serviceProdRepository.findAll();
        List<ServiceProdViewModel> allServices = new ArrayList<>();

        for (ServiceProd s : as) {
            ServiceProdViewModel model = this.modelParser.convert(s, ServiceProdViewModel.class);
            allServices.add(model);
        }
        return allServices;
    }

    @Override
    public List<ServiceProdViewModel> getAllServicesByType(ServiceType type) {
        List<ServiceProd> sps = this.serviceProdRepository.findAllByServiceTypeEquals(type);
        List<ServiceProdViewModel> servicesByType = new ArrayList<>();
        for (ServiceProd sp : sps) {
            ServiceProdViewModel model = this.modelParser.convert(sp, ServiceProdViewModel.class);
            servicesByType.add(model);
        }
        return servicesByType;
    }

    @Override
    public void addService(@Valid ServiceProdAddBindingModel addServiceModel) {
        this.serviceProdRepository
                .save(this.modelParser.convert(addServiceModel, ServiceProd.class));
    }
}
