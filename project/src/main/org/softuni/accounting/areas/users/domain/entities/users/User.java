package org.softuni.accounting.areas.users.domain.entities.users;

import org.hibernate.annotations.GenericGenerator;
import org.softuni.accounting.areas.users.domain.entities.roles.Role;
import org.softuni.accounting.validations.annotations.email.Email;

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

    private Set<Role> roles;


    public User(String email, String fullName, String password) {
        this.email = email;
        this.username = fullName;
        this.password = password;

        this.roles = new HashSet<>();
    }

    public User() {
        this.roles = new HashSet<>();
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

    @Email
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

    @Column(name = "password", length = 60, nullable = false)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles")
    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}