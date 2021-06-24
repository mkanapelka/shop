package com.max.shop.dto.request;

import com.max.shop.entity.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCriteriaBoDto {

    private String name;
    private String subCategoryName;
    private Integer cost1;
    private Integer cost2;
    private Integer quantity1;
    private Integer quantity2;
    private String vendorCode;
    private String description;
    private ProductStatus status;
}