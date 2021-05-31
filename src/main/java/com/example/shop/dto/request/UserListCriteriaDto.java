package com.example.shop.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class UserListCriteriaDto {

    private String username;
    private String email;
    private LocalDate dateCreatedFrom;
    private LocalDate dateCreatedTo;
    private Boolean isActive;//TODO add isActive
}
