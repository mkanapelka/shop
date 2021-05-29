package com.example.shop.repository;

import com.example.shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findAll(Specification<Product> specification, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    @Query("select p from Product p where p.cost >= :cost1 and p.cost <= :cost2")
    Page<Product> findAllByCost(int cost1, int cost2, Pageable pageable);

    @Query("select p from Product p where p.quantity >= :quantity1 and p.quantity <= :quantity2")
    Page<Product> findAllByQuantity(int quantity1, int quantity2, Pageable pageable);

    Page<Product> findAllByVendorCodeContainingIgnoreCase(String vendorCode, Pageable pageable);

    Page<Product> findAllByDescriptionContainingIgnoreCase(String description, Pageable pageable);
























    @Query("SELECT COUNT(id) FROM Product ")
    Long countProducts();
}
