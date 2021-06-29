package com.max.shop.security;

import com.max.shop.entity.User;
import com.max.shop.exception.EntityNotFountException;
import com.max.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SaveContextOnUpdateOrErrorResponseWrapper;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CookieSecurityContextRepository implements SecurityContextRepository {

    private static final String EMPTY_CREDENTIALS = "";
    private static final RequestMatcher DEFAULT_PATH_TO_SKIP = new AntPathRequestMatcher("/api/login", "POST");

    @Value("${auth.disable-url-rewriting:false}")
    private boolean disableUrlRewriting;

    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final AuthCookieService authCookieService;

    @Setter
    private RequestMatcher pathToSkip = DEFAULT_PATH_TO_SKIP;

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();
        HttpServletResponse response = requestResponseHolder.getResponse();
        requestResponseHolder.setResponse(new SaveToCookieResponseWrapper(response, disableUrlRewriting));
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        if (pathToSkip.matches(request)) {
            return context;
        }

        readUserInfoFromCookie(request)
            .ifPresent(userInfo ->
                context.setAuthentication(
                    new UsernamePasswordAuthenticationToken(userInfo, EMPTY_CREDENTIALS, userInfo.getAuthorities())));
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
        return authCookieService.readCookieFromRequest(request)
            .map(cookie -> {
                try {
                    return (User) this.userDetailsService.loadUserByUsername(cookie);
                } catch (EntityNotFountException ex) {
                    return userService.createUser(cookie);
                }
            });
    }


    private class SaveToCookieResponseWrapper extends SaveContextOnUpdateOrErrorResponseWrapper {

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
