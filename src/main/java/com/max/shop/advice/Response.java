package com.max.shop.advice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Response {

    private String message;
    private String date;
    private int statusCode;
}
