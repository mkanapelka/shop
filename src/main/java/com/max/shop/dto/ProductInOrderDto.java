package com.max.shop.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ProductInOrderDto {

    private Integer quantity;
    private ProductPicDto product;
}
