package com.maks.shop.converter;

import com.maks.shop.converter.CategoryMapper;
import com.maks.shop.dto.CategoryDto;
import com.maks.shop.entity.ProductCategory;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {CategoryMapper.class})
public interface CategoryListMapper extends Converter<Collection<ProductCategory>, List<CategoryDto>> {

    List<CategoryDto> convert(Collection<ProductCategory> source);
}
