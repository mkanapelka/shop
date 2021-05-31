package com.example.shop.service;

import com.example.shop.dto.ProductSearchDto;
import com.example.shop.entity.Product;
import com.example.shop.repository.ProductRepository;
import com.example.shop.specification.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> findAllByCost(int cost1, int cost2, Pageable pageable) {
        return productRepository.findAllByCost(cost1, cost2, pageable);
    }

    public Page<Product> findAllByQuantity(int quantity1, int quantity2, Pageable pageable) {
        return productRepository.findAllByQuantity(quantity1, quantity2, pageable);
    }

    public Page<Product> findAllByVendorCode(String vendorCode, Pageable pageable) {
        return productRepository.findAllByVendorCodeContainingIgnoreCase(vendorCode, pageable);
    }

    public Page<Product> findAllByDescription(String description, Pageable pageable) {
        return productRepository.findAllByDescriptionContainingIgnoreCase(description, pageable);
    }

//    public Page<Product> findAllByCategory(String nameCategory, Pageable pageable) {
//        return productRepository
//                .findAll(ProductSpecification.findAllByProductCategory(nameCategory), pageable);
//    }

    public Page<Product> findAllByCategory(Map<String, String> nameCategory, Pageable pageable) {
        Page<Product> pageProductResult = null;

        for (String name : nameCategory.values()) {
            Page<Product> pageProduct = productRepository
                    .findAll(ProductSpecification.findAllByProductCategory(name), pageable);
            pageProductResult = (Page<Product>) pageProductResult.and(pageProduct);
        }
        return pageProductResult;
    }

    public Page<Product> findAllByCharacteristic(String nameCharacteristic, Pageable pageable) {
        return productRepository
                .findAll(ProductSpecification.findAllByCharacteristic(nameCharacteristic), pageable);
    }

//    ------------------------------------------------------------

    //    todo:exp
    public Page<Product> findAll2(ProductSearchDto productSearchDto, Pageable pageable) {

        Page<Product> pageProductResult = Page.empty();

        if (productSearchDto.getCategoryName() != null) {
            pageProductResult.and(productRepository
                    .findAll(ProductSpecification
                            .findAllByProductCategory(productSearchDto.getCategoryName()), pageable));
        }

        if (productSearchDto.getCharacteristicName() != null) {
            pageProductResult.and(productRepository
                    .findAll(ProductSpecification
                            .findAllByCharacteristic(productSearchDto.getCharacteristicName()), pageable));
        }

        if (productSearchDto.getCost1() >= 0 && productSearchDto.getCost2() >= 0) {
            pageProductResult.and(productRepository
                    .findAllByCost(productSearchDto.getCost1(), productSearchDto.getCost2(), pageable));
        }

        if (productSearchDto.getQuantity1() >= 0 && productSearchDto.getQuantity2() >= 0) {
            pageProductResult.and(productRepository
                    .findAllByQuantity(productSearchDto.getQuantity1(), productSearchDto.getQuantity2(), pageable));
        }

        if (productSearchDto.getDescription() != null) {
            pageProductResult.and(productRepository
                    .findAllByDescriptionContainingIgnoreCase(productSearchDto.getDescription(), pageable));
        }

        if (productSearchDto.getVendorCode() != null) {
            pageProductResult.and(productRepository
                    .findAllByVendorCodeContainingIgnoreCase(productSearchDto.getVendorCode(), pageable));
        }
        return pageProductResult;
    }

}
