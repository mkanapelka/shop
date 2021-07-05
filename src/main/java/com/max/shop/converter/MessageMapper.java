package com.max.shop.converter;

import com.max.shop.dto.MessageDto;
import com.max.shop.entity.Message;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface MessageMapper extends Converter<Message, MessageDto> {

    MessageDto convert(Message message);
}
