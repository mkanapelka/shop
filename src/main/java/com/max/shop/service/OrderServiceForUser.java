package com.max.shop.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.OrderDto;
import com.max.shop.entity.Order;
import com.max.shop.repository.OrderRepository;
import com.max.shop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceForUser {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final MapperService conversionService;

    public OrderDto showOrderByUserId() {
        List<Order> orders = orderRepository.findOrdersByUserId(SecurityUtil.getUserId());
        return conversionService.convert(orders, OrderDto.class);
    }
}
