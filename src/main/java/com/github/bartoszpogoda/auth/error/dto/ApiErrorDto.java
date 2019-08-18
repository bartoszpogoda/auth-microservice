package com.github.bartoszpogoda.auth.error.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ApiErrorDto {
    private LocalDateTime timestamp;
    private int status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String debugMessage;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ApiSubErrorDto> subErrors;

    public void addSubError(ApiSubErrorDto subError) {
        if (this.subErrors == null) {
            this.subErrors = new ArrayList<>();
        }

        this.subErrors.add(subError);
    }
}
