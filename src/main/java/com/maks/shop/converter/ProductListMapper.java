package com.maks.shop.converter;

import com.maks.shop.converter.ProductMapper;
import com.maks.shop.dto.ProductDto;
import com.maks.shop.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.List;


@Mapper(uses = {ProductMapper.class})
public interface ProductListMapper extends Converter<Collection<Product>, List<ProductDto>> {

    List<ProductDto> convert(Collection<Product> source);
}
