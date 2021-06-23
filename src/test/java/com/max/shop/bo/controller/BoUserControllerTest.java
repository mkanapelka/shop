package com.max.shop.bo.controller;

import com.max.shop.repository.parent.IntegrationTestBase;
import com.max.shop.security.AuthCookieService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Sql("/sql/data.sql")
class BoUserControllerTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AuthCookieService authCookieService;

    @SneakyThrows
    @Test
//    TODO: It don't work
    public void testShowUserInfo(){
//        mvc.perform(get("/api/users/").param("id","10001"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.username", is("ivan")));

    }
}