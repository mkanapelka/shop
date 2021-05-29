package com.example.shop.service;

import com.example.shop.entity.Product;
import com.example.shop.repository.ProductRepository;
import com.example.shop.specification.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    public Page<Product> findAllByCategory(String nameCategory, Pageable pageable) {
        return productRepository
                .findAll(ProductSpecification.findAllByProductCategory(nameCategory), pageable);
    }

    public Page<Product> findAllByCharacteristic(String nameCharacteristic, Pageable pageable) {
        return productRepository
                .findAll(ProductSpecification.findAllByCharacteristic(nameCharacteristic), pageable);
    }

}
