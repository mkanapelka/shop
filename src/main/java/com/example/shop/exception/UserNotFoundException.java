package com.example.shop.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("User not found exception");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
