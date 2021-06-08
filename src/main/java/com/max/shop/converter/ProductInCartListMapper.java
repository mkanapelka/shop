package com.max.shop.converter;

import com.max.shop.dto.ProductInCartDto;
import com.max.shop.entity.ProductInCart;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {ProductInCartMapper.class})
public interface ProductInCartListMapper extends Converter<Collection<ProductInCart>, List<ProductInCartDto>> {

    List<ProductInCartDto> convert(Collection<ProductInCart> source);
}
