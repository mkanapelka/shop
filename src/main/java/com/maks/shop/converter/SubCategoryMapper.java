package com.maks.shop.converter;

import com.maks.shop.dto.SubCategoryDto;
import com.maks.shop.entity.SubProductCategory;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface SubCategoryMapper extends Converter<SubProductCategory, SubCategoryDto> {

    SubCategoryDto convert(SubProductCategory subProductCategory);
}
