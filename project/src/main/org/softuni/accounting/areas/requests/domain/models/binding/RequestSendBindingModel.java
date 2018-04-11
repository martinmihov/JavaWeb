package org.softuni.accounting.areas.requests.domain.models.binding;

import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

public class RequestSendBindingModel {

    private String senderSubject;

    private String senderMessage;

    private String senderEmail;

    private User senderUser;

    private Date requestSentOn;

    public RequestSendBindingModel() {
        this.requestSentOn = new Date();
    }

    @NotEmpty(message = "Subject cannot be empty")
    public String getSenderSubject() {
        return this.senderSubject;
    }

    public void setSenderSubject(String senderSubject) {
        this.senderSubject = senderSubject;
    }

    @NotEmpty(message = "Message cannot be empty")
    @Size(min = 10,max = 2000,message = "Message has to be between 10 and 2000 symbols long")
    public String getSenderMessage() {
        return this.senderMessage;
    }

    public void setSenderMessage(String senderMessage) {
        this.senderMessage = senderMessage;
    }

    @NotEmpty(message = "Email cannot be empty")
    @Email
    public String getSenderEmail() {
        return this.senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public User getSenderUser() {
        return this.senderUser;
    }

    public void setSenderUser(User senderUser) {
        this.senderUser = senderUser;
    }

    public Date getRequestSentOn() {
        return this.requestSentOn;
    }

    public void setRequestSentOn(Date requestSentOn) {
        this.requestSentOn = requestSentOn;
    }
}
