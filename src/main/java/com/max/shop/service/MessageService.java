package com.max.shop.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.MessageDto;
import com.max.shop.entity.Message;
import com.max.shop.exception.EntityNotFountException;
import com.max.shop.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MapperService conversionService;

    public MessageDto findOneById(Long id){
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFountException(Message.class));
        return conversionService.convert(message, MessageDto.class);
    }

    public void save(MessageDto messageDto){
        messageRepository.save(conversionService.convert(messageDto, Message.class));
    }
}
