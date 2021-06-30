package com.max.shop.aspect.handler;

import com.max.shop.dto.OrderDto;
import com.max.shop.entity.stat.OrderStat;
import com.max.shop.repository.OrderStatRepository;
import com.max.shop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class OrderCreateHandler implements Handler {

    private final OrderStatRepository orderStatRepository;

    @Override
    public Object exist(ProceedingJoinPoint joinPoint) throws Throwable {
        Long userId = SecurityUtil.getUserId();
        OrderDto orderDto = (OrderDto) joinPoint.proceed();
        OrderStat orderStat = orderStatRepository
                .findByUserIdAndDateViews(userId, LocalDate.now())
                .orElseGet(() -> OrderStat.builder()
                        .quantityOrders(0)
                        .totalCost(0)
                        .dateViews(LocalDate.now())
                        .build());
        orderStat.setQuantityOrders(orderStat.getQuantityOrders() + 1);
        orderStat.setTotalCost(orderStat.getTotalCost() + orderDto.getTotalCost());
        orderStatRepository.save(orderStat);
        return orderDto;
    }
}
