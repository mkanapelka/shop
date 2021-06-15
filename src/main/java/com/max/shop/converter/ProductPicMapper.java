package com.max.shop.converter;

import com.max.shop.dto.ProductPicDto;
import com.max.shop.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface ProductPicMapper extends Converter<Product, ProductPicDto> {

    ProductPicDto convert(Product product);
}
