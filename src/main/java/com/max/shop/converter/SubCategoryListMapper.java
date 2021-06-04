package com.max.shop.converter;

import com.max.shop.dto.SubCategoryDto;
import com.max.shop.entity.SubProductCategory;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {SubCategoryMapper.class})
public interface SubCategoryListMapper  extends Converter<Collection<SubProductCategory>, List<SubCategoryDto>> {

    List<SubCategoryDto> convert(Collection<SubProductCategory> source);
}
