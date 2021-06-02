package com.maks.shop.controller;

import com.maks.shop.dto.ProductDto;
import com.maks.shop.dto.request.ProductCriteriaDto;
import com.maks.shop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.maks.shop.constans.Constants.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/api/public/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> listUsers(
        @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable,
        ProductCriteriaDto productCriteria) {
        Page<ProductDto> productPage = productService.listProducts(productCriteria, pageable);
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }


}

