package com.max.shop.service;

import com.max.shop.repository.CartRepository;
import com.max.shop.repository.parent.BaseRepositoryTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CartServiceTest extends BaseRepositoryTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

}