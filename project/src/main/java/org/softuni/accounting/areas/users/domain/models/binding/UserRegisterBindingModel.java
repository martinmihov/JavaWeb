package org.softuni.accounting.areas.users.domain.models.binding;

import org.softuni.accounting.utils.constants.UserConstants;
import org.softuni.accounting.validations.annotations.email.Email;
import org.softuni.accounting.validations.annotations.email.EmailUnique;
import org.softuni.accounting.validations.annotations.password.PasswordMatch;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@PasswordMatch
public class UserRegisterBindingModel {

    private String email;

    private String username;

    private String password;

    private String confirmPassword;

    public UserRegisterBindingModel() { }

    @NotEmpty(message = UserConstants.USERNAME_EMPTY_MESSAGE)
    @Size(min = UserConstants.USERNAME_MIN_LENGTH,
            max = UserConstants.USERNAME_MAX_LENGTH,
            message = UserConstants.USERNAME_SIZE_MESSAGE)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty(message = UserConstants.PASSWORD_EMPTY_MESSAGE)
    @Size(min = UserConstants.PASSWORD_AND_CONFIRM_PASSWORD_MIN_LENGTH,
            max = UserConstants.PASSWORD_AND_CONFIRM_PASSWORD_MAX_LENGTH,
            message = UserConstants.PASSWORD_SIZE_MESSAGE)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotEmpty(message = UserConstants.PASSWORD_CONFIRM_EMPTY_MESSAGE)
    @Size(min = UserConstants.PASSWORD_AND_CONFIRM_PASSWORD_MIN_LENGTH,
            max = UserConstants.PASSWORD_AND_CONFIRM_PASSWORD_MAX_LENGTH,
            message = UserConstants.PASSWORD_CONFIRM_SIZE_MESSAGE)
    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Email
    @EmailUnique
    @NotEmpty(message = UserConstants.EMAIL_EMPTY_MESSAGE)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
