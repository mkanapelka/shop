package com.maks.shop.util;

public class CastClassUtil {

    @SuppressWarnings("unchecked")
    public static <T> Class<T> castClass(Class<?> aClass) {
        return (Class<T>) aClass;
    }
}
