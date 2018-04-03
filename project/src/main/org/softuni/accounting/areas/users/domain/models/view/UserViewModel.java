package org.softuni.accounting.areas.users.domain.models.view;

import org.softuni.accounting.areas.users.domain.entities.roles.Role;

import java.util.Set;

public class UserViewModel {

    private String id;

    private String email;

    private String username;

    private Set<Role> roles;


    public UserViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
