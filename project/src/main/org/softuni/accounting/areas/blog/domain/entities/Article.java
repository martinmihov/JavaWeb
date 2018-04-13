package org.softuni.accounting.areas.blog.domain.entities;

import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "articles")
public class Article implements Serializable {

    private Long id;

    private String title;

    private String content;

    private User author;

    private String imagePath;

    private Date date;

    private Long pageView;

    public Article(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;

        this.date = new Date();
        this.pageView = 0L;
    }

    public Article(String title, String content, Date date) {
        this.title = title;
        this.content = content;

        this.date = new Date();
    }

    public Article() {

    }

    public Article(String title, String content, User author, String imagePath, Date date) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.imagePath = imagePath;

        this.date = new Date();
        this.pageView = 0L;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(nullable = false)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne
    @JoinColumn(nullable = false, name = "authorId")
    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Column(name = "date")
    @DateTimeFormat(pattern = "EEE, MMM d, ''yy 'at' h:mm a")
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "image_path")
    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Column(name = "page_view")
    public Long getPageView() {
        return this.pageView;
    }

    public void setPageView(Long pageView) {
        this.pageView = pageView;
    }
}

