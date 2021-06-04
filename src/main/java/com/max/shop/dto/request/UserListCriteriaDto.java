package com.max.shop.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserListCriteriaDto {

    private String username;
    private String email;
    private LocalDate dateCreatedFrom;
    private LocalDate dateCreatedTo;
    private Boolean isActive;
}


