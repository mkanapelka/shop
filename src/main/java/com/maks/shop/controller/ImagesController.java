package com.maks.shop.controller;

import com.maks.shop.util.ContentTypeUtil;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@Controller
@RequestMapping("/api/public/images")
public class ImagesController {

    @GetMapping(value = "/thumbnails/{thumbnailId}")
    @SneakyThrows
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long thumbnailId, HttpServletResponse response) {
        byte[] data = IOUtils.toByteArray(new ClassPathResource("/images/image.jpg").getInputStream());
        ContentTypeUtil.setContentType(response, "image.jpg");
        return ResponseEntity.ok(data);
    }

    @GetMapping("/stream/thumbnails/{thumbnailId}")
    @SneakyThrows
    public StreamingResponseBody downloadImageStream(@PathVariable Long thumbnailId, HttpServletResponse response) {
        InputStream is = new ClassPathResource("/images/image.jpg").getInputStream();
        ContentTypeUtil.setContentType(response, "image.jpg");
        return os -> IOUtils.copyLarge(is, os);
    }
}
