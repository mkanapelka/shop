package com.max.shop.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UserProfileDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime created;
    private List<OrderInfoListDto> orders;
    private Long cartId;
    private Boolean isActive;
}
