package com.max.shop.controller;

import com.max.shop.entity.User;
import com.max.shop.exception.UserNotFoundException;
import com.max.shop.repository.UserRepository;
import com.max.shop.repository.parent.IntegrationTestBase;
import com.max.shop.security.AuthCookieService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Sql("/sql/data.sql")
class UserControllerTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthCookieService authCookieService;

    @BeforeEach
    void updateContext(){
        User user = userRepository.findById(10001L).orElseThrow(UserNotFoundException::new);

        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(user,"", user.getAuthorities()));

    }

    @SneakyThrows
    @Test
    void showUserInfo() {
        mvc.perform(get("/api/users/profile").header(HttpHeaders.COOKIE, authCookieService.buildCookie("ivan")))
                .andDo(print())
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.username",is("ivan")));

    }
}