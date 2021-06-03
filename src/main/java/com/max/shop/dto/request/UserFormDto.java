package com.max.shop.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFormDto {

    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean isActive;
}
