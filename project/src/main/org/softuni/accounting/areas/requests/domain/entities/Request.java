package org.softuni.accounting.areas.requests.domain.entities;

import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "requests")
public class Request implements Serializable {

    private Long id;

    private String senderSubject;

    private String senderMessage;

    private String senderEmail;

    private User senderUser;

    private BigDecimal price;

    private Date requestSentOn;

    private boolean isReplied;

    private List<Reply> replies;

    public Request() {
        this.setRequestSentOn(new Date());
        this.setReplies(new LinkedList<>());
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

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL) // TODO Cascade.all or not
    @JoinColumn(name = "user_requests")
    public User getSenderUser() {
        return this.senderUser;
    }

    public void setSenderUser(User senderUser) {
        this.senderUser = senderUser;
    }

    @Column(name = "price")
    public BigDecimal getRequestPrice() {
        return this.price;
    }

    public void setRequestPrice(BigDecimal price) {
        this.price = price;
    }


    @Column(name = "sent_on",nullable = false)
    @DateTimeFormat(pattern = "EEE, MMM d, ''yy 'at' h:mm a")
    public Date getRequestSentOn() {
        return this.requestSentOn;
    }

    public void setRequestSentOn(Date requestSentOn) {
        this.requestSentOn = requestSentOn;
    }


    @Column(name = "is_replied")
    public boolean getIsReplied() {
        return this.isReplied;
    }

    public void setIsReplied(boolean isReplied) {
        this.isReplied = isReplied;
    }

    @OneToMany(mappedBy = "request")
    public List<Reply> getReplies() {
        return this.replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }


    public void addReply(Reply reply){
        this.replies.add(reply);
    }
}

//@OneToMany(cascade = CascadeType.ALL,
//        fetch = FetchType.LAZY,mappedBy = "request")
