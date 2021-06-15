package com.max.shop.converter;

import com.max.shop.entity.Order;
import com.max.shop.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

@Mapper(uses = {ProductInOrderListMapper.class})
public interface OrderMapper extends Converter<Order, OrderDto> {

    @Mapping(source = "productInOrders", target = "productInOrders")
    OrderDto convert(Order order);
}
