package com.max.shop.converter;

import com.max.shop.dto.CategoryDto;
import com.max.shop.entity.ProductCategory;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {CategoryMapper.class})
public interface CategoryListMapper extends Converter<Collection<ProductCategory>, List<CategoryDto>> {

    List<CategoryDto> convert(Collection<ProductCategory> source);
}
