package org.softuni.accounting.validations.annotations.email;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface Email {

    String message() default "* Invalid Email";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
