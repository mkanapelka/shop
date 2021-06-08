package com.max.shop.repository;

import com.max.shop.entity.ProductInCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInCartRepository extends JpaRepository<ProductInCart, Long> {
     ProductInCart findByProductId(Long id);
     void removeByProductId(Long id);

}
