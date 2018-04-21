package org.softuni.accounting.areas.requests.domain.models.view;

import org.softuni.accounting.utils.constants.GlobalConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class ReplyViewModel {

    private Long id;

    private String replyMessage;

    private BigDecimal price;

    private Date replySentOn;

    public ReplyViewModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @DateTimeFormat(pattern = GlobalConstants.DATE_TIME_FORMAT_PATTERN)
    public Date getReplySentOn() {
        return this.replySentOn;
    }

    public void setReplySentOn(Date replySentOn) {
        this.replySentOn = replySentOn;
    }
}
