package com.max.shop.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    String name;
    private List<SubCategoryDto> subCategoryDtoList;
}
