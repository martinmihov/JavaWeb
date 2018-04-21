package org.softuni.accounting.areas.users.domain.models.binding;

import org.springframework.web.multipart.MultipartFile;

public class ProfileUploadAvatarBindingModel {

    private String email;

    private MultipartFile image;

    public ProfileUploadAvatarBindingModel() { }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MultipartFile getImage() {
        return this.image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

}
