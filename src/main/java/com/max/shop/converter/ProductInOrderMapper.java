package com.max.shop.converter;

import com.max.shop.dto.ProductInOrderDto;
import com.max.shop.entity.ProductInOrder;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface ProductInOrderMapper extends Converter<ProductInOrder, ProductInOrderDto> {

    ProductInOrderDto convert(ProductInOrder productInOrder);
}
