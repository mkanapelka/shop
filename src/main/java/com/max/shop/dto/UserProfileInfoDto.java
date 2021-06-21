package com.max.shop.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserProfileInfoDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime created;
    private Boolean isActive;
}
