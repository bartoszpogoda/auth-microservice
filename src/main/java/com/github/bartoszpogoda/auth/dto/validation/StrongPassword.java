package com.github.bartoszpogoda.auth.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Constraint(validatedBy = {StrongPasswordValidator.class})
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {
    String message() default "Password doesn't fulfill security standards";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
