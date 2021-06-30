package com.max.shop.exception;

public class CartIsEmptyException extends BaseException {

    public CartIsEmptyException() {
        super("Cart is empty");
    }

    public CartIsEmptyException(String message) {
        super(message);
    }
}
