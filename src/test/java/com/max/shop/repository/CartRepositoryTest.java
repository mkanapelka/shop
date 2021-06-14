package com.max.shop.repository;

import com.max.shop.entity.Cart;
import com.max.shop.entity.ProductInCart;
import com.max.shop.repository.parent.BaseRepositoryTest;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CartRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private CartRepository testSubject;

    @Autowired
    private ProductInCartRepository productInCartRepository;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void cleanUp() {
        testSubject.deleteAll();
    }

//    @Test
//    public void shouldCascadeSaveProducts() {
//
//        val cart = testSubject.save(new Cart());
//
//        assertThat(testSubject.findAll())
//                .hasSize(1);
//        assertThat(cart.getProductInCarts()).isNull();
//        assertThat(productInCartRepository.findAll()).hasSize(0);
//
//        val product = ProductInCart.builder()
//                .name("prod_1")
//                .cart(cart)
//                .build();
//        cart.setProductInCarts(new ArrayList<>(Collections.singletonList(product)));
//        testSubject.save(cart);
//
//        assertThat(testSubject.findAll())
//                .hasSize(1);
//        assertThat(cart.getProductInCarts())
//                .hasSize(1)
//                .extracting("name")
//                .containsExactly("prod_1");
//        assertThat(productInCartRepository.findAll())
//                .hasSize(1)
//                .extracting("name")
//                .containsExactly("prod_1");
//    }
//
//    @Test
//    public void shouldCascadeUpdateProducts() {
//
//        val cart = new Cart();
//        val product = ProductInCart.builder()
//                .name("prod_1")
//                .cart(cart)
//                .build();
//        cart.setProductInCarts(new ArrayList<>(Collections.singletonList(product)));
//
//        testSubject.save(cart);
//
//        assertThat(cart.getProductInCarts())
//                .hasSize(1)
//                .extracting("name")
//                .containsExactly("prod_1");
//        assertThat(productInCartRepository.findAll())
//                .hasSize(1)
//                .extracting("name")
//                .containsExactly("prod_1");
//
//        cart.getProductInCarts().get(0).setName("prod_2");
//
//        testSubject.save(cart);
//
//        assertThat(cart.getProductInCarts())
//                .hasSize(1)
//                .extracting("name")
//                .containsExactly("prod_2");
//        assertThat(productInCartRepository.findAll())
//                .hasSize(1)
//                .extracting("name")
//                .containsExactly("prod_2");
//    }
//
//    @Test
//    public void shouldCascadeDeleteProducts() {
//
//        val cart = new Cart();
//        val product = ProductInCart.builder()
//                .name("prod_1")
//                .cart(cart)
//                .build();
//        cart.setProductInCarts(new ArrayList<>(Collections.singletonList(product)));
//
//        testSubject.save(cart);
//
//        assertThat(cart.getProductInCarts())
//                .hasSize(1)
//                .extracting("name")
//                .containsExactly("prod_1");
//        assertThat(productInCartRepository.findAll())
//                .hasSize(1)
//                .extracting("name")
//                .containsExactly("prod_1");
//
//        cart.setProductInCarts(Collections.emptyList());
//
//        testSubject.save(cart);
//
//        assertThat(cart.getProductInCarts())
//                .hasSize(0);
//        assertThat(productInCartRepository.findAll())
//                .hasSize(0);
//    }
//
//
//    @Test
//    @Sql("/sql/data.sql")
//    void findCartByUserId() {
//
//        Cart cart = testSubject.findCartByUserId(10001L);
//        assertEquals(10001L, cart.getUser().getId());
//    }


}
