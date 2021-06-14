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

//    @Test
//    void testFindByProductId(){
//
//        ProductInCart productExpected = ProductInCart.builder()
//                .name("кефир")
//                .productId(12L)
//                .build();
//
//        testSubject.save(productExpected);
//        ProductInCart product = testSubject.findByProductId(12L);
//        assertEquals(productExpected.getProductId(), product.getProductId());
//    }
//
//    @Test
//    @Sql("/sql/data.sql")
//    void  testFindAllByCartId(){
//
//        Cart cart = cartRepository.findCartByUserId(10001L);
//
//        ProductInCart productExpected = ProductInCart.builder()
//                .name("кефир")
//                .productId(12L)
//                .cart(cart)
//                .build();
//        testSubject.save(productExpected);
//
//        assertThat(testSubject.findAllByCartId(cart.getId()))
//                .hasSize(1)
//                .extracting("name")
//                .containsExactly("кефир");
//    }


}