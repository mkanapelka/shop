package com.max.shop.converter;

import com.max.shop.dto.ProductInfoDto;
import com.max.shop.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface ProductInfoMapper extends Converter<Product, ProductInfoDto> {

    ProductInfoDto convert(Product product);
}
