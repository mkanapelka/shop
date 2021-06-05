package com.max.shop.controller;

import com.max.shop.constans.Constants;
import com.max.shop.dto.SubCategoryDto;
import com.max.shop.dto.request.SubCategoryCriteriaDto;
import com.max.shop.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
