package com.github.bartoszpogoda.auth.error.impl;

import com.github.bartoszpogoda.auth.error.AbstractApiError;
import org.springframework.http.HttpStatus;

public class RegistrationFailedError extends AbstractApiError {

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getMessage() {
        return "Some unexpected problem took place while performing this operation. Please contact administrator.";
    }
}
