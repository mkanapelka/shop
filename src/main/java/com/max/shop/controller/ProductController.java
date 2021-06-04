package com.max.shop.controller;

import com.max.shop.constans.Constants;
import com.max.shop.dto.ProductDto;
import com.max.shop.dto.request.ProductCriteriaDto;
import com.max.shop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<Page<ProductDto>> listUsers(
        @PageableDefault(size = Constants.DEFAULT_PAGE_SIZE) Pageable pageable,
        ProductCriteriaDto productCriteria) {
        Page<ProductDto> productPage = productService.listProducts(productCriteria, pageable);
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }


}

