package com.max.shop.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ProductInCartDto {

    private Integer quantity;
    private ProductDto product;
}
