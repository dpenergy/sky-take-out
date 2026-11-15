package com.wudd.exception;

public class PasswordException extends RuntimeException{
    public PasswordException() {
    }

    public PasswordException(String message) {
        super(message);
    }
}
