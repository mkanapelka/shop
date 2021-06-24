package com.max.shop.converter;

import com.max.shop.dto.ProductInfoDto;
import com.max.shop.entity.Product;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {ProductInfoMapper.class})
public interface ProductInfoListMapper extends Converter<Collection<Product>, List<ProductInfoDto>> {

    List<ProductInfoDto> convert(Collection<Product> source);
}
