package com.max.shop.converter;

import com.max.shop.dto.CharacteristicDto;
import com.max.shop.entity.Characteristic;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {CharacteristicMapper.class})
public interface CharacteristicListMapper extends Converter<Collection<Characteristic>, List<CharacteristicDto>> {

    List<CharacteristicDto> convert(Collection<Characteristic> source);
}
