package com.max.shop.security;

import com.max.shop.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
public class AuthCookieService {

    @Value("${auth.cookie-name:user}")
    private String userCookieName;

    public void addAuthCookie(HttpServletResponse response, Authentication authentication) {
        addAuthCookie(response, authentication, null);
    }

    public void addAuthCookie(HttpServletResponse response, Authentication authentication,
                              Predicate<Authentication> condition) {
        Optional.ofNullable(authentication)
            .filter(auth -> condition == null || condition.test(auth))
            .map(auth -> (User) auth.getPrincipal())
            .map(user -> ResponseCookie.from(userCookieName, user.getUsername())
                .maxAge(90 * 24 * 60 * 60)
                .httpOnly(false)
                .sameSite("Lax")
                .path("/")
                .build())
            .ifPresent(cookie -> response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString()));
    }

    public void addAuthCookieForOAuth(HttpServletResponse response, Authentication authentication, User user) {
        DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
        Optional.of(ResponseCookie.from(oidcUser.getName(), user.getUsername())
                .maxAge(90 * 24 * 60 * 60)
                .httpOnly(false)
                .sameSite("Lax")
                .path("/")
                .build())
                .ifPresent(cookie -> response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString()));
    }

    public Optional<String> readCookieFromRequest(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Optional.empty();
        }

        return Stream.of(request.getCookies())
                .filter(c -> StringUtils.equals(userCookieName, c.getName()))
                .map(Cookie::getValue)
                .findFirst();
    }
}
