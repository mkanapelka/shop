package com.max.shop.util;

import com.max.shop.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserContextUtil {

    public static Long getUserContextId(){

        User userContext = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userContext.getId();
    }
}
