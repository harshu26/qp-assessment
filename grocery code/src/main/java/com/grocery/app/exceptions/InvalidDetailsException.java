package com.grocery.app.exceptions;

public class InvalidDetailsException extends RuntimeException {
    public InvalidDetailsException(String msg) {
        super(msg);
    }
}