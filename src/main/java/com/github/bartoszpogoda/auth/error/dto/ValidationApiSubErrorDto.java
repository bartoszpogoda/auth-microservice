package com.github.bartoszpogoda.auth.error.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationApiSubErrorDto extends ApiSubErrorDto {
    private String field;
    private Object rejectedValue;
    private String message;
}
