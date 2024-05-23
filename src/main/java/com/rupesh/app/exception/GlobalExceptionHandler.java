package com.rupesh.app.exception;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

import static com.rupesh.app.exception.ErrorCode.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handle(LockedException ex) {
        return ResponseEntity.status(ACCOUNT_LOCKED.getStatus())
                .body(ExceptionResponse.builder()
                        .code(ACCOUNT_LOCKED.getCode())
                        .description(ACCOUNT_LOCKED.getDescription())
                        .error(ex.getMessage())
                        .build());

    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> handle(DisabledException ex) {
        return ResponseEntity.status(ACCOUNT_DISABLED.getStatus())
                .body(ExceptionResponse.builder()
                        .code(ACCOUNT_DISABLED.getCode())
                        .description(ACCOUNT_DISABLED.getDescription())
                        .error(ex.getMessage())
                        .build());

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handle(BadCredentialsException ex) {
        return ResponseEntity.status(INVALID_CREDENTIALS.getStatus())
                .body(ExceptionResponse.builder()
                        .code(INVALID_CREDENTIALS.getCode())
                        .description(INVALID_CREDENTIALS.getDescription())
                        .error(ex.getMessage())
                        .build());

    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handle(MessagingException ex) {
        return ResponseEntity.status(SERVER_ERROR.getStatus())
                .body(ExceptionResponse.builder()
                        .code(SERVER_ERROR.getCode())
                        .description(SERVER_ERROR.getDescription())
                        .error(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handle(MethodArgumentNotValidException ex) {
        Set<String> errors = new HashSet<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> errors.add(error.getDefaultMessage()));

        return ResponseEntity.status(SERVER_ERROR.getStatus())
                .body(ExceptionResponse.builder()
                        .errors(errors)
                        .build());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handle(Exception ex) {
        return ResponseEntity.status(SERVER_ERROR.getStatus())
                .body(ExceptionResponse.builder()
                        .code(SERVER_ERROR.getCode())
                        .description(SERVER_ERROR.getDescription())
                        .error(ex.getMessage())
                        .build());
    }

}
