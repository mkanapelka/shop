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
public class UserStatCriteriaDto {

    private Long userId;
    private Integer quantityViewsFrom;
    private Integer quantityViewsTo;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
