package org.softuni.accounting.areas.blog.domain.models.view;

import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.softuni.accounting.utils.constants.GlobalConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ArticleViewModel {

    private Long id;

    private String title;

    private String content;

    private User author;

    private String imagePath;

    private Date date;

    private Long pageView;

    public ArticleViewModel() {
    }

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

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @DateTimeFormat(pattern = GlobalConstants.DATE_TIME_FORMAT_PATTERN)
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getPageView() {
        return this.pageView;
    }

    public void setPageView(Long pageView) {
        this.pageView = pageView;
    }

    public String getSummary() {
        return this.getContent().substring(0, this.getContent().length() / 2) + "...";
    }
}
