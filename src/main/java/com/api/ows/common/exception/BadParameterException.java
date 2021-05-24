package com.api.ows.common.exception;

public class BadParameterException extends RuntimeException{
    public BadParameterException() {
        super();
    }

    public BadParameterException(String message) {
        super(message);
    }
}
