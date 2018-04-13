package org.softuni.accounting.areas.requests.repositories;

import org.softuni.accounting.areas.requests.domain.entities.Reply;
import org.softuni.accounting.areas.requests.domain.entities.Request;
import org.softuni.accounting.areas.requests.domain.models.view.ReplyViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    LinkedList<Reply> getRepliesByRequest(Request request);



}
