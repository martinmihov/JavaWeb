package org.softuni.accounting.validations.annotations.image;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ImageTypeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
public @ interface ImageType {
    String message() default "The uploaded image's extension has to be of type jpg, jpeg, png or gif";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
