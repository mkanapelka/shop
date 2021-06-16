package com.max.shop.security;

import lombok.SneakyThrows;
import lombok.val;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @SneakyThrows
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) {
        response.setStatus(401);
        val writer = response.getWriter();
        writer.print(exception.getMessage());
        writer.flush();
    }
}
