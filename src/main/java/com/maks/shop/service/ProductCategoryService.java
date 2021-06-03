package com.maks.shop.service;

import com.maks.shop.converter.MapperService;
import com.maks.shop.dto.CategoryDto;
import com.maks.shop.dto.request.CategoryCriteriaDto;
import com.maks.shop.entity.ProductCategory;
import com.maks.shop.repository.ProductCategoryRepository;
import com.maks.shop.specification.ProductCategorySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryRepository categoryRepository;
    private final MapperService conversionService;

    public Page<CategoryDto> listCategories(CategoryCriteriaDto categoryCriteria, Pageable pageable) {
        Page<ProductCategory> categories =
                categoryRepository.findAll(ProductCategorySpecification.buildListFilter(categoryCriteria), pageable);
        List<CategoryDto> categoryList =
                conversionService.convertList(categories.getContent(), CategoryDto.class);
        return new PageImpl<>(categoryList, pageable, categories.getTotalElements());
    }
}
