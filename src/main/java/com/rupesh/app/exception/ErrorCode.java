package com.rupesh.app.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum ErrorCode {

    NO_CODE(0, NOT_IMPLEMENTED, "No code"),
    INCORRECT_CURRENT_PASSWORD(300, BAD_REQUEST, "Invalid current password"),
    INVALID_CREDENTIALS(301, UNAUTHORIZED, "Invalid credentials"),
    ACCOUNT_DISABLED(303, FORBIDDEN, "Account disabled"),
    ACCOUNT_EXPIRED(304, FORBIDDEN, "Account expired"),
    ACCOUNT_LOCKED(305, FORBIDDEN, "Account locked"),
    ACCOUNT_NOT_FOUND(306, NOT_FOUND, "Account not found"),
    SERVER_ERROR(307, INTERNAL_SERVER_ERROR, "Internal server error"),

    ;

    @Getter
    private final int code;
    @Getter
    private final String description;
    @Getter
    private final HttpStatus status;

    ErrorCode(int code, HttpStatus status, String description) {
        this.code = code;
        this.description = description;
        this.status = status;
    }

}
