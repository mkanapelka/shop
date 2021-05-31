package com.example.shop.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCriteriaDto {

    private String categoryName;
    private String characteristicName;
    private Integer cost1;
    private Integer cost2;
    private Integer quantity1;
    private Integer quantity2;
    private String vendorCode;
    private String description;
}
