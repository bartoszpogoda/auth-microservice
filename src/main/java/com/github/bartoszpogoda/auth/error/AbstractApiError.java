package com.github.bartoszpogoda.auth.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class AbstractApiError extends RuntimeException {

    public abstract HttpStatus getStatus();

    public abstract String getMessage();

//    public Optional<Exception> getInnerException();

}
