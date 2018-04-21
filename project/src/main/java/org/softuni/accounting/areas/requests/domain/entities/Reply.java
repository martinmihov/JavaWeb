package org.softuni.accounting.areas.requests.domain.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "replies")
public class Reply implements Serializable {

    private Long id;

    private Request request;

    private String replyMessage;

    private BigDecimal price;

    private Date replySentOn;

    public Reply(){
        this.setReplySentOn(new Date());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    public Request getRequest() {
        return this.request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Column(name = "reply_message",nullable = false,columnDefinition = "TEXT")
    public String getReplyMessage() {
        return this.replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "sent_on",nullable = false)
    public Date getReplySentOn() {
        return this.replySentOn;
    }

    public void setReplySentOn(Date replySentOn) {
        this.replySentOn = replySentOn;
    }
}
