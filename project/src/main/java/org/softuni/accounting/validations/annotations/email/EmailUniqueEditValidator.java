package org.softuni.accounting.validations.annotations.email;

import org.softuni.accounting.areas.users.domain.entities.users.User;
import org.softuni.accounting.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUniqueEditValidator implements ConstraintValidator<EmailUniqueEdit, String> {

    private final UserService userService;

    @Autowired
    public EmailUniqueEditValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(EmailUniqueEdit email) { }
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        boolean isValid = true;

        User existingUser = this.userService.findByEmail(email);

        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        User loggedUser = this.userService.findByEmail(user.getUsername());

        if (existingUser != null) {
            isValid = false;
        }
        if (loggedUser.getEmail().equals(email)) {
            isValid = true;
        }
        return isValid;
    }
}
