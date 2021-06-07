package com.max.shop.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserProfileListDto {

    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime created;
}
