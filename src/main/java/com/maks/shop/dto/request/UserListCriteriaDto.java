package com.maks.shop.dto.request;

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
    private Boolean isActive;//TODO add isActive

    public UserListCriteriaDto(String username, String email, Boolean isActive) {
        this.username = username;
        this.email = email;
        this.isActive = isActive;
    }
}


