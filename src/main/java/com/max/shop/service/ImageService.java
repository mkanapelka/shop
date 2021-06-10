package com.max.shop.service;

import com.max.shop.exception.EntityNotFountException;
import com.max.shop.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public StreamingResponseBody getImageStream(Long imageId) {
        val image = imageRepository.findById(imageId).orElseThrow(() -> new EntityNotFountException("Image"));
        InputStream is = new ByteArrayInputStream(image.getBigImageArray());
        return os -> IOUtils.copyLarge(is, os);
    }
}
