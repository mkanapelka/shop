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

//    @SneakyThrows
//    @Around("@annotation(userStatistics)")
//    public Object addUserStatisticAfterReturning(ProceedingJoinPoint joinPoint,
//                                                 UserStatistics userStatistics) {
//        //may be void
//        Object returnValue = joinPoint.proceed(joinPoint.getArgs());
//        Handler handler = this.statisticsHandlerFactory.getHandler(userStatistics.value());
//        handler.writeStatistics(returnValue);
//
//        return returnValue;
//    }

    @SneakyThrows
    @AfterReturning(pointcut = "@annotation(userStatistics)", returning = "result")
    public void addUserStatisticAfterReturning(JoinPoint joinPoint, UserStatistics userStatistics, Object result) {
        Handler handler = this.statisticsHandlerFactory.getHandler(userStatistics.value());

        if (userStatistics.index() == -1) {
            handler.writeStatistics(result);
        } else {
            Object[] args = joinPoint.getArgs();
            result = handler.getResultByArg(args[userStatistics.index()]);
            handler.writeStatistics(result);
        }
    }

}




