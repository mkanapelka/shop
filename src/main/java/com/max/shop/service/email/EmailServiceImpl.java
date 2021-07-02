package com.max.shop.service.email;

import com.max.shop.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;


    @Override
    public void sendSimpleMessage(User user, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(user.getFirstName());
        mailMessage.setTo(user.getEmail());
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
    }
}
