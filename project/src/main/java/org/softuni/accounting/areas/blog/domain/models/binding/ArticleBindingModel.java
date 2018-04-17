package org.softuni.accounting.areas.blog.domain.models.binding;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

public class ArticleBindingModel {

    private static final int TITLE_MIN_LENGTH = 1;
    private static final int TITLE_MAX_LENGTH = 100;
    private static final int CONTENT_MIN_LENGTH = 20;
    private static final int CONTENT_MAX_LENGTH = 65535;

    private static final String TITLE_SIZE_MESSAGE = "Title must be between 1 and 100 symbols in length";
    private static final String TITLE_EMPTY_MESSAGE = "Please name your article";
    private static final String CONTENT_EMPTY_MESSAGE = "The content of the article cannot be empty";
    private static final String CONTENT_SIZE_MESSAGE = "Article''s content must be minimum 20 symbols and not exceeding 65,535 symbols in length";

    private String title;

    private String content;

    private MultipartFile image;

    private Date date;

    public ArticleBindingModel() {
    }

    @DateTimeFormat(pattern = "EEE, MMM d, ''yy 'at' h:mm a")
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MultipartFile getImage() {
        return this.image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    @NotEmpty(message = TITLE_EMPTY_MESSAGE)
    @Size(min = TITLE_MIN_LENGTH,max = TITLE_MAX_LENGTH,message = TITLE_SIZE_MESSAGE)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotEmpty(message = CONTENT_EMPTY_MESSAGE)
    @Size(min = CONTENT_MIN_LENGTH,max = CONTENT_MAX_LENGTH,message = CONTENT_SIZE_MESSAGE)
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
