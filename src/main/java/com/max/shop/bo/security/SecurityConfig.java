package com.max.shop.bo.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.security.interfaces.RSAPublicKey;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
@RequiredArgsConstructor
@Order(2)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //    TODO: Add handlers
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .requestMatchers().antMatchers("/bo/admin/**")
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/bo/admin/users/login").permitAll()
                .antMatchers("/bo/admin/**").authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .decoder(serverJwtDecoder());

    }

    @Bean
    public JwtDecoder serverJwtDecoder() {
        return NimbusJwtDecoder
                .withPublicKey((RSAPublicKey) KeyUtils.readKeyPair("src/main/resources/sertificat/selfsigned.jks", "127312", "tomcat")
                        .getPublic())
                .build();
    }

}
