package com.max.shop.converter;

import com.max.shop.dto.CategoryDto;
import com.max.shop.entity.ProductCategory;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(uses = {SubCategoryListMapper.class})
public interface CategoryMapper extends Converter<ProductCategory, CategoryDto> {

    CategoryDto convert(ProductCategory category);
}
