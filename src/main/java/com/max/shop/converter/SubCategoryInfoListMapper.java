package com.max.shop.converter;

import com.max.shop.dto.SubCategoryInfoDto;
import com.max.shop.entity.SubProductCategory;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {SubCategoryInfoMapper.class})
public interface SubCategoryInfoListMapper extends Converter<Collection<SubProductCategory>, List<SubCategoryInfoDto>> {

    List<SubCategoryInfoDto> convert(Collection<SubProductCategory> source);
}
