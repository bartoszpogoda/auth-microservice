package com.github.bartoszpogoda.auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AuthorizationRequest {
    private String userId;
    private String URL;
    private String method;
}
