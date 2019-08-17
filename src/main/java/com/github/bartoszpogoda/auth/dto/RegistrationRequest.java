package com.github.bartoszpogoda.auth.dto;

import com.github.bartoszpogoda.auth.dto.validation.EmailNotInUse;
import com.github.bartoszpogoda.auth.dto.validation.StrongPassword;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegistrationRequest {

    @NotBlank(message = "{auth.validation.message.email-blank}")
    @Email(message = "{auth.validation.message.email-format}")
    @EmailNotInUse(message = "{auth.validation.message.email-in-use}")
    private String email;

    @NotBlank(message = "{auth.validation.message.password-blank}")
    @StrongPassword(message = "{auth.validation.message.password-weak}")
    private String plainPassword;
}
