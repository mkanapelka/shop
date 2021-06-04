package com.max.shop.generator;

import com.max.shop.constans.Constants;
import com.max.shop.entity.Image;
import com.max.shop.repository.ImageRepository;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ImageGenerator {

    private final ImageRepository imageRepository;

    public ImageGenerator(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @SneakyThrows
    public void addImages(){
        byte[] imageArray = IOUtils.toByteArray(new ClassPathResource("/images/logo.png").getInputStream());

        for (int i = 0; i < Constants.QUANTITY_PRODUCT; i++) {
            Image image = new Image();
            image.setBigImageArray(imageArray);
            image.setCreated(LocalDateTime.now());
            image.setUpdated(LocalDateTime.now());
            imageRepository.save(image);
        }
    }
}
