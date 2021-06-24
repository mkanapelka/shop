package com.max.shop.bo.controller;

import com.max.shop.bo.service.BoCategoryService;
import com.max.shop.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class BoCategoryController {

    private final BoCategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void saveCategory(@RequestBody CategoryDto categoryDto){
        categoryService.saveCategory(categoryDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void excludeCategory(@PathVariable Long id){
        categoryService.excludeCategory(id);
    }
}
