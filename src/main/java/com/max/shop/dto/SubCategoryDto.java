package com.max.shop.dto;

import com.max.shop.entity.Characteristic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubCategoryDto {

    private String name;
    private List<Characteristic> characteristics;
}
