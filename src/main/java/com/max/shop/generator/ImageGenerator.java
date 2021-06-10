package com.max.shop.generator;

import com.max.shop.entity.Image;
import com.max.shop.repository.ImageRepository;
import com.max.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ImageGenerator {

    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void addImages() {
        productRepository.findAllInStream()
            .forEach(product -> {
                val image = saveImage();
                product.setThumbnailId(image.getId());
                productRepository.save(product);
            });

    }

    @SneakyThrows
    private Image saveImage() {
        byte[] imageArray = IOUtils.toByteArray(new ClassPathResource("/images/default512.png").getInputStream());
        Image image = new Image();
        image.setBigImageArray(imageArray);
        image.setCreated(LocalDateTime.now());
        image.setUpdated(LocalDateTime.now());
        return imageRepository.save(image);
    }
}
