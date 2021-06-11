package com.max.shop.controller;

import com.max.shop.dto.OrderDto;
import com.max.shop.service.OrderServiceForUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/orders")
@RequiredArgsConstructor
public class OrderForUserController {

    private final OrderServiceForUser orderServiceForUser;

    @GetMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto createOrder(){
        return orderServiceForUser.createOrder();
    }
}
