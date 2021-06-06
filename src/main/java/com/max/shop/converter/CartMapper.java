package com.max.shop.converter;

import com.max.shop.dto.CartDto;
import com.max.shop.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

@Mapper(uses = {ProductListMapper.class})
public interface CartMapper extends Converter<Cart, CartDto> {

    @Mapping(source = "products", target = "products")
    CartDto convert(Cart cart);

}
