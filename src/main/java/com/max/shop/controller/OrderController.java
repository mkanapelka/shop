package com.max.shop.controller;

import com.max.shop.dto.OrderDto;
import com.max.shop.dto.request.OrderCriteriaForUserDto;
import com.max.shop.entity.embeddable.OrderDetails;
import com.max.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> listOrdersByUser(OrderCriteriaForUserDto criteriaDto){
        return orderService.showOrdersByUser(criteriaDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderDto createOrder(@RequestBody OrderDetails orderDetails){
        return orderService.createOrder(orderDetails);
    }


    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void removeOrder(@RequestParam Long id){
        orderService.cancelOrder(id);
    }

}
