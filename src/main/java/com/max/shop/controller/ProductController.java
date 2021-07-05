package com.max.shop.controller;

import com.max.shop.constans.Constants;
import com.max.shop.dto.ProductDto;
import com.max.shop.dto.ProductInfoDto;
import com.max.shop.dto.request.ProductCriteriaDto;
import com.max.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductInfoDto> listProducts(
        @PageableDefault(size = Constants.DEFAULT_PAGE_SIZE) Pageable pageable,
        ProductCriteriaDto productCriteria) {
        return productService.listProducts(productCriteria, pageable);
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }


}

