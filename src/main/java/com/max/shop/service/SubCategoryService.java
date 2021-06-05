package com.max.shop.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.SubCategoryDto;
import com.max.shop.dto.request.SubCategoryCriteriaDto;
import com.max.shop.entity.SubProductCategory;
import com.max.shop.repository.SubProductCategoryRepository;
import com.max.shop.specification.SubCategorySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryService {

    private final SubProductCategoryRepository subCategoryRepository;
    private final MapperService conversionService;

    public Page<SubCategoryDto> listSubCategories(SubCategoryCriteriaDto subCategoryCriteria, Pageable pageable) {
        Page<SubProductCategory> subCategories =
                subCategoryRepository.findAll(SubCategorySpecification.buildListFilter(subCategoryCriteria), pageable);
        List<SubCategoryDto> subCategoryList =
                conversionService.convertList(subCategories.getContent(), SubCategoryDto.class);
        return new PageImpl<>(subCategoryList, pageable, subCategories.getTotalElements());
    }
}
