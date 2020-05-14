package com.example.UserSession.Services.Exceptions;

public class SessionIdInvalidException extends RuntimeException {

    public SessionIdInvalidException(final String message) {
        super(message);
    }

}