package org.softuni.accounting.areas.requests.domain.models.view;

import java.math.BigDecimal;

public class RequestAndReplyViewModel {

    private Long id;

    private String senderSubject;

    private String senderMessage;

    private String senderEmail;

    private String replyMessage;

    private BigDecimal price;

    public RequestAndReplyViewModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderSubject() {
        return this.senderSubject;
    }

    public void setSenderSubject(String senderSubject) {
        this.senderSubject = senderSubject;
    }

    public String getSenderMessage() {
        return this.senderMessage;
    }

    public void setSenderMessage(String senderMessage) {
        this.senderMessage = senderMessage;
    }

    public String getSenderEmail() {
        return this.senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReplyMessage() {
        return this.replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
