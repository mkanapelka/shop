package com.max.shop.bo.controller;

import com.max.shop.bo.service.BoProductService;
import com.max.shop.constans.Constants;
import com.max.shop.dto.ProductDto;
import com.max.shop.dto.request.ProductCriteriaBoDto;
import com.max.shop.entity.ProductStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class BoProductController {

    private final BoProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductDto> listProducts(
            @PageableDefault(size = Constants.DEFAULT_PAGE_SIZE) Pageable pageable,
            ProductCriteriaBoDto productCriteria) {
        return productService.listProducts(productCriteria, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void saveProduct(@RequestBody ProductDto productDto){
        productService.saveProduct(productDto);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto setStatus(@PathVariable Long id, @RequestParam ProductStatus status){
        return productService.setStatus(id,status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeProduct(@PathVariable Long id){
        productService.removeProduct(id);
    }

}
