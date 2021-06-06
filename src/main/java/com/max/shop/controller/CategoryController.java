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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping
    public ResponseEntity<Page<CategoryDto>> listCategories(
            @PageableDefault(size = Constants.DEFAULT_PAGE_SIZE) Pageable pageable,
            CategoryCriteriaDto categoryCriteria) {
        Page<CategoryDto> categoryPage = productCategoryService.listCategories(categoryCriteria, pageable);
        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<CategoryDto>> listAllCategories(
            @PageableDefault(size = Constants.DEFAULT_PAGE_SIZE) Pageable pageable) {
        Page<CategoryDto> categoryPage = productCategoryService.listAllCategories(pageable);
        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<CategoryDto> oneCategory(@PathVariable Long id) {
        CategoryDto categoryDto = productCategoryService.findCategoryById(id);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ProductCategory> saveCategory(@RequestBody CategoryDto categoryDto) {
        ProductCategory productCategory = productCategoryService.saveCategory(categoryDto);
        return new ResponseEntity<>(productCategory, HttpStatus.OK);
    }

    @GetMapping("/remove/{id}")
    public void removeCategory(@PathVariable Long id) {
        productCategoryService.removeCategory(id);
    }



}
