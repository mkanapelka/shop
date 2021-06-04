package com.max.shop.exception;

public class SubCategoryNotFoundException extends RuntimeException{

    public SubCategoryNotFoundException() {
        super("SubCategory Not Found");
    }

    public SubCategoryNotFoundException(String message) {
        super(message);
    }
}
