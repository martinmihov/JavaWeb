package org.softuni.accounting.areas.requests.repositories;

import org.softuni.accounting.areas.requests.domain.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {

//    List<Request> findRequestsBySenderEmailOrderByIsRepliedDesc(String authorEmail);
}
