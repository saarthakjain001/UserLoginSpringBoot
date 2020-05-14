package com.example.UserSession.Services.Exceptions;

public class InvalidUsernamePasswordException extends RuntimeException {

    public InvalidUsernamePasswordException(final String message) {
        super(message);
    }
}