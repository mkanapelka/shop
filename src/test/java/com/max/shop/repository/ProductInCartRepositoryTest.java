package com.max.shop.repository;

import com.max.shop.entity.Cart;
import com.max.shop.repository.parent.BaseRepositoryTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

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
    void  testFindAllByCartId(){

        Cart cart = cartRepository.findCartByUserId(10001L);

        assertThat(testSubject.findAllByCartId(cart.getId()))
                .hasSize(1)
                .extracting("quantity")
                .containsExactly(5);
    }


}
