package com.max.shop.repository;

import com.max.shop.entity.Cart;
import com.max.shop.entity.ProductInCart;
import com.max.shop.repository.parent.BaseRepositoryTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductInCartRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private ProductInCartRepository testSubject;

    @Autowired
    private CartRepository cartRepository;

    @AfterEach
    void cleanUp() {
        testSubject.deleteAll();
    }

    @Test
    @Sql("/sql/data.sql")
    void  testFindAllByCartId(){

        Cart cart = cartRepository.findCartByUserId(10001L);

//        ProductInCart productExpected = ProductInCart.builder()
//                .quantity(10)
//                .cart(cart)
//                .build();
//        testSubject.save(productExpected);

        assertThat(testSubject.findAllByCartId(cart.getId()))
                .hasSize(1)
                .extracting("quantity")
                .containsExactly(5);
    }


}