package org.softuni.accounting.areas.blog.domain.models.view;

import org.softuni.accounting.utils.constants.GlobalConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ArticleHomeViewModel {

    private Long id;

    private String title;

    private String content;

    private Date date;

    private String imagePath;

    public ArticleHomeViewModel() { }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @DateTimeFormat(pattern = GlobalConstants.DATE_TIME_FORMAT_PATTERN)
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getSummary() {
        return this.getContent().substring(0, this.getContent().length() / 5) + "...";
    }
}
