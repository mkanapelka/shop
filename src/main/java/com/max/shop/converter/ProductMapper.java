package com.max.shop.converter;

import com.max.shop.dto.ProductDto;
import com.max.shop.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

@Mapper(uses = {SubCategoryMapper.class})
public interface ProductMapper extends Converter<Product, ProductDto> {

    @Mapping(source = "subProductCategory", target = "subProductCategory")
    ProductDto convert(Product product);
}
