package com.max.shop.generator;

import com.max.shop.constans.Constants;
import com.max.shop.entity.Image;
import com.max.shop.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ImageGenerator {

    private final ImageRepository imageRepository;

    public void addImages() throws IOException {
        for (int i = 0; i < Constants.QUANTITY_PRODUCT; i++) {
            byte[] imageArray = IOUtils.toByteArray(new ClassPathResource("/images/logo.png").getInputStream());
            Image image = new Image();
            image.setBigImageArray(imageArray);
            image.setCreated(LocalDateTime.now());
            image.setUpdated(LocalDateTime.now());
            imageRepository.save(image);
        }
    }
}
