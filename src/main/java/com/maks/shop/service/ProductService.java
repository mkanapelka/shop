package com.maks.shop.service;

import com.maks.shop.dto.ProductDto;
import com.maks.shop.dto.request.ProductCriteriaDto;
import com.maks.shop.entity.Product;
import com.maks.shop.repository.ProductRepository;
import com.maks.shop.specification.ProductSpecification;
import com.maks.shop.util.CastClassUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ConversionService conversionService;


    public Page<ProductDto> listProducts(ProductCriteriaDto productCriteria, Pageable pageable) {
        Page<Product> products = productRepository.findAll(ProductSpecification.buildListFilter(productCriteria), pageable);
        List<ProductDto> profilesList =
                conversionService.convert(products.getContent(), CastClassUtil.castClass(List.class));
        return new PageImpl<>(profilesList, pageable, products.getTotalElements());
    }


}
