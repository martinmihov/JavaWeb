package org.softuni.accounting.areas.users.domain.models.binding;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class UserEditBindingModel {

    private String username;

    private String password;

    private String confirmPassword;

    private String email;

    private MultipartFile image;

    private Set<Long> rolesIds;

    public UserEditBindingModel() {
    }

//    @Email
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    @NotEmpty(message = "Username cannot be empty")
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public MultipartFile getImage() {
        return this.image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Set<Long> getRolesIds() {
        return this.rolesIds;
    }

    public void setRolesIds(Set<Long> rolesIds) {
        this.rolesIds = rolesIds;
    }
}
