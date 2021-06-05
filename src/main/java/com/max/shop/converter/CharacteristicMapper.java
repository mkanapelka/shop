package com.max.shop.converter;

import com.max.shop.dto.CharacteristicDto;
import com.max.shop.entity.Characteristic;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface CharacteristicMapper extends Converter<Characteristic, CharacteristicDto> {

    CharacteristicDto convert(Characteristic characteristic);
}
