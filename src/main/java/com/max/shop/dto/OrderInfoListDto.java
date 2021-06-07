package com.max.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInfoListDto {

    private Long id;
    private String orderNumber;
    private int totalCost;
    private String status;
}
