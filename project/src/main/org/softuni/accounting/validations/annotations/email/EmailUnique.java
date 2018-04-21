package org.softuni.accounting.validations.annotations.email;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailUniqueValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface EmailUnique {
    String message() default "* Email address already taken";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
