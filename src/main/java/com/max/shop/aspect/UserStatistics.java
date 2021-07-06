package com.max.shop.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UserStatistics {

    /**
     * field index shows the position number of the parameter
     */
    int index() default -1;
    StatisticsType value();
}
