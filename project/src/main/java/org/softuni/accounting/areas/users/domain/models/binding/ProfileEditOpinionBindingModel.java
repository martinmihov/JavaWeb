package org.softuni.accounting.areas.users.domain.models.binding;

import javax.validation.constraints.Size;

public class ProfileEditOpinionBindingModel {

    private static final int OPINION_MIN_LENGTH      = 3;
    private static final int OPINION_MAX_LENGTH      = 254;

    private static final String OPINION_SIZE_MESSAGE = "* Opinion must be between 3 and 254 symbols in length";

    private String opinion;

    private String email;

    public ProfileEditOpinionBindingModel() { }

    @Size(min = OPINION_MIN_LENGTH,
            max = OPINION_MAX_LENGTH,
            message = OPINION_SIZE_MESSAGE)
    public String getOpinion() {
        return this.opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
