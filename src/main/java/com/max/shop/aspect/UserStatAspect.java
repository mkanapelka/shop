package com.max.shop.aspect;

import com.max.shop.aspect.handler.Handler;
import com.max.shop.aspect.handler.OrderCreateHandler;
import com.max.shop.aspect.handler.ProductViewHandler;
import com.max.shop.repository.OrderStatRepository;
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
    private final OrderStatRepository orderStatRepository;

    @SneakyThrows
    @Around("@annotation(userStatistics)")
    public Object addUserStatisticAfterReturning(ProceedingJoinPoint joinPoint,
                                                 UserStatistics userStatistics) {
        Map<StatisticsType, Handler> map = createHandlersMap();
        Handler handler = map.get(userStatistics.value());
        return handler.exist(joinPoint);
    }

    private Map<StatisticsType, Handler> createHandlersMap(){
        Map<StatisticsType, Handler> map = new HashMap<>();
        map.put(StatisticsType.PRODUCT_VIEW, new ProductViewHandler(userStatRepository));
        map.put(StatisticsType.ORDER_CREATE, new OrderCreateHandler(orderStatRepository));
        return map;
    }



}




