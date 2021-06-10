package com.max.shop.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.SubCategoryDto;
import com.max.shop.dto.request.OrderCriteriaDto;
import com.max.shop.dto.request.SubCategoryCriteriaDto;
import com.max.shop.entity.Order;
import com.max.shop.dto.OrderDto;
import com.max.shop.entity.SubProductCategory;
import com.max.shop.repository.OrderRepository;
import com.max.shop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.max.shop.specification.OrderSpecification.*;

@Service
@RequiredArgsConstructor
public class OrderServiceForAdmin {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final MapperService conversionService;


    public Page<OrderDto> listOrders(OrderCriteriaDto orderCriteria, Pageable pageable) {
        Page<Order> orders =
                orderRepository.findAll(buildListFilter(orderCriteria)
                        .and(fetchProductInOrder()), pageable);
        List<OrderDto> orderDtoList =
                conversionService.convertList(orders.getContent(), OrderDto.class);
        return new PageImpl<>(orderDtoList, pageable, orders.getTotalElements());
    }

    public OrderDto showOrderByUserId(Long id) {
        List<Order> orders = orderRepository.findOrdersByUserId(id);
        return conversionService.convert(orders, OrderDto.class);
    }







}
