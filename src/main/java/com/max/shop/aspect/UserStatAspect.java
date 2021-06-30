package com.max.shop.aspect;

import com.max.shop.aspect.handler.Handler;
import com.max.shop.aspect.handler.StatisticsHandlerFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class UserStatAspect {

    private final StatisticsHandlerFactory statisticsHandlerFactory;

    @SneakyThrows
    @Around("@annotation(userStatistics)")
    public Object addUserStatisticAfterReturning(ProceedingJoinPoint joinPoint,
                                                 UserStatistics userStatistics) {
        //may be void
        val returnValue = joinPoint.proceed(joinPoint.getArgs());
        Handler handler = this.statisticsHandlerFactory.getHandler(userStatistics.value());
        handler.writeStatistics(joinPoint.getArgs());

        return returnValue;
    }



}




