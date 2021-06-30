package com.max.shop.exception;

public class UserIsNotRegisteredException extends BaseException{

    public UserIsNotRegisteredException() {
        super("User is not registered");
    }

    public UserIsNotRegisteredException(String message) {
        super(message);
    }
}
