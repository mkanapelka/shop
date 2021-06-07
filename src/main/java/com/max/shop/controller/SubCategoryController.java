package com.max.shop.controller;

import com.max.shop.constans.Constants;
import com.max.shop.dto.SubCategoryDto;
import com.max.shop.dto.request.SubCategoryCriteriaDto;
import com.max.shop.entity.SubProductCategory;
import com.max.shop.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/subcategories")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<SubCategoryDto> listSubCategories(
            @PageableDefault(size = Constants.DEFAULT_PAGE_SIZE) Pageable pageable,
            SubCategoryCriteriaDto subCategoryCriteria) {
        return subCategoryService.listSubCategories(subCategoryCriteria, pageable);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubCategoryDto oneSubCategory(@PathVariable Long id) {
        return subCategoryService.findSubCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public SubProductCategory saveSubCategory(@RequestBody SubCategoryDto subCategoryDto) {
        return subCategoryService.saveSubCategory(subCategoryDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeSubCategory(@PathVariable Long id) {
        subCategoryService.removeSubCategory(id);
    }

}
