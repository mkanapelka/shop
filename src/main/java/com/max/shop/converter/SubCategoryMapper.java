package com.max.shop.converter;

import com.max.shop.dto.SubCategoryDto;
import com.max.shop.entity.SubProductCategory;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface SubCategoryMapper extends Converter<SubProductCategory, SubCategoryDto> {

    SubCategoryDto convert(SubProductCategory subProductCategory);
}
