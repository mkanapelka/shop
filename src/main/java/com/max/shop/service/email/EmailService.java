package com.max.shop.service.email;

import com.max.shop.entity.User;
import org.springframework.scheduling.annotation.Async;

public interface EmailService {

    @Async
    void sendSimpleMessage(User user, String text);
}
