package com.max.shop.converter;

import com.max.shop.dto.UserProfileDto;
import com.max.shop.entity.User;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface UserProfileMapper extends Converter<User, UserProfileDto> {

    UserProfileDto convert(User user);
}
