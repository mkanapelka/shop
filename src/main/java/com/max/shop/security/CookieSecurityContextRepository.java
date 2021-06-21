package com.max.shop.security;

import com.max.shop.entity.User;
import com.max.shop.exception.UserNotFoundException;
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
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CookieSecurityContextRepository implements SecurityContextRepository {

    private static final String EMPTY_CREDENTIALS = "";

    @Value("${auth.disable-url-rewriting:false}")
    private boolean disableUrlRewriting;

    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final AuthCookieService authCookieService;
    @Setter
    private Set<String> pathToSkip;

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();
        HttpServletResponse response = requestResponseHolder.getResponse();
        requestResponseHolder.setResponse(new SaveToCookieResponseWrapper(response, disableUrlRewriting));

        SecurityContext context = SecurityContextHolder.createEmptyContext();

        //TODO configure request matcher to skip /api/login uri and let the UseranmePasswordAuthFilter to login user
        /* request.getRequestURI() = "/api/login";
         *
         * if(pathToSkip.contains(requestUri)
         * skip readUserInfoFromCookie()
         *
         * OR
         * new AntPathRequestMatcher("/api/login","POST").matches(request);
         */
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
                } catch (UserNotFoundException e) {
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
