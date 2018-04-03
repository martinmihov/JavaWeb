package org.softuni.accounting.areas.users.services;

import org.softuni.accounting.areas.users.domain.models.service.RoleServiceModel;

import java.util.List;

public interface RoleService {
    List<RoleServiceModel> findAllRoles();
}
