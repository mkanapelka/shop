package com.max.shop.converter;

import com.max.shop.dto.ProductInCartDto;
import com.max.shop.entity.ProductInCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

@Mapper(uses = {ProductPicMapper.class})
public interface ProductInCartMapper extends Converter<ProductInCart, ProductInCartDto> {

    @Mapping(source = "product", target = "product")
    ProductInCartDto convert(ProductInCart productInCart);
}
