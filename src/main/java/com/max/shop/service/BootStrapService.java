package com.max.shop.service;

import com.max.shop.generator.ProductGenerator;
import com.max.shop.repository.ProductRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
@Profile("init-products")
public class BootStrapService implements ApplicationRunner {

    private ProductGenerator productGenerator;
    private ProductRepository productRepository;

    public BootStrapService(ProductGenerator productGenerator, ProductRepository productRepository) {
        this.productGenerator = productGenerator;
        this.productRepository = productRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (productRepository.countProducts() < 10) {
            productGenerator.allProductGeneration();
        }
    }
}
