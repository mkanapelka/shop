package com.maks.shop.repository;

import com.maks.shop.IntegrationTestBase;
import com.maks.shop.dto.request.ProductCriteriaDto;
import com.maks.shop.entity.Product;
import com.maks.shop.specification.ProductSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductRepositoryTest extends IntegrationTestBase {

    @Autowired
    ProductRepository productRepository;

    @Test
    void testFindAllProductsBySpecification() {

        List<Product> productsExpected = new ArrayList<>();
        productsExpected.add(
                Product.builder()
                        .name("борщ")
                        .cost(1200)
                        .quantity(7)
                        .vendorCode("AL-123456789")
                        .description("чёткий и красный")
                        .build()
        );


        ProductCriteriaDto productCriteria = ProductCriteriaDto.builder()
                .name("борщ")
                .cost1(1000)
                .cost2(2000)
                .quantity1(5)
                .quantity2(10)
                .vendorCode("AL-123456789")
                .description("чёткий и красный")
                .build();


        List<Product> productList = productRepository.findAll(ProductSpecification.buildListFilter(productCriteria));
        assertEquals(productsExpected.size(), productList.size());
        assertEquals(productsExpected.get(0).getName(), productList.get(0).getName());
        assertEquals(productsExpected.get(0).getCost(), productList.get(0).getCost());
        assertEquals(productsExpected.get(0).getDescription(), productList.get(0).getDescription());
        assertEquals(productsExpected.get(0).getVendorCode(), productList.get(0).getVendorCode());
        assertEquals(productsExpected.get(0).getQuantity(), productList.get(0).getQuantity());
    }
}
