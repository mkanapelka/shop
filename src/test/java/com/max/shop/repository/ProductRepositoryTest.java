package com.max.shop.repository;

import com.max.shop.dto.request.ProductCriteriaDto;
import com.max.shop.entity.Product;
import com.max.shop.repository.parent.BaseRepositoryTest;
import com.max.shop.repository.parent.IntegrationTestBase;
import com.max.shop.specification.ProductSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql("/sql/data.sql")
public class ProductRepositoryTest extends BaseRepositoryTest {

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


        List<Product> productList = productRepository.findAll(ProductSpecification.buildListFilter(new ProductCriteriaDto()));
        assertEquals(productsExpected.size(), productList.size());
        assertEquals(productsExpected.get(0).getName(), productList.get(0).getName());
        assertEquals(productsExpected.get(0).getCost(), productList.get(0).getCost());
        assertEquals(productsExpected.get(0).getDescription(), productList.get(0).getDescription());
        assertEquals(productsExpected.get(0).getVendorCode(), productList.get(0).getVendorCode());
        assertEquals(productsExpected.get(0).getQuantity(), productList.get(0).getQuantity());
    }

    @Test
    void testFindAllByIdInProductsId(){
        List<Long> expectedIdList = Collections.singletonList(10001L);
        List<Product> idList = productRepository.findAllByIdInProductsId(expectedIdList).collect(Collectors.toList());
        assertThat(idList).hasSize(1);
        assertEquals(idList.get(0).getId(),10001L);

    }
}
