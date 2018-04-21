package org.softuni.accounting.areas.users.domain.entities.users;

import org.hibernate.annotations.GenericGenerator;
import org.softuni.accounting.areas.blog.domain.entities.Article;
import org.softuni.accounting.areas.requests.domain.entities.Request;
import org.softuni.accounting.areas.users.domain.entities.roles.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private String id;

    private String email;

    private String username;

    private String password;

    private String imagePath;

    private String opinion;

    private Set<Role> roles;

    private Set<Request> requests;

    private Set<Article> articles;


    public User() {
        this.setRoles(new HashSet<>());
        this.setRequests(new HashSet<>());
        this.setArticles(new HashSet<>());
    }



    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "email", unique = true, nullable = false)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", length = 64, nullable = false)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "image_path")
    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Column(name = "opinion")
    public String getOpinion() {
        return this.opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles")
    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Transient // THAT IS NEW
    public void addRole(Role role) {
        this.roles.add(role);
    }

    @OneToMany(mappedBy = "senderUser")
    public Set<Request> getRequests() {
        return this.requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    @Transient
    public void addRequest(Request request) {
        this.requests.add(request);
    }

    @OneToMany(mappedBy = "author")
    public Set<Article> getArticles() {
        return this.articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}

//TODO CASCADE DELETION FROM THE DB