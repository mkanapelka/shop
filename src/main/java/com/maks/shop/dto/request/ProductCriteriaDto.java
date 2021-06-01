package com.maks.shop.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCriteriaDto {

    private String name;
    private String categoryName;
    private String characteristicName;
    private Integer cost1;
    private Integer cost2;
    private Integer quantity1;
    private Integer quantity2;
    private String vendorCode;
    private String description;

    public ProductCriteriaDto(String name, Integer cost1, Integer cost2, Integer quantity1, Integer quantity2, String vendorCode, String description) {
        this.name = name;
        this.cost1 = cost1;
        this.cost2 = cost2;
        this.quantity1 = quantity1;
        this.quantity2 = quantity2;
        this.vendorCode = vendorCode;
        this.description = description;
    }
}
