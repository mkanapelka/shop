package com.max.shop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public interface Command {

    Object exist(ProceedingJoinPoint joinPoint) throws Throwable;
}
