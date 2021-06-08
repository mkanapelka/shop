package com.max.shop.converter;

import com.max.shop.dto.SubCategoryInfoDto;
import com.max.shop.entity.SubProductCategory;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface SubCategoryInfoMapper extends Converter<SubProductCategory, SubCategoryInfoDto> {
    SubCategoryInfoDto convert(SubProductCategory subProductCategory);
}
