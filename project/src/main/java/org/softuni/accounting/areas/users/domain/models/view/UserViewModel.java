package org.softuni.accounting.areas.users.domain.models.view;

import org.softuni.accounting.areas.users.domain.entities.roles.Role;
import org.softuni.accounting.utils.constants.GlobalConstants;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

public class UserViewModel {

    private String id;

    private String email;

    private String username;

    private Set<Role> roles;

    private Date deletedOn;


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

    @DateTimeFormat(pattern = GlobalConstants.DATE_TIME_FORMAT_PATTERN)
    public Date getDeletedOn() {
        return this.deletedOn;
    }

    public void setDeletedOn(Date deletedOn) {
        this.deletedOn = deletedOn;
    }
}
