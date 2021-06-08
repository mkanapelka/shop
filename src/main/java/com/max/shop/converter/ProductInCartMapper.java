package com.max.shop.converter;

import com.max.shop.dto.ProductInCartDto;
import com.max.shop.entity.ProductInCart;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface ProductInCartMapper extends Converter<ProductInCart, ProductInCartDto> {

    ProductInCartDto convert(ProductInCart productInCart);
}
