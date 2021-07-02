package com.max.shop.service.email;

import com.max.shop.entity.User;

public interface EmailService {
    void sendSimpleMessage(User user, String text);
}
