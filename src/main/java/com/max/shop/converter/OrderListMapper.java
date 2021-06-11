package com.max.shop.converter;

import com.max.shop.dto.OrderDto;
import com.max.shop.entity.Order;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {OrderMapper.class})
public interface OrderListMapper extends Converter<Collection<Order>, List<OrderDto>> {

    List<OrderDto> convert(Collection<Order> source);
}