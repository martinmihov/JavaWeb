package org.softuni.accounting.areas.users.services;

import org.softuni.accounting.areas.users.domain.entities.roles.Role;
import org.softuni.accounting.areas.users.domain.models.service.RoleServiceModel;

import java.util.List;

public interface RoleService {
    List<RoleServiceModel> findAllRoles();

    void save(Role role);
}
