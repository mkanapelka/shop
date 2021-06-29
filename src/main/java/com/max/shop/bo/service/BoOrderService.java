package com.max.shop.bo.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.OrderDto;
import com.max.shop.dto.request.OrderCriteriaForAdminDto;
import com.max.shop.entity.Order;
import com.max.shop.entity.OrderStatus;
import com.max.shop.exception.EntityNotFountException;
import com.max.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.max.shop.specification.OrderSpecification.buildListFilterForAdmin;
import static com.max.shop.specification.OrderSpecification.fetchProducts;
import static com.max.shop.specification.OrderSpecification.findOrderById;

@Service
@RequiredArgsConstructor
public class BoOrderService {

    private final OrderRepository orderRepository;
    private final MapperService conversionService;


    public Page<OrderDto> listOrders(OrderCriteriaForAdminDto orderCriteria, Pageable pageable) {
        Page<Order> orders =
                orderRepository.findAll(buildListFilterForAdmin(orderCriteria), pageable);
        List<OrderDto> orderDtoList =
                conversionService.convertList(orders.getContent(), OrderDto.class);
        return new PageImpl<>(orderDtoList, pageable, orders.getTotalElements());
    }

    public OrderDto fondOneById(Long id){
        Order order = orderRepository
                .findOne(findOrderById(id).and(fetchProducts())).orElseThrow(() -> new EntityNotFountException("Order"));
        return conversionService.convert(order, OrderDto.class);
    }

    @Transactional
    public OrderDto changeOrderStatus(Long id, OrderStatus status){
        Order order = orderRepository
                .findOne(findOrderById(id).and(fetchProducts())).orElseThrow(() -> new EntityNotFountException("Order"));
        order.setStatus(status);
        orderRepository.save(order);
        return conversionService.convert(order, OrderDto.class);
    }

    public void removeOrderById(Long id){
        orderRepository.deleteById(id);
    }








}
