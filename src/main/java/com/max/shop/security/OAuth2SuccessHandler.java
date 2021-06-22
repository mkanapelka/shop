package com.max.shop.security;

import com.max.shop.entity.AuthType;
import com.max.shop.entity.Role;
import com.max.shop.entity.User;
import com.max.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final AuthCookieService authCookieService;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
        User user = null;
        Optional<String> cookie = authCookieService.readCookieFromRequest(request);

        if (cookie.isPresent()) {
            String userName = cookie.get();
            user = userRepository.findByUsername(userName)
                    .map(usr -> defaultOidcUserToUser(oidcUser, usr))
                    .orElse(null);
        }

        if (user == null) {
            user = userRepository.findByEmail(oidcUser.getEmail())
                    .orElse(defaultOidcUserToUser(oidcUser, new User()));
        }
        this.userRepository.save(user);
        this.authCookieService.addAuthCookieForUser(response, user);
        log.info("success login with oauth2");
        response.setStatus(200);
    }

    private User defaultOidcUserToUser(DefaultOidcUser oidcUser, User user) {
        user.setEmail(oidcUser.getEmail());
        user.setUsername(oidcUser.getSubject());
        user.setFirstName(oidcUser.getGivenName());
        user.setLastName(oidcUser.getFamilyName());
        user.setIsActive(true);
        user.setAuthType(AuthType.GOOGLE);
        user.setRoles(Collections.singleton(Role.USER));
        return user;
    }
}
