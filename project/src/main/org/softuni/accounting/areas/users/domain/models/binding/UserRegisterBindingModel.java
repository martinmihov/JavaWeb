package org.softuni.accounting.areas.users.domain.models.binding;

import org.softuni.accounting.validations.annotations.email.Email;

import javax.validation.constraints.NotEmpty;

public class UserRegisterBindingModel {

    @NotEmpty
    @Email
    private String email;
    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    @NotEmpty(message = "Confirm password cannot be empty")
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
