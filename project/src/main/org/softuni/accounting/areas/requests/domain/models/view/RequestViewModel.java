package org.softuni.accounting.areas.requests.domain.models.view;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;

public class RequestViewModel {

    private Long id;

    private String senderSubject;

    private String senderMessage;

    private String senderEmail;

    private BigDecimal requestPrice;

    private Date requestSentOn;

    private boolean isReplied;

    private LinkedList<ReplyViewModel> replies;

    public RequestViewModel() {
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

    public BigDecimal getRequestPrice() {
        return this.requestPrice;
    }

    public void setRequestPrice(BigDecimal requestPrice) {
        this.requestPrice = requestPrice;
    }

    @DateTimeFormat(pattern = "EEE, MMM d, ''yy 'at' h:mm a")
    public Date getRequestSentOn() {
        return this.requestSentOn;
    }

    public void setRequestSentOn(Date requestSentOn) {
        this.requestSentOn = requestSentOn;
    }

    public boolean getIsReplied() {
        return this.isReplied;
    }

    public void setIsReplied(boolean isReplied) {
        this.isReplied = isReplied;
    }

    public LinkedList<ReplyViewModel> getReplies() {
        return this.replies;
    }

    public void setReplies(LinkedList<ReplyViewModel> replies) {
        this.replies = replies;
    }
}
