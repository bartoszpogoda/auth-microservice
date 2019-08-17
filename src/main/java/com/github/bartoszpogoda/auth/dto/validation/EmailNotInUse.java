package com.github.bartoszpogoda.auth.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Constraint(validatedBy = {EmailNotInUseValidator.class})
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailNotInUse {

    String message() default "Email is already in use";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
