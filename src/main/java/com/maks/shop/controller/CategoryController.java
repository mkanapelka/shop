package com.maks.shop.controller;

import com.maks.shop.dto.CategoryDto;
import com.maks.shop.dto.ProductDto;
import com.maks.shop.dto.request.CategoryCriteriaDto;
import com.maks.shop.dto.request.ProductCriteriaDto;
import com.maks.shop.service.ProductCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.maks.shop.constans.Constants.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/api/public/categories")
public class CategoryController {

    private final ProductCategoryService productCategoryService;

    public CategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDto>> listUsers(
            @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable,
            CategoryCriteriaDto categoryCriteria) {
        Page<CategoryDto> categoryPage = productCategoryService.listCategories(categoryCriteria, pageable);
        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
    }


}
