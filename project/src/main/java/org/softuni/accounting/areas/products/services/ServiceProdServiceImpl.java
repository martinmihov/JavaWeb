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
import java.util.Optional;

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
        this.serviceProdRepository.save(this.modelParser.convert(addServiceModel, ServiceProd.class));
    }

    @Override
    public ServiceProdViewModel getServiceToDelete(Long id) {
        Optional<ServiceProd> serv = this.serviceProdRepository.findById(id);
        if(serv.isPresent()){
            return serv.map(serviceProd -> this.modelParser.convert(serviceProd, ServiceProdViewModel.class)).orElse(null);
        }
        return null;
    }

    @Override
    public void deleteService(Long id) {
        this.serviceProdRepository.deleteById(id);
    }

    @Override
    public List<ServiceProdViewModel> searchServiceProd(String searchInDescription) {
        List<ServiceProd> serviceProdEntities = this.serviceProdRepository.findServiceProdByDescriptionContainingOrderByServiceTypeAsc(searchInDescription);
        List<ServiceProdViewModel> result = new ArrayList<>();
        for (ServiceProd serviceProdEntity : serviceProdEntities) {
            ServiceProdViewModel model = this.modelParser.convert(serviceProdEntity,ServiceProdViewModel.class);
            result.add(model);
        }
        return result;
    }
}
