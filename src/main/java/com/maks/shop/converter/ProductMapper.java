package com.maks.shop.converter;

import com.maks.shop.dto.ProductDto;
import com.maks.shop.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Mapper
public interface ProductMapper extends Converter<Product, ProductDto> {

    ProductDto convert(Product product);
}
