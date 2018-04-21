package org.softuni.accounting.validations.annotations.email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<Email,String>{
    @Override
    public void initialize(Email email) { }
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        boolean isValid = true;

        if (email != null) {

            Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            Matcher emailMatcher = emailPattern.matcher(email);

            if (!emailMatcher.find()) {
                isValid = false;
            }
        }
        return isValid;
    }
}
