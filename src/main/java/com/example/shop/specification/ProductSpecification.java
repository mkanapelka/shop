package com.example.shop.specification;

import com.example.shop.entity.Characteristic;
import com.example.shop.entity.Product;
import com.example.shop.entity.ProductCategory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> findAllByProductCategory(String name) {
        return (product, criteriaQuery, criteriaBuilder) -> {
            Join<Product, ProductCategory> productToProductCategory = product.join("productCategories");
            return criteriaBuilder.equal(productToProductCategory.get("name"), name);
        };
    }

    public static Specification<Product> findAllByCharacteristic(String name) {
        return (product, criteriaQuery, criteriaBuilder) -> {
            Join<Product, Characteristic> productToProductCategory = product.join("characteristics");
            return criteriaBuilder.equal(productToProductCategory.get("name"), name);
        };
    }



}
