package com.github.bartoszpogoda.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AuthorizationResponse {
    private final boolean authorized;
}
