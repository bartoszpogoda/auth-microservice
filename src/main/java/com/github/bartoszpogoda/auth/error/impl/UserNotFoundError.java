package com.github.bartoszpogoda.auth.error.impl;

import com.github.bartoszpogoda.auth.error.AbstractApiError;
import org.springframework.http.HttpStatus;

public class UserNotFoundError extends AbstractApiError {

    private long userId;

    public UserNotFoundError(long userId) {
        this.userId = userId;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
        return String.format("User with id %d was not found", userId);
    }
}
