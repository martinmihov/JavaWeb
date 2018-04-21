package org.softuni.accounting.validations.annotations.password;


import org.softuni.accounting.areas.users.domain.models.binding.ProfileEditBindingModel;
import org.softuni.accounting.areas.users.domain.models.binding.UserEditBindingModel;
import org.softuni.accounting.areas.users.domain.models.binding.UserRegisterBindingModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {


    @Override
    public void initialize(PasswordMatch passwordMatching) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o instanceof UserEditBindingModel){
            UserEditBindingModel user = (UserEditBindingModel) o;

            return user.getPassword().equals(user.getConfirmPassword());
        }
        if (o instanceof ProfileEditBindingModel){
            ProfileEditBindingModel user = (ProfileEditBindingModel) o;

            return user.getPassword().equals(user.getConfirmPassword());
        }
        if (o instanceof UserRegisterBindingModel){
            UserRegisterBindingModel user = (UserRegisterBindingModel) o;

            return user.getPassword().equals(user.getConfirmPassword());
        }
        return false;
    }
}
