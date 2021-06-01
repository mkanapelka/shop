package com.maks.shop.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
