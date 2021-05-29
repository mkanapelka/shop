package com.example.shop.controller;

import com.example.shop.dto.ProductDto;
import com.example.shop.service.ProductService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/open/products")
public class ProductController {

    private final ConversionService conversionService;
    private final ProductService productService;

    public ProductController(ConversionService conversionService, ProductService productService) {
        this.conversionService = conversionService;
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ProductDto>> findAll(@PageableDefault Pageable pageable) {
        Page<ProductDto> productDtoPage = productService
                .findAll(pageable)
                .map(item -> conversionService.convert(item, ProductDto.class));
        return new ResponseEntity<>(productDtoPage, HttpStatus.OK);
    }

    @GetMapping("/allByCategory")
    public ResponseEntity<Page<ProductDto>> findAllByCategory(@RequestParam("name") String name,
                                                              @PageableDefault Pageable pageable) {
        Page<ProductDto> productDtoPage = productService
                .findAllByCategory(name, pageable)
                .map(item -> conversionService.convert(item, ProductDto.class));
        return new ResponseEntity<>(productDtoPage, HttpStatus.OK);
    }


    @GetMapping("/allByCharacteristic")
    public ResponseEntity<Page<ProductDto>> findAllByCharacteristic(@RequestParam("name") String name,
                                                                    @PageableDefault Pageable pageable) {
        Page<ProductDto> productDtoPage = productService
                .findAllByCharacteristic(name, pageable)
                .map(item -> conversionService.convert(item, ProductDto.class));
        return new ResponseEntity<>(productDtoPage, HttpStatus.OK);
    }

    @GetMapping("/allByCost")
    public ResponseEntity<Page<ProductDto>> findAllByCost(@RequestParam("cost1") int cost1,
                                                          @RequestParam("cost2") int cost2,
                                                          @PageableDefault Pageable pageable) {
        Page<ProductDto> productDtoPage = productService
                .findAllByCost(cost1, cost2, pageable)
                .map(item -> conversionService.convert(item, ProductDto.class));
        return new ResponseEntity<>(productDtoPage, HttpStatus.OK);
    }


    @GetMapping("/allByQuantity")
    public ResponseEntity<Page<ProductDto>> findAllByQuantity(@RequestParam("quantity1") int quantity1,
                                                              @RequestParam("quantity2") int quantity2,
                                                              @PageableDefault Pageable pageable) {
        Page<ProductDto> productDtoPage = productService
                .findAllByQuantity(quantity1, quantity2, pageable)
                .map(item -> conversionService.convert(item, ProductDto.class));
        return new ResponseEntity<>(productDtoPage, HttpStatus.OK);
    }

    @GetMapping("/allByVendorCode")
    public ResponseEntity<Page<ProductDto>> findAllByVendorCode(@RequestParam("vendorCode") String vendorCode,
                                                                @PageableDefault Pageable pageable) {
        Page<ProductDto> productDtoPage = productService
                .findAllByVendorCode(vendorCode, pageable)
                .map(item -> conversionService.convert(item, ProductDto.class));
        return new ResponseEntity<>(productDtoPage, HttpStatus.OK);
    }

    @GetMapping("/allByDescription")
    public ResponseEntity<Page<ProductDto>> findAllByDescription(@RequestParam("description") String description,
                                                                 @PageableDefault Pageable pageable) {
        Page<ProductDto> productDtoPage = productService
                .findAllByDescription(description, pageable)
                .map(item -> conversionService.convert(item, ProductDto.class));
        return new ResponseEntity<>(productDtoPage, HttpStatus.OK);
    }


}

