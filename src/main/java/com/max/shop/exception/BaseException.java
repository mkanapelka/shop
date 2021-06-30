package com.max.shop.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private int statusCode = 500;
}
