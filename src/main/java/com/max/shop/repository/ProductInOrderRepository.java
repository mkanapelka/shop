package com.max.shop.repository;

import com.max.shop.entity.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {

    ProductInOrder findByProductId(Long id);

    List<ProductInOrder> findAllByOrderId(Long Id);

    void removeAllByOrderId(Long id);

    void removeByProductId(Long id);
}
