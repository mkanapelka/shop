package com.max.shop.bo.controller;

import com.max.shop.bo.service.BoSubCategoryService;
import com.max.shop.dto.CategoryDto;
import com.max.shop.dto.SubCategoryDto;
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
@RequestMapping("/api/admin/subcategories")
@RequiredArgsConstructor
public class BoSubCategoryController {

    private final BoSubCategoryService subCategoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void saveSubCategory(@RequestBody SubCategoryDto subCategoryDto){
        subCategoryService.saveSubCategory(subCategoryDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void excludeSubCategory(@PathVariable Long id){
        subCategoryService.excludeSubCategory(id);
    }

}