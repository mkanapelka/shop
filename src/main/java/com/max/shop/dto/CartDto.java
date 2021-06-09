package com.max.shop.dto;

import com.max.shop.entity.OrderStatus;
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
