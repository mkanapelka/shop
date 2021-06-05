package com.max.shop.converter;

import com.max.shop.dto.SubCategoryDto;
import com.max.shop.entity.SubProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

@Mapper(uses = {CharacteristicListMapper.class})
public interface SubCategoryMapper extends Converter<SubProductCategory, SubCategoryDto> {

    @Mapping(source = "characteristics", target = "characteristics")
    SubCategoryDto convert(SubProductCategory subProductCategory);
}
