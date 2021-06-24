package com.max.shop.dto;

import com.max.shop.entity.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private Long id;
    private String name;
    private String vendorCode;
    private int cost;
    private Integer quantity;
    private String description;
    private Long thumbnailId;
    private ProductStatus status;
    private SubCategoryDto subProductCategory;
}
