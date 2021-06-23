package com.max.shop.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private Long id;
    private int totalCost;
    private int quantityProduct;
    private String status;
    private List<ProductInOrderDto> productInOrders;
}
