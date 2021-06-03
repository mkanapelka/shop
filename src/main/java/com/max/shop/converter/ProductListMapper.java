package com.max.shop.converter;

import com.max.shop.dto.ProductDto;
import com.max.shop.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import java.util.Collection;
import java.util.List;


@Mapper(uses = {ProductMapper.class})
public interface ProductListMapper extends Converter<Collection<Product>, List<ProductDto>> {

    List<ProductDto> convert(Collection<Product> source);
}
