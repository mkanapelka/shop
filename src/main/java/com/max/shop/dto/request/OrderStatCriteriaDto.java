package com.max.shop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatCriteriaDto {

    private Long userId;
    private Integer quantityOrdersFrom;
    private Integer quantityOrdersTo;
    private Integer totalCostFrom;
    private Integer totalCostTo;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
