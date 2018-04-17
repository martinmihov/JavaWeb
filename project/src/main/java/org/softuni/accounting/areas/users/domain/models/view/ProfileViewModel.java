package org.softuni.accounting.areas.users.domain.models.view;

import org.softuni.accounting.areas.blog.domain.models.view.ArticleViewModel;
import org.softuni.accounting.areas.requests.domain.models.view.RequestViewModel;

import java.util.Set;

public class ProfileViewModel {

    private String id;

    private String username;

    private String email;

    private String imagePath;

    private String opinion;

    private Set<RequestViewModel> requests;

    private Set<ArticleViewModel> articleViewModels;

    public ProfileViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getOpinion() {
        return this.opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Set<RequestViewModel> getRequests() {
        return this.requests;
    }

    public void setRequests(Set<RequestViewModel> requests) {
        this.requests = requests;
    }

    public Set<ArticleViewModel> getArticleViewModels() {
        return this.articleViewModels;
    }

    public void setArticleViewModels(Set<ArticleViewModel> articleViewModels) {
        this.articleViewModels = articleViewModels;
    }
}
