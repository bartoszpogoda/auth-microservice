package com.github.bartoszpogoda.auth.error;

import com.github.bartoszpogoda.auth.error.dto.ApiErrorDto;
import com.github.bartoszpogoda.auth.error.dto.ValidationApiSubErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
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

        ApiErrorDto apiErrorDto = ApiErrorDto.builder()
                .status(status.value())
                .message("Validation failed")
                .timestamp(LocalDateTime.now()).build();

        ex.getBindingResult().getFieldErrors().stream()
                .map(this::mapToValidationApiSubErrorDto)
                .forEach(apiErrorDto::addSubError);

        return ResponseEntity.badRequest().body(apiErrorDto);
    }

    private ValidationApiSubErrorDto mapToValidationApiSubErrorDto(FieldError fieldError) {
        return ValidationApiSubErrorDto.builder()
                .field(fieldError.getField())
                .message(fieldError.getDefaultMessage())
                .rejectedValue(fieldError.getRejectedValue())
                .build();
    }

    @ExceptionHandler(AbstractApiError.class)
    public ResponseEntity<ApiErrorDto> handleApiError(AbstractApiError apiError) {

        return ResponseEntity.status(apiError.getStatus())
                .body(
                        ApiErrorDto.builder()
                                .message(apiError.getMessage())
                                .status(apiError.getStatus().value())
                                .timestamp(LocalDateTime.now()).build()
                );
    }
}
