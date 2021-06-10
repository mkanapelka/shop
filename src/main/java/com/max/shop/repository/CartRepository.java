package com.max.shop.repository;

import com.max.shop.entity.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @EntityGraph(attributePaths = "productInCarts")
    Cart findCartByUserId(Long id);
}
