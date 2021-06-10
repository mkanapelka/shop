package com.max.shop.exception;

public class EntityNotFountException extends RuntimeException {

    public EntityNotFountException(String entityName) {
        super("Entity '" + entityName + "' not found");
    }
}
