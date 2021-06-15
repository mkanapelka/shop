package com.max.shop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCriteriaForUserDto {

    private Long userId;
    private String status;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDate dateCreatedFrom;
    private LocalDate dateCreatedTo;
}
