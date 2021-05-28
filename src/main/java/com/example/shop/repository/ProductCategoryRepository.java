package com.example.shop.repository;

import com.example.shop.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {

    ProductCategory findProductCategoryById(Long id);
}
