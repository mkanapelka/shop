package com.max.shop.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {

    private String orderNumber;
    private int totalCost;
    private int quantityProduct;
    private List<ProductDto> products;
}
