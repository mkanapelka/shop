package com.max.shop.converter;

import com.max.shop.dto.ProductDto;
import com.max.shop.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface ProductMapper extends Converter<Product, ProductDto> {

    ProductDto convert(Product product);
}
