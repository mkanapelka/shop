package com.max.shop.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryCriteriaDto {

    private String name;
    private String characteristicName;
}
