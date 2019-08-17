package com.github.bartoszpogoda.auth.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        return ResponseEntity.badRequest().body(
                ApiErrorDto.builder()
                    .message("Validation failed")
                    .details(ex.getBindingResult().toString())
                    .timestamp(LocalDateTime.now()).build()
        );
    }

    @ExceptionHandler(AbstractApiError.class)
    public ResponseEntity<ApiErrorDto> handleApiError(AbstractApiError apiError) {

        return ResponseEntity.status(apiError.getStatus())
                .body(
                        ApiErrorDto.builder()
                                .message(apiError.getMessage())
                                .status(apiError.getStatus().value()).build()
                );
    }
}
