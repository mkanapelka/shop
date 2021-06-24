package com.max.shop.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.ProductDto;
import com.max.shop.dto.ProductInfoDto;
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

    public Page<ProductInfoDto> listProducts(ProductCriteriaDto productCriteria, Pageable pageable) {
        Page<Product> products =
            productRepository.findAll(buildListFilter(productCriteria), pageable);
        List<ProductInfoDto> profilesList =
            conversionService.convertList(products.getContent(), ProductInfoDto.class);
        return new PageImpl<>(profilesList, pageable, products.getTotalElements());
    }

    public Product findOneById(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public ProductDto getProduct(Long id) {
        Product product = productRepository
                .findOne(buildSingleFilter(id).and(fetchCharacteristics()))
                .orElseThrow(ProductNotFoundException::new);
        return conversionService.convert(product, ProductDto.class);
    }
}
