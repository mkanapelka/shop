package com.max.shop.controller;

import com.max.shop.constans.Constants;
import com.max.shop.dto.CategoryDto;
import com.max.shop.dto.request.CategoryCriteriaDto;
import com.max.shop.entity.ProductCategory;
import com.max.shop.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CategoryDto> listCategories(
            @PageableDefault(size = Constants.DEFAULT_PAGE_SIZE) Pageable pageable,
            CategoryCriteriaDto categoryCriteria) {
        return productCategoryService.listCategories(categoryCriteria, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto oneCategory(@PathVariable Long id) {
        return productCategoryService.findCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductCategory saveCategory(@RequestBody CategoryDto categoryDto) {
        return productCategoryService.saveCategory(categoryDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeCategory(@PathVariable Long id) {
        productCategoryService.removeCategory(id);
    }



}
