package org.softuni.accounting.areas.requests.domain.models.binding;

import org.softuni.accounting.areas.requests.domain.entities.Request;
import org.softuni.accounting.utils.constants.GlobalConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

public class ReplyBindingModel {

    private static final int REPLY_MIN_LENGTH = 1;
    private static final int REPLY_MAX_LENGTH = 254;
    private static final long PRICE_MIN_VALUE = 0L;

    private static final String REPLY_TEXT_SIZE_MESSAGE = "* Reply must be between 3 and 254 symbols in length";
    private static final String REPLY_TEXT_EMPTY_MESSAGE = "* Reply cannot be empty";
    private static final String PRICE_POSITIVE_VALUE = "* The value must be positive";

    private String replyMessage;
    private BigDecimal price;
    private Date replySentOn;
    private Request request;

    public ReplyBindingModel() {
        this.replySentOn = new Date();
    }


    @NotEmpty(message = REPLY_TEXT_EMPTY_MESSAGE)
    @Size(min = REPLY_MIN_LENGTH,max = REPLY_MAX_LENGTH, message = REPLY_TEXT_SIZE_MESSAGE)
    public String getReplyMessage() {
        return this.replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }

    @Min(value = PRICE_MIN_VALUE, message = PRICE_POSITIVE_VALUE)
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @DateTimeFormat(pattern = GlobalConstants.DATE_TIME_FORMAT_PATTERN)
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
