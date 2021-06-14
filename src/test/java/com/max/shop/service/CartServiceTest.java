package com.max.shop.service;

import com.max.shop.entity.Cart;
import com.max.shop.entity.ProductInCart;
import com.max.shop.entity.User;
import com.max.shop.repository.CartRepository;
import com.max.shop.repository.parent.BaseRepositoryTest;
import com.max.shop.util.SecurityUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CartServiceTest{

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

//    private final Cart cart = Cart.builder()
//            .productInCarts(Collections.singletonList(ProductInCart.builder()
//                    .name("кефир")
//                    .cost(2)
//                    .quantity(1)
//                    .productId(1L)
//                    .build()))
//            .user(User.builder()
//                    .name("user2")
//                    .email("usr@mail.com")
//                    .firstName("Homer")
//                    .lastName("Simpson")
//                    .build())
//            .quantityProduct(1)
//            .totalCost(2)
//            .build();
//
////    TODO: Do it
//
//
//    @Test
//    void testEnsureCart() {
//        given(SecurityUtil.getUserId()).willReturn(12L);
//        given(cartRepository.findCartByUserId(12L)).willReturn(cart);
//        assertEquals(cartService.ensureCart(), cart);
//    }

}