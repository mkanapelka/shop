package com.max.shop.aspect;

import com.max.shop.repository.UserStatRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@RequiredArgsConstructor
public class UserStatAspect {

    private final UserStatRepository userStatRepository;

    @SneakyThrows
    @Around("@annotation(userStatistics)")
    public Object addUserStatisticAfterReturning(ProceedingJoinPoint joinPoint,
                                                 UserStatistics userStatistics) {
        Map<StatisticsType, Command> map = new HashMap<>();
        map.put(StatisticsType.PRODUCT_VIEW, new ProductViewHandler(userStatRepository));

        Command command = map.get(userStatistics.value());
        return command.exist(joinPoint);
    }



}




