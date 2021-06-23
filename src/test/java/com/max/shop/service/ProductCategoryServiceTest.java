package com.max.shop.service;

import com.max.shop.constans.Constants;
import com.max.shop.converter.MapperService;
import com.max.shop.dto.CategoryDto;
import com.max.shop.dto.request.CategoryCriteriaDto;
import com.max.shop.entity.ProductCategory;
import com.max.shop.repository.ProductCategoryRepository;
import com.max.shop.repository.parent.IntegrationTestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@Sql("/sql/data.sql")
class ProductCategoryServiceTest extends IntegrationTestBase {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Autowired
    private ProductCategoryService categoryService;

    @Autowired
    private MapperService conversionService;

    @AfterEach
    void cleanUp() {
        categoryRepository.deleteAll();
    }

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
                .listCategories(criteriaDto, PageRequest.of(1, 10))
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