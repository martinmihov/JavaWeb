package org.softuni.accounting.areas.users.services;

import org.softuni.accounting.areas.users.domain.entities.roles.Role;
import org.softuni.accounting.areas.users.domain.models.service.RoleServiceModel;
import org.softuni.accounting.areas.users.repositories.RoleRepository;
import org.softuni.accounting.utils.parser.interfaces.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelParser modelParser;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository,
                           ModelParser modelParser) {
        this.roleRepository = roleRepository;
        this.modelParser = modelParser;
    }

    @Override
    public List<RoleServiceModel> findAllRoles() {

        List<RoleServiceModel> roleServiceModels = new ArrayList<>();
        List<Role> all = this.roleRepository.findAll();

        for (Role role : all) {
            roleServiceModels.add(this.modelParser.convert(role, RoleServiceModel.class));
        }
        return roleServiceModels;
    }

    @Override
    public void save(Role role) {
        this.roleRepository.save(role);
    }

    public Role getByName(String name){
        return this.roleRepository.findByName(name);
    }

    public Role getOneById(Long id){
       return this.roleRepository.getOne(id);
    }
}
