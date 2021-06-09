package com.max.shop.converter;

import com.max.shop.dto.ProductInOrderDto;
import com.max.shop.entity.ProductInOrder;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {ProductInOrderMapper.class})
public interface ProductInOrderListMapper extends Converter<Collection<ProductInOrder>, List<ProductInOrderDto>> {

    List<ProductInOrderDto> convert(Collection<ProductInOrder> source);
}
