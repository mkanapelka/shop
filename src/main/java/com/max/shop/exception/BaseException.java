package com.max.shop.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final int statusCode = 500;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }
}
