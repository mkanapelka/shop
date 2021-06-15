package com.max.shop.converter;

import com.max.shop.dto.ProductInOrderDto;
import com.max.shop.entity.ProductInOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

@Mapper(uses = {ProductPioMapper.class})
public interface ProductInOrderMapper extends Converter<ProductInOrder, ProductInOrderDto> {

    @Mapping(source = "product", target = "product")
    ProductInOrderDto convert(ProductInOrder productInOrder);
}
