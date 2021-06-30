package com.max.shop.advice;

import com.max.shop.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<Response> handleException(BaseException e) {
        Response response = new Response();
        response.setMessage(LocalDateTime.now() + " " + e.getMessage());
        return ResponseEntity
            .status(e.getStatusCode())
            .body(response);
    }
}
