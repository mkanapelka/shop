package com.max.shop.aspect.handler;

import com.max.shop.aspect.StatisticsType;
import com.max.shop.dto.OrderDto;
import com.max.shop.entity.stat.OrderStat;
import com.max.shop.repository.OrderStatRepository;
import com.max.shop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class OrderCreateHandler implements Handler {

    private final OrderStatRepository orderStatRepository;

    @Override
    public void writeStatistics(Object order) {
        Long userId = SecurityUtil.getUserId();
        OrderDto orderDto = (OrderDto) order;
        OrderStat orderStat = orderStatRepository
            .findByUserIdAndDateViews(userId, LocalDate.now())
            .orElseGet(() -> OrderStat.builder()
                .quantityOrders(0)
                .totalCost(0)
                .userId(userId)
                .dateViews(LocalDate.now())
                .build());
        orderStat.setQuantityOrders(orderStat.getQuantityOrders() + 1);
        orderStat.setTotalCost(orderStat.getTotalCost() + orderDto.getTotalCost());
        orderStatRepository.save(orderStat);
    }

    @Override public boolean supports(StatisticsType type) {
        return type == StatisticsType.ORDER_CREATE;
    }
}
