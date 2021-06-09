package com.max.shop.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubCategoryDto {

    private Long id;
    private String name;
    private List<CharacteristicDto> characteristics;
}
