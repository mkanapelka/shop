package com.max.shop.aspect;

import com.max.shop.aspect.handler.Handler;
import com.max.shop.aspect.handler.StatisticsHandlerFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class UserStatAspect {

    private final StatisticsHandlerFactory statisticsHandlerFactory;

    @SneakyThrows
    @AfterReturning(pointcut = "@annotation(userStatistics)", returning = "result")
    public void addUserStatisticAfterReturning(JoinPoint joinPoint, UserStatistics userStatistics, Object result) {
        Handler handler = this.statisticsHandlerFactory.getHandler(userStatistics.value());

        if (userStatistics.index() != -1) {
            Object[] args = joinPoint.getArgs();
            result = handler.getResultByArg(args[userStatistics.index()]);
        }
        handler.writeStatistics(result);
    }

}




