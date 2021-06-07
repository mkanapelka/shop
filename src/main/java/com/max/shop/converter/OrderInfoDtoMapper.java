package com.max.shop.converter;

import com.max.shop.dto.OrderInfoListDto;
import com.max.shop.entity.Order;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface OrderInfoDtoMapper extends Converter<Order, OrderInfoListDto> {

    OrderInfoListDto convert(Order order);
}
