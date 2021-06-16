package com.max.shop.security;

import com.max.shop.entity.Role;
import com.max.shop.service.UserService;
import lombok.val;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.UUID;

public class SavingAnonymousAuthenticationFilter extends AnonymousAuthenticationFilter {

    private final AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource =
        new WebAuthenticationDetailsSource();

    private final String key;
    private final UserService userService;

    public SavingAnonymousAuthenticationFilter(String key, UserService userService) {
        super(key);
        this.key = key;
        this.userService = userService;
    }

    @Override
    protected Authentication createAuthentication(HttpServletRequest request) {
        val user = this.userService.createUser(UUID.randomUUID().toString());
        AnonymousAuthenticationToken token = new AnonymousAuthenticationToken(this.key, user,
            Collections.singletonList(Role.ANONYMOUS));
        token.setDetails(this.authenticationDetailsSource.buildDetails(request));
        return token;
    }
}
