package com.max.shop.service;

import com.max.shop.constans.Constants;
import com.max.shop.generator.ImageGenerator;
import com.max.shop.generator.ProductGenerator;
import com.max.shop.repository.ImageRepository;
import com.max.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Profile("init-products")
@RequiredArgsConstructor
@Transactional
public class BootStrapService implements ApplicationRunner {

    private final ProductGenerator productGenerator;
    private final ProductRepository productRepository;
    private final ImageGenerator imageGenerator;
    private final ImageRepository imageRepository;

    @Override
    public void run(ApplicationArguments args) throws IOException {
        if (productRepository.countProducts() < Constants.QUANTITY_PRODUCT) {
            productGenerator.allProductGeneration();
        }

        if (imageRepository.countImage() < Constants.QUANTITY_PRODUCT) {
            imageGenerator.addImages();
        }
    }
}
