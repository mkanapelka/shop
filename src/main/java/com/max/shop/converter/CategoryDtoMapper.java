package com.max.shop.converter;

import com.max.shop.dto.CategoryDto;
import com.max.shop.entity.ProductCategory;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface CategoryDtoMapper extends Converter<CategoryDto, ProductCategory> {

    ProductCategory convert(CategoryDto categoryDto);
}
