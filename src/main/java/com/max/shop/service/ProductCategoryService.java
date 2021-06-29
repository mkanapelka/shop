package com.max.shop.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.CategoryDto;
import com.max.shop.dto.request.CategoryCriteriaDto;
import com.max.shop.entity.ProductCategory;
import com.max.shop.exception.EntityNotFountException;
import com.max.shop.repository.ProductCategoryRepository;
import com.max.shop.specification.ProductCategorySpecification;
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
            categoryRepository.findAll(
                ProductCategorySpecification.buildListFilter(categoryCriteria)
                    .and(ProductCategorySpecification.fetchSubcategories()), pageable);
        List<CategoryDto> categoryList =
            conversionService.convertList(categories.getContent(), CategoryDto.class);
        return new PageImpl<>(categoryList, pageable, categories.getTotalElements());
    }

    public CategoryDto findCategoryById(Long id) {
        ProductCategory productCategory = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFountException("Category"));
        return conversionService.convert(productCategory, CategoryDto.class);
    }

}
