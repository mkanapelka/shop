package com.example.shop.converter;

import com.example.shop.dto.UserProfileDto;
import com.example.shop.entity.User;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Mapper
public interface UserProfileMapper extends Converter<User, UserProfileDto> {

    UserProfileDto convert(User user);
}
