package com.max.shop.exception;

public class EntityNotFountException extends BaseException {

    public EntityNotFountException(String entityName) {
        super("Entity '" + entityName + "' not found");
    }

    public EntityNotFountException(Class<?> targetClass) {
        super("Entity '" + targetClass.getSimpleName() + "' not found");
    }
}
