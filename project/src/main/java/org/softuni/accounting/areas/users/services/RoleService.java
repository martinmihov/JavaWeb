package org.softuni.accounting.areas.users.services;

import org.softuni.accounting.areas.users.domain.entities.roles.Role;
import org.softuni.accounting.areas.users.domain.models.service.RoleServiceModel;

import java.util.List;

public interface RoleService {

    void save(Role role);

    Role getByName(String name);

    Role getOneById(Long id);

    List<RoleServiceModel> findAllRoles();
}
