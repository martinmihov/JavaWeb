package org.softuni.accounting.areas.requests.domain.entities;

import org.softuni.accounting.areas.users.domain.entities.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
public class Request {

    private Long id;

    private String senderSubject;

    private String senderMessage;

    private String senderEmail;

    private User senderUser;

    private LocalDateTime requestSentOn;

    public Request() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "subject", nullable = false)
    public String getSenderSubject() {
        return this.senderSubject;
    }

    public void setSenderSubject(String senderSubject) {
        this.senderSubject = senderSubject;
    }

    @Column(name = "request", nullable = false, columnDefinition = "TEXT")
    public String getSenderMessage() {
        return this.senderMessage;
    }

    public void setSenderMessage(String senderMessage) {
        this.senderMessage = senderMessage;
    }

    @Column(name = "email", nullable = false)
    public String getSenderEmail() {
        return this.senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_requests")
    public User getSenderUser() {
        return this.senderUser;
    }

    public void setSenderUser(User senderUser) {
        this.senderUser = senderUser;
    }

    @Column(name = "date_time",nullable = false)
    public LocalDateTime getRequestSentOn() {
        return this.requestSentOn;
    }

    public void setRequestSentOn(LocalDateTime requestSentOn) {
        this.requestSentOn = LocalDateTime.now();
    }

    //    public LocalDateTime getRequestSentOn() {
//        return this.requestSentOn;
//    }
//
//    public void setRequestSentOn() {
//        Date date = new Date();
//        this.requestSentOn = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//    }
}
