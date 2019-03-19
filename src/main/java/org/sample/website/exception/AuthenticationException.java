package org.sample.website.exception;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(Throwable cause) {
        super(cause);
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
