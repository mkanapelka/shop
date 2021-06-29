package com.max.shop.bo.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.SubCategoryDto;
import com.max.shop.entity.SubProductCategory;
import com.max.shop.exception.EntityNotFountException;
import com.max.shop.repository.SubProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoSubCategoryService {

    private final SubProductCategoryRepository subCategoryRepository;
    private final MapperService conversionService;

    public void saveSubCategory(SubCategoryDto subCategoryDto){
        subCategoryRepository.save(conversionService.convert(subCategoryDto, SubProductCategory.class));
    }

    public void excludeSubCategory(Long id){
        SubProductCategory subProductCategory = subCategoryRepository
                .findById(id).orElseThrow(() -> new EntityNotFountException("SubProductCategory"));
        subProductCategory.setIsActive(false);
        subCategoryRepository.save(subProductCategory);
    }
}
