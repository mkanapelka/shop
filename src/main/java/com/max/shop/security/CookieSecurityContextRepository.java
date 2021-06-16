package com.max.shop.security;

import com.max.shop.entity.User;
import com.max.shop.exception.UserNotFoundException;
import com.max.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SaveContextOnUpdateOrErrorResponseWrapper;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CookieSecurityContextRepository implements SecurityContextRepository {

    private static final String EMPTY_CREDENTIALS = "";

    @Value("${auth.cookie-name:user}")
    private String userCookieName;
    @Value("${auth.disable-url-rewriting:false}")
    private boolean disableUrlRewriting;

    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final AuthCookieService authCookieService;

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();
        HttpServletResponse response = requestResponseHolder.getResponse();
        requestResponseHolder.setResponse(new SaveToCookieResponseWrapper(response, disableUrlRewriting));

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        readUserInfoFromCookie(request)
            .ifPresent(userInfo ->
                context.setAuthentication(
                    new UsernamePasswordAuthenticationToken(userInfo, EMPTY_CREDENTIALS, Collections.emptyList())));

        return context;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        SaveToCookieResponseWrapper responseWrapper = (SaveToCookieResponseWrapper) response;
        if (!responseWrapper.isContextSaved()) {
            responseWrapper.saveContext(context);
        }
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return readUserInfoFromCookie(request).isPresent();
    }

    private Optional<User> readUserInfoFromCookie(HttpServletRequest request) {
        return readCookieFromRequest(request)
            .map(cookie -> {
                try {
                    return (User) this.userDetailsService.loadUserByUsername(cookie);
                } catch (UserNotFoundException e) {
                    return userService.createUser(cookie);
                }
            });
    }

    private Optional<String> readCookieFromRequest(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Optional.empty();
        }

        return Stream.of(request.getCookies())
            .filter(c -> StringUtils.equals(userCookieName, c.getName()))
            .map(Cookie::getValue)
            .findFirst();
    }

    private class SaveToCookieResponseWrapper extends SaveContextOnUpdateOrErrorResponseWrapper {

        //        private HttpServletRequest request;

        SaveToCookieResponseWrapper(HttpServletResponse response, boolean disableUrlRewriting) {
            super(response, disableUrlRewriting);
        }

        @Override
        protected void saveContext(SecurityContext securityContext) {
            HttpServletResponse response = (HttpServletResponse) getResponse();
            authCookieService.addAuthCookie(response, securityContext.getAuthentication(),
                auth -> AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass()));
        }
    }
}
