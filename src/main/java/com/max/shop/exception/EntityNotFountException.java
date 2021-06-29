package com.max.shop.exception;

import com.max.shop.entity.parent.BaseEntity;

public class EntityNotFountException extends RuntimeException {

    public EntityNotFountException(String entityName) {
        super("Entity '" + entityName + "' not found");
    }

//    public EntityNotFountException(Class<?> targetClass) {
//        super("Entity '" + entityName + "' not found");
//    }
}
