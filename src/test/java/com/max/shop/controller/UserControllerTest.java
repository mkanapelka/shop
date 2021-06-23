package com.max.shop.controller;

import com.max.shop.repository.parent.IntegrationTestBase;
import com.max.shop.security.AuthCookieService;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import javax.servlet.http.Cookie;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Sql("/sql/data.sql")
class UserControllerTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AuthCookieService authCookieService;

    @SneakyThrows
    @Test
    void showUserInfo() {
        val cookie = authCookieService.buildCookie("ivan");
        mvc.perform(get("/api/users/profile")
            .cookie(new Cookie(cookie.getName(), cookie.getValue())))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username", is("ivan")));
    }
}
