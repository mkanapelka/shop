package com.max.shop.converter;

import com.max.shop.dto.OrderStatDto;
import com.max.shop.entity.stat.OrderStat;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface OrderStatMapper extends Converter<OrderStat, OrderStatDto> {

    OrderStatDto convert(OrderStat orderStat);
}
