package org.softuni.accounting.areas.users.domain.models.binding;

import org.softuni.accounting.utils.constants.UserConstants;
import org.softuni.accounting.validations.annotations.password.PasswordMatch;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@PasswordMatch
public class UserEditBindingModel {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String opinion;
    private MultipartFile image;
    private Set<Long> rolesIds;
    private Date deletedOn;

    public UserEditBindingModel() {
    }


    @NotEmpty(message = UserConstants.EMAIL_EMPTY_MESSAGE)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getOpinion() {
        return this.opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
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

    public Date getDeletedOn() {
        return this.deletedOn;
    }

    public void setDeletedOn(Date deletedOn) {
        this.deletedOn = deletedOn;
    }
}
