package com.max.shop.bo.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.CategoryDto;
import com.max.shop.entity.ProductCategory;
import com.max.shop.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoCategoryProduct {

    private final ProductCategoryRepository categoryRepository;
    private final MapperService conversionService;

    @Transactional
    public ProductCategory saveCategory(CategoryDto categoryDto) {

        ProductCategory productCategory = new ProductCategory();
        if (categoryDto.getId() != null) {
            productCategory = categoryRepository.findById(categoryDto.getId()).orElse(new ProductCategory());
        }
        conversionService.update(categoryDto, productCategory);
        return categoryRepository.save(productCategory);
    }

    public void removeCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
