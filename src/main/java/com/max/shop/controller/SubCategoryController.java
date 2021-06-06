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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/subcategories")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @GetMapping
    public ResponseEntity<Page<SubCategoryDto>> listSubCategories(
            @PageableDefault(size = Constants.DEFAULT_PAGE_SIZE) Pageable pageable,
            SubCategoryCriteriaDto subCategoryCriteria) {
        Page<SubCategoryDto> subCategoryPage = subCategoryService.listSubCategories(subCategoryCriteria, pageable);
        return new ResponseEntity<>(subCategoryPage, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<SubCategoryDto>> listAllSubCategories(
            @PageableDefault(size = Constants.DEFAULT_PAGE_SIZE) Pageable pageable) {
        Page<SubCategoryDto> subCategoryPage = subCategoryService.listAllSubCategories(pageable);
        return new ResponseEntity<>(subCategoryPage, HttpStatus.OK);
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<SubCategoryDto> oneSubCategory(@PathVariable Long id) {
        SubCategoryDto subCategoryDto = subCategoryService.findSubCategoryById(id);
        return new ResponseEntity<>(subCategoryDto, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<SubProductCategory> saveSubCategory(@RequestBody SubCategoryDto subCategoryDto) {
        SubProductCategory subProductCategory = subCategoryService.saveSubCategory(subCategoryDto);
        return new ResponseEntity<>(subProductCategory, HttpStatus.OK);
    }

    @GetMapping("/remove/{id}")
    public void removeSubCategory(@PathVariable Long id) {
        subCategoryService.removeSubCategory(id);
    }

}
