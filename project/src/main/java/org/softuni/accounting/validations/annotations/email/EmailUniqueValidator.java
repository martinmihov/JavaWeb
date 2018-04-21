package org.softuni.accounting.validations.annotations.email;

import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.softuni.accounting.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class EmailUniqueValidator implements ConstraintValidator<EmailUnique,String> {

    private final UserService userService;

    @Autowired
    public EmailUniqueValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(EmailUnique email) { }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        boolean isValid = true;

        User existingUser = this.userService.findByEmail(email);

        if(existingUser != null){
            isValid = false;
        }
        return isValid;
    }
}
