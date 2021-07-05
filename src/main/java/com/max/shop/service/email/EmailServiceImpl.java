package com.max.shop.service.email;

import com.max.shop.dto.MessageDto;
import com.max.shop.entity.User;
import com.max.shop.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final MessageService messageService;


    @Override
    public void sendSimpleMessage(User user, Long messageId) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        MessageDto messageDto = messageService.findOneById(messageId);
        String message = MessageFormat
                .format(messageDto.getText(), user.getFirstName(), user.getLastName());

        mailMessage.setFrom(user.getFirstName());
        mailMessage.setTo(user.getEmail());
        mailMessage.setText(message);
        mailMessage.setFrom(messageDto.getFromAddress());
        mailMessage.setSubject(messageDto.getSubject());
        javaMailSender.send(mailMessage);
    }
}
