package com.max.shop.repository;

import com.max.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.stream.Stream;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("SELECT COUNT(id) FROM Product ")
    Long countProducts();

    @Query("from Product p")
    Stream<Product> findAllInStream();
}
