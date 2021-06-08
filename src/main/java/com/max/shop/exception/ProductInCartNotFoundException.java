package com.max.shop.exception;

public class ProductInCartNotFoundException extends RuntimeException{

    public ProductInCartNotFoundException() {
        super("ProductInCart not found");
    }

    public ProductInCartNotFoundException(String message) {
        super(message);
    }
}
