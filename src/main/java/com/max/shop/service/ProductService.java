package com.max.shop.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.ProductDto;
import com.max.shop.dto.request.ProductCriteriaDto;
import com.max.shop.entity.Product;
import com.max.shop.exception.ProductNotFoundException;
import com.max.shop.repository.ProductRepository;
import com.max.shop.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MapperService conversionService;

    public Page<ProductDto> listProducts(ProductCriteriaDto productCriteria, Pageable pageable) {
        Page<Product> products =
            productRepository.findAll(ProductSpecification.buildListFilter(productCriteria), pageable);
        List<ProductDto> profilesList =
            conversionService.convertList(products.getContent(), ProductDto.class);
        return new PageImpl<>(profilesList, pageable, products.getTotalElements());
    }

    public Product findObeById(Long id){
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }


}
