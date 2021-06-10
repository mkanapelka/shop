package com.max.shop.controller;

import com.max.shop.service.ImageService;
import com.max.shop.util.ContentTypeUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/public/images")
@RequiredArgsConstructor
public class ImagesController {

    private final ImageService imageService;

    //    @GetMapping(value = "/thumbnails/{thumbnailId}")
    //    @SneakyThrows
    //    public ResponseEntity<byte[]> downloadImage(@PathVariable Long thumbnailId, HttpServletResponse response) {
    //        byte[] data = IOUtils.toByteArray(new ClassPathResource("/images/image.jpg").getInputStream());
    //        ContentTypeUtil.setContentType(response, "image.jpg");
    //        return ResponseEntity.ok(data);
    //    }

    @GetMapping("/thumbnails/{thumbnailId}")
    @SneakyThrows
    public StreamingResponseBody downloadImageStream(@PathVariable Long thumbnailId, HttpServletResponse response) {
        val stream = imageService.getImageStream(thumbnailId);
        ContentTypeUtil.setContentType(response, "image.jpg");
        return stream;
    }
}
