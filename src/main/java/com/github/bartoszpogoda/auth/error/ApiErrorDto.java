package com.github.bartoszpogoda.auth.error;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiErrorDto {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String details;
}
