package com.max.shop.converter;

import com.max.shop.dto.MessageDto;
import com.max.shop.entity.Message;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface MessageDtoMapper extends Converter<MessageDto, Message> {

    Message converter(MessageDto messageDto);
}
