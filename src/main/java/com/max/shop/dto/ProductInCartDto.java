package com.max.shop.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ProductInCartDto {

    private Long productId;
    private String name;
    private int quantity;
    private Integer cost;
}
