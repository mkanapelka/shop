package com.max.shop.bo.service;

import com.max.shop.bo.specification.BoProductSpecification;
import com.max.shop.converter.MapperService;
import com.max.shop.dto.ProductDto;
import com.max.shop.dto.request.ProductCriteriaBoDto;
import com.max.shop.entity.Product;
import com.max.shop.entity.ProductStatus;
import com.max.shop.exception.EntityNotFountException;
import com.max.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoProductService {

    private final ProductRepository productRepository;
    private final MapperService conversionService;

    public Page<ProductDto> listProducts(ProductCriteriaBoDto productCriteria, Pageable pageable) {
        Page<Product> products =
                productRepository.findAll(BoProductSpecification.buildListFilter(productCriteria), pageable);
        List<ProductDto> profilesList =
                conversionService.convertList(products.getContent(), ProductDto.class);
        return new PageImpl<>(profilesList, pageable, products.getTotalElements());
    }

    public void saveProduct(ProductDto productDto){
        productRepository.save(conversionService.convert(productDto, Product.class));
    }

    public ProductDto setStatus(Long id, ProductStatus status){
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFountException("Product"));
        product.setStatus(status);
        product = productRepository.save(product);
        return conversionService.convert(product, ProductDto.class);
    }

    public void removeProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFountException("Product"));
        product.setStatus(ProductStatus.DELETED);
    }
}
