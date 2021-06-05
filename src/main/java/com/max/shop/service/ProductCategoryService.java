package com.max.shop.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.CategoryDto;
import com.max.shop.dto.request.CategoryCriteriaDto;
import com.max.shop.dto.request.UserFormDto;
import com.max.shop.entity.ProductCategory;
import com.max.shop.entity.User;
import com.max.shop.exception.CategoryNotFoundException;
import com.max.shop.repository.ProductCategoryRepository;
import com.max.shop.specification.ProductCategorySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    public Page<CategoryDto> listAllCategories(Pageable pageable) {
        List<CategoryDto> categoryList = conversionService.convertList(categoryRepository.findAll(), CategoryDto.class);
        return new PageImpl<>(categoryList, pageable, categoryList.size());
    }

    public CategoryDto findCategoryById(Long id){
        ProductCategory productCategory = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        return conversionService.convert(productCategory, CategoryDto.class);
    }

    public ProductCategory saveCategory(CategoryDto categoryDto) {

        ProductCategory productCategory = new ProductCategory();
        if (categoryDto.getId() != null){
            productCategory = categoryRepository.findById(categoryDto.getId()).orElse(new ProductCategory());
        }
        conversionService.update(categoryDto, productCategory);
        return categoryRepository.save(productCategory);
    }



}
