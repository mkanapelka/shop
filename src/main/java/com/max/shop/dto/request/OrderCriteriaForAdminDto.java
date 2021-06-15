package com.max.shop.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCriteriaForAdminDto {

    private Long userId;
    private int totalCost1;
    private int totalCost2;
    private int quantityProduct1;
    private int quantityProduct2;
    private LocalDate dateCreatedFrom;
    private LocalDate dateCreatedTo;
    private String status;
    private Long productId;
}
