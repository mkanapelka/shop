package com.max.shop.repository;

import com.max.shop.entity.ProductInCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInCartRepository extends JpaRepository<ProductInCart, Long> {

     List<ProductInCart> findAllByCartId(Long Id);
}
