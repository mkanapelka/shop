package com.max.shop.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("User not found exception");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
