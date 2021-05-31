package com.example.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchDto {
    String categoryName;
    String characteristicName;
    int cost1;
    int cost2;
    int quantity1;
    int quantity2;
    String vendorCode;
    String description;
}
