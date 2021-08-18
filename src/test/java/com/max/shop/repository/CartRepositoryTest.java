package com.max.shop.repository;

import com.max.shop.entity.Cart;
import com.max.shop.entity.ProductInCart;
import com.max.shop.repository.parent.BaseRepositoryTest;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CartRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private CartRepository testSubject;

    @Autowired
    private ProductInCartRepository productInCartRepository;

    @Test
    @Transactional //TODO I don't like it
    public void shouldCascadeSaveProducts() {

        Cart cart = testSubject.save(new Cart());

        assertThat(cart.getProductInCarts()).isNull();

        ProductInCart product = ProductInCart.builder()
            .quantity(10)
            .cart(cart)
            .build();
        cart.setProductInCarts(new ArrayList<>(Collections.singletonList(product)));
        testSubject.save(cart);

        val result = testSubject.getOne(cart.getId());
        assertThat(result.getProductInCarts())
            .hasSize(1)
            .extracting("quantity")
            .containsExactly(10);
        assertThat(productInCartRepository.getOne(result.getProductInCarts().get(0).getId()))
            .extracting("quantity")
            .isEqualTo(10);
    }


    // TODO fix this
    //    @Test
    //    public void shouldCascadeUpdateProducts() {
    //
    //        Cart cart = new Cart();
    //        ProductInCart product = ProductInCart.builder()
    //            .quantity(10)
    //            .cart(cart)
    //            .build();
    //        cart.setProductInCarts(new ArrayList<>(Collections.singletonList(product)));
    //
    //        testSubject.save(cart);
    //
    //        assertThat(cart.getProductInCarts())
    //            .hasSize(1)
    //            .extracting("quantity")
    //            .containsExactly(10);
    //        assertThat(productInCartRepository.findAll())
    //            .hasSize(1)
    //            .extracting("quantity")
    //            .containsExactly(10);
    //
    //        cart.getProductInCarts().get(0).setQuantity(20);
    //
    //        testSubject.save(cart);
    //
    //        assertThat(cart.getProductInCarts())
    //            .hasSize(1)
    //            .extracting("quantity")
    //            .containsExactly(20);
    //        assertThat(productInCartRepository.findAll())
    //            .hasSize(1)
    //            .extracting("quantity")
    //            .containsExactly(20);
    //    }
    //
    //    @Test
    //    public void shouldCascadeDeleteProducts() {
    //
    //        Cart cart = new Cart();
    //        ProductInCart product = ProductInCart.builder()
    //            .quantity(10)
    //            .cart(cart)
    //            .build();
    //        cart.setProductInCarts(new ArrayList<>(Collections.singletonList(product)));
    //
    //        testSubject.save(cart);
    //
    //        assertThat(cart.getProductInCarts())
    //            .hasSize(1)
    //            .extracting("quantity")
    //            .containsExactly(10);
    //        assertThat(productInCartRepository.findAll())
    //            .hasSize(1)
    //            .extracting("quantity")
    //            .containsExactly(10);
    //
    //        cart.setProductInCarts(Collections.emptyList());
    //
    //        testSubject.save(cart);
    //
    //        assertThat(cart.getProductInCarts())
    //            .hasSize(0);
    //        assertThat(productInCartRepository.findAll())
    //            .hasSize(0);
    //    }


    @Test
    void findCartByUserId() {

        Cart cart = testSubject.findCartByUserId(10001L);
        assertEquals(10001L, cart.getUser().getId());
    }


}
