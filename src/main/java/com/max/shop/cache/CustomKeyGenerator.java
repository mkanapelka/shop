package com.max.shop.cache;

import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
@Component
public class CustomKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object o, Method method, Object... params) {
        return o.getClass().getSimpleName() + "_"
                + method.getName() + "_"
                + StringUtils.arrayToDelimitedString("_", params);
    }
}
