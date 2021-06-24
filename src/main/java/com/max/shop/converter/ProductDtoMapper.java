package com.max.shop.converter;

import com.max.shop.dto.ProductDto;
import com.max.shop.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface ProductDtoMapper extends Converter<ProductDto, Product> {

    Product convert(ProductDto productDto);
}
