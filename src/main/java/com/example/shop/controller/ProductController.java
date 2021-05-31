package com.example.shop.controller;

import com.example.shop.dto.ProductDto;
import com.example.shop.dto.UserProfileDto;
import com.example.shop.dto.request.ProductCriteriaDto;
import com.example.shop.dto.request.UserListCriteriaDto;
import com.example.shop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.shop.constans.Constants.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("api/open/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<Page<ProductDto>> listUsers(
            @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable,
            ProductCriteriaDto productCriteria) {
        Page<ProductDto> productPage = productService.listProducts(productCriteria, pageable);
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }


}

