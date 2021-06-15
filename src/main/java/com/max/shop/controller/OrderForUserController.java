package com.max.shop.controller;

import com.max.shop.dto.OrderDto;
import com.max.shop.dto.request.OrderCriteriaForUserDto;
import com.max.shop.service.OrderServiceForUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/orders")
@RequiredArgsConstructor
public class OrderForUserController {

    private final OrderServiceForUser orderServiceForUser;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> listOrdersByUser(OrderCriteriaForUserDto criteriaDto){
        return orderServiceForUser.showOrdersByUser(criteriaDto);
    }

    @GetMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto createOrder(){
        return orderServiceForUser.createOrder();
    }


    @DeleteMapping ("/{id}}")
    @ResponseStatus(HttpStatus.OK)
    public void removeOrder(@RequestParam Long id){
        orderServiceForUser.cancelOrder(id);
    }

}
