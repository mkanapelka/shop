package com.max.shop.service;

import com.max.shop.IntegrationTestBase;
import com.max.shop.dto.CategoryDto;
import com.max.shop.dto.request.CategoryCriteriaDto;
import com.max.shop.entity.ProductCategory;
import com.max.shop.repository.ProductCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductCategoryServiceTest extends IntegrationTestBase {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Autowired
    private ProductCategoryService categoryService;

    @Test
    public void testListCategories() {
        CategoryCriteriaDto criteriaDto = CategoryCriteriaDto.builder()
            .name("компьютеры")
            .subCategoryName("ноутбуки")
            .build();

        ProductCategory categoryExp = ProductCategory.builder()
            .name("компьютеры")
            .thumbnail("Computer")
            .build();

        CategoryDto category = categoryService
            .listCategories(criteriaDto, PageRequest.of(0, 10))
            .getContent()
            .get(0);

        assertEquals(categoryExp.getName(), category.getName());
    }

    @Test
    public void testFindCategoryById() {
        CategoryDto category = categoryService.findCategoryById(10001L);
        assertEquals("первое", category.getName());
    }
}
