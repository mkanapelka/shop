package com.max.shop.exception;

public class ProductsNotEnoughException extends RuntimeException{

    public ProductsNotEnoughException() {
        super("Products not enough");
    }

    public ProductsNotEnoughException(String message) {
        super(message);
    }
}
