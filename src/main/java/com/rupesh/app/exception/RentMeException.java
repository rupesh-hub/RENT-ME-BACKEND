package com.rupesh.app.exception;

public class RentMeException extends RuntimeException {

    public RentMeException(String message) {
        super(message);
    }

    public RentMeException(String message, Throwable cause) {
        super(message, cause);
    }

}
