package com.max.shop.bo.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.CategoryDto;
import com.max.shop.entity.ProductCategory;
import com.max.shop.exception.CategoryNotFoundException;
import com.max.shop.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoCategoryService {

    private final ProductCategoryRepository categoryRepository;
    private final MapperService conversionService;

    @Transactional
    public void saveCategory(CategoryDto categoryDto) {
        categoryRepository.save(conversionService.convert(categoryDto, ProductCategory.class));
    }

    @Transactional
    public void excludeCategory(Long id){
        ProductCategory productCategory = categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
        productCategory.setIsActive(false);
        categoryRepository.save(productCategory);
    }

}
