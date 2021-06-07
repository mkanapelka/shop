package com.max.shop.dto.request;

import com.max.shop.validation.PasswordUpdate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PasswordUpdate
public class UserFormDto {

    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean isActive;

    private String newPassword;
    private String repeatPassword;
}
