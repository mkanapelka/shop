package com.max.shop.security;

import com.max.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
@RequiredArgsConstructor
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final SecurityContextRepository cookieSecurityContextRepository;
    private final CookieBasedAuthenticationSuccessHandler cookieBasedAuthenticationSuccessHandler;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .requestMatchers().antMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/public/**").permitAll()
                .antMatchers("/api/login").permitAll()
                .anyRequest().permitAll()
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/api/oauth2/authorization/")
                .and()
                .loginProcessingUrl("/api/login/oauth2/code/*")
                .failureHandler(new AuthenticationFailureHandlerImpl())
                .successHandler(oAuth2SuccessHandler)
                .and()
                .formLogin()
                .loginProcessingUrl("/api/login")
                .failureHandler(new AuthenticationFailureHandlerImpl())
                .successHandler(cookieBasedAuthenticationSuccessHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .securityContext().securityContextRepository(cookieSecurityContextRepository)
                .and()
                .anonymous()
                .authenticationFilter(this.anonymousAuthenticationFilter())
                .authenticationProvider(this.anonymousAuthenticationProvider());
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }


    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }


    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public AnonymousAuthenticationFilter anonymousAuthenticationFilter() {
        return new SavingAnonymousAuthenticationFilter("anonymousUser", userService);
    }

    @Bean
    public AuthenticationProvider anonymousAuthenticationProvider() {
        return new AnonymousAuthenticationProvider("anonymousUser");
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> initCorsFilter() {
        val source = new UrlBasedCorsConfigurationSource();
        val conf = new CorsConfiguration();
        log.debug("CORS configuration");
        conf.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        conf.setAllowedMethods(Collections.singletonList("*"));
        conf.setAllowCredentials(true);
        conf.setMaxAge(3600L);
        conf.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", conf);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
