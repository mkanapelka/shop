package com.max.shop.aspect.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public interface Handler {

    Object exist(ProceedingJoinPoint joinPoint) throws Throwable;
}
