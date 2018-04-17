package org.softuni.accounting.areas.users.repositories;

import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);

    User findByEmailAndDeletedOnIsNull(String email);

    List<User> findAll();

    List<User> findDistinctTop3ByImagePathNotNullAndOpinionNotNullAndArticlesNotNullOrderByArticlesAsc();


}
