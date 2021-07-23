package com.max.shop.advice;

import com.max.shop.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ExceptionDefaultHandler {

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<Response> handleException(BaseException e) {
        Response response = new Response();
        response.setMessage(e.getMessage());
        response.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        response.setStatusCode(e.getStatusCode());
        return ResponseEntity
            .status(e.getStatusCode())
            .body(response);

    }
}
