package com.max.shop.service;

import com.max.shop.repository.CartRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CartServiceTest{

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

}