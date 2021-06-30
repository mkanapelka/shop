package com.max.shop.exception;

public class DifferentPasswordsException extends BaseException{

    public DifferentPasswordsException() {
        super("different password");
    }

    public DifferentPasswordsException(String message) {
        super(message);
    }

}
