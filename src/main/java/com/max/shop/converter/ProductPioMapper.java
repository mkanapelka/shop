package com.max.shop.converter;

import com.max.shop.dto.ProductPioDto;
import com.max.shop.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface ProductPioMapper extends Converter<Product, ProductPioDto> {

    ProductPioDto convert(Product product);
}
