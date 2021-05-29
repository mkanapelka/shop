package com.example.shop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductDto {

    @NotNull
    String name;

    @NotNull
    private String vendorCode;

    @NotNull
    private int cost;

    @NotNull
    private Integer quantity;

    @NotNull
    private String description;
}
