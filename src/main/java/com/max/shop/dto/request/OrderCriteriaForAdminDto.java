package com.max.shop.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCriteriaForAdminDto {

    private int totalCost1;
    private int totalCost2;
    private int quantityProduct1;
    private int quantityProduct2;
    private String status;
    private Long productId;
}
