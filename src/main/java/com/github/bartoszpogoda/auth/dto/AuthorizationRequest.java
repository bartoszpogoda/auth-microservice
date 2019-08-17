package com.github.bartoszpogoda.auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AuthorizationRequest {
    private long userId;
    private String url;
    private String method;
}
