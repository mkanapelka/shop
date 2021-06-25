package com.max.shop.converter;

import com.max.shop.dto.CharacteristicDto;
import com.max.shop.entity.Characteristic;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface CharacteristicDtoMapper extends Converter<CharacteristicDto, Characteristic> {

    Characteristic convert(CharacteristicDto characteristicDto);
}
