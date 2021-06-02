package com.maks.shop.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCriteriaDto {

    private String name;
    private String thumbnail;
}
