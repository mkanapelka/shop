package com.max.shop.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {

    private int totalCost;
    private int quantityProduct;
    private List<ProductInCartDto> productInCarts;
}
