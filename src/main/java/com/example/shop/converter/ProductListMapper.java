package com.example.shop.converter;

import com.example.shop.dto.ProductDto;
import com.example.shop.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.List;


@Mapper(uses = {ProductMapper.class})
public interface ProductListMapper extends Converter<Collection<Product>, List<ProductDto>> {

    List<ProductDto> convert(Collection<Product> source);
}
