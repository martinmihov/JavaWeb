package org.softuni.accounting.areas.users.repositories;

import org.softuni.accounting.areas.users.domain.entities.roles.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
