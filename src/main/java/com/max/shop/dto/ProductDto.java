package com.max.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
