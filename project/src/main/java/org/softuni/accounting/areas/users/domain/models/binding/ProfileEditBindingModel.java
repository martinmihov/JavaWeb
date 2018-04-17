package org.softuni.accounting.areas.users.domain.models.binding;

import org.softuni.accounting.utils.constants.UserConstants;
import org.softuni.accounting.validations.annotations.email.Email;
import org.softuni.accounting.validations.annotations.email.EmailUniqueEdit;
import org.softuni.accounting.validations.annotations.password.PasswordMatch;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@PasswordMatch
public class ProfileEditBindingModel {

    private static final int OPINION_MIN_LENGTH = 3;
    private static final int OPINION_MAX_LENGTH = 254;

    private static final String OPINION_SIZE_MESSAGE = "* Opinion must be between 3 and 254 symbols in length";

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String opinion;

    public ProfileEditBindingModel() {
    }

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

    @Size(min = UserConstants.PASSWORD_AND_CONFIRM_PASSWORD_MIN_LENGTH,
            max = UserConstants.PASSWORD_AND_CONFIRM_PASSWORD_MAX_LENGTH,
            message = UserConstants.PASSWORD_SIZE_MESSAGE)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Size(min = UserConstants.PASSWORD_AND_CONFIRM_PASSWORD_MIN_LENGTH,
            max = UserConstants.PASSWORD_AND_CONFIRM_PASSWORD_MAX_LENGTH,
            message = UserConstants.PASSWORD_CONFIRM_SIZE_MESSAGE)
    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @EmailUniqueEdit
    @NotEmpty(message = UserConstants.EMAIL_EMPTY_MESSAGE)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Size(min = OPINION_MIN_LENGTH,
            max = OPINION_MAX_LENGTH,
            message = OPINION_SIZE_MESSAGE)
    public String getOpinion() {
        return this.opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}
