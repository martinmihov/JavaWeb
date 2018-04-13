package org.softuni.accounting.areas.requests.domain.models.binding;

import org.softuni.accounting.areas.requests.domain.entities.Request;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ReplyBindingModel {

    private String replyMessage;

    private BigDecimal price;

    private Date replySentOn;

    private Request request;

    public ReplyBindingModel() {
        this.replySentOn = new Date();
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

    @DateTimeFormat(pattern = "EEE, MMM d, ''yy 'at' h:mm a")
    public Date getReplySentOn() {
        return this.replySentOn;
    }

    public void setReplySentOn(Date replySentOn) {
        this.replySentOn = replySentOn;
    }

    public Request getRequest() {
        return this.request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
