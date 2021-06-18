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
        User user = new User();
        if (authCookieService.readCookieFromRequest(request).isPresent()) {
            String userName = authCookieService.readCookieFromRequest(request).get();
            user = userRepository.findByName(userName).orElseGet(null);
            user.setEmail(oidcUser.getEmail());
            user.setName(oidcUser.getSubject());
            user.setFirstName(oidcUser.getGivenName());
            user.setLastName(oidcUser.getFamilyName());
            user.setIsActive(true);
            user.setAuthType(AuthType.GOOGLE);
            user.setRoles(Collections.singleton(Role.USER));
        } else {
            user = userRepository.findByEmail(oidcUser.getEmail())
                    .orElseGet(
                            () -> User.builder()
                                    .email(oidcUser.getEmail())
                                    .name(oidcUser.getSubject())
                                    .firstName(oidcUser.getGivenName())
                                    .lastName(oidcUser.getFamilyName())
                                    .isActive(true)
                                    .authType(AuthType.GOOGLE)
                                    .roles(Collections.singleton(Role.USER))
                                    .build()
                    );

        }
        userRepository.save(user);
        this.authCookieService.addAuthCookieForOAuth(response, authentication, user);
        log.info("success login with oauth2");
        response.setStatus(200);

    }
}
