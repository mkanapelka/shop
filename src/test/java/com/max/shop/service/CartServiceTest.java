package com.max.shop.service;

import com.max.shop.repository.CartRepository;
import com.max.shop.repository.parent.BaseRepositoryTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CartServiceTest extends BaseRepositoryTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

//    @AfterEach
//    void cleanUp() {
//        testSubject.deleteAll();
//    }

    @Test
    @Sql("/sql/data.sql")
    public void testAddProductInCart(){

    }

}