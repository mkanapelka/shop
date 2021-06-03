package com.maks.shop.converter;

import com.maks.shop.dto.CategoryDto;
import com.maks.shop.entity.ProductCategory;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(uses = {SubCategoryListMapper.class})
public interface CategoryMapper extends Converter<ProductCategory, CategoryDto> {

    CategoryDto convert(ProductCategory category);
}
