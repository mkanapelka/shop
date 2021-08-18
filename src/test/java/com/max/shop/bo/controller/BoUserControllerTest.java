package com.max.shop.bo.controller;

import com.max.shop.IntegrationTestBase;
import com.max.shop.security.AuthCookieService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
class BoUserControllerTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AuthCookieService authCookieService;

    @SneakyThrows
    @Test
    //    TODO: It doesn't work
    public void testShowUserInfo(){
//        mvc.perform(get("/api/users/").param("id","10001"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.username", is("ivan")));

    }
}
