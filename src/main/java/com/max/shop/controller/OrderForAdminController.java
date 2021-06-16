package com.max.shop.controller;

import com.max.shop.constans.Constants;
import com.max.shop.dto.OrderDto;
import com.max.shop.dto.request.OrderCriteriaForAdminDto;
import com.max.shop.entity.OrderStatus;
import com.max.shop.bo.service.BoOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
public class OrderForAdminController {

    private final BoOrderService boOrderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<OrderDto> listOrders(OrderCriteriaForAdminDto orderCriteria,
                                     @PageableDefault(size = Constants.DEFAULT_PAGE_SIZE) Pageable pageable){
        return boOrderService.listOrders(orderCriteria, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto findOne(@PathVariable Long id){
        return boOrderService.fondOneById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto changeOrder(@PathVariable Long id, OrderStatus status){
        return boOrderService.changeOrderStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeOrderById(@PathVariable Long id){
        boOrderService.removeOrderById(id);
    }
}
