package org.softuni.accounting.areas.users.repositories;

import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}