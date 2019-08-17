package com.github.bartoszpogoda.auth.dto.validation;

import com.github.bartoszpogoda.auth.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailNotInUseValidator implements ConstraintValidator<EmailNotInUse, String> {

    private final UserService userService;

    public EmailNotInUseValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !userService.userWithEmailExists(value);
    }


}
