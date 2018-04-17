package org.softuni.accounting.areas.requests.domain.models.binding;

import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.softuni.accounting.utils.constants.GlobalConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

public class RequestSendBindingModel {

    private static final int SUBJECT_MIN_LENGTH = 1;
    private static final int SUBJECT_MAX_LENGTH = 50;
    private static final int MESSAGE_MIN_LENGTH = 10;
    private static final int MESSAGE_MAX_LENGTH = 65535;

    private static final String SUBJECT_SIZE_MESSAGE = "* Subject must be between 1 and 50 symbols in length";
    private static final String SUBJECT_EMPTY_MESSAGE = "* Subject cannot be empty";
    private static final String MESSAGE_EMPTY_MESSAGE = "* Message cannot be empty";
    private static final String MESSAGE_SIZE_MESSAGE = "* Message''s content must be minimum 10 symbols and not exceeding 65,535 symbols in length";
    private static final String EMAIL_EMPTY_MESSAGE = "* Email cannot be empty";

    private String senderSubject;
    private String senderMessage;
    private String senderEmail;
    private User senderUser;
    private Date requestSentOn;

    public RequestSendBindingModel() {
        this.requestSentOn = new Date();
    }

    @NotEmpty(message = SUBJECT_EMPTY_MESSAGE)
    @Size(min = SUBJECT_MIN_LENGTH, max = SUBJECT_MAX_LENGTH, message = SUBJECT_SIZE_MESSAGE)
    public String getSenderSubject() {
        return this.senderSubject;
    }

    public void setSenderSubject(String senderSubject) {
        this.senderSubject = senderSubject;
    }

    @NotEmpty(message = MESSAGE_EMPTY_MESSAGE)
    @Size(min = MESSAGE_MIN_LENGTH, max = MESSAGE_MAX_LENGTH, message = MESSAGE_SIZE_MESSAGE)
    public String getSenderMessage() {
        return this.senderMessage;
    }

    public void setSenderMessage(String senderMessage) {
        this.senderMessage = senderMessage;
    }

    @NotEmpty(message = EMAIL_EMPTY_MESSAGE)
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

    @DateTimeFormat(pattern = GlobalConstants.DATE_TIME_FORMAT_PATTERN)
    public Date getRequestSentOn() {
        return this.requestSentOn;
    }

    public void setRequestSentOn(Date requestSentOn) {
        this.requestSentOn = requestSentOn;
    }
}
