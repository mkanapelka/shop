package com.max.shop.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.ProductDto;
import com.max.shop.dto.request.ProductCriteriaDto;
import com.max.shop.entity.Product;
import com.max.shop.exception.ProductNotFoundException;
import com.max.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.max.shop.specification.ProductSpecification.buildListFilter;
import static com.max.shop.specification.ProductSpecification.buildSingleFilter;
import static com.max.shop.specification.ProductSpecification.fetchCharacteristics;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MapperService conversionService;

    public Page<ProductDto> listProducts(ProductCriteriaDto productCriteria, Pageable pageable) {
        Page<Product> products =
            productRepository.findAll(buildListFilter(productCriteria), pageable);
        List<ProductDto> profilesList =
            conversionService.convertList(products.getContent(), ProductDto.class);
        return new PageImpl<>(profilesList, pageable, products.getTotalElements());
    }

    public Product findObeById(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public ProductDto getProduct(Long id) {
        val product = productRepository.findOne(buildSingleFilter(id).and(fetchCharacteristics()));
        return conversionService.convert(product, ProductDto.class);
    }
}
