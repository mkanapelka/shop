package com.max.shop.repository;

import com.max.shop.entity.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CartRepository extends JpaRepository<Cart, Long>, JpaSpecificationExecutor<Cart> {

    @EntityGraph(attributePaths = "productInCarts")
    Cart findCartByUserId(Long id);
}
