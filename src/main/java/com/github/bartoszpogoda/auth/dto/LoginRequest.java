package com.github.bartoszpogoda.auth.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank(message = "{auth.validation.message.email-blank}")
    @Email(message = "{auth.validation.message.email-format}")
    private String email;

    @NotBlank
    private String plainPassword;
}
