package com.maks.shop.converter;

import com.maks.shop.dto.UserProfileDto;
import com.maks.shop.entity.User;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface UserProfileMapper extends Converter<User, UserProfileDto> {

    UserProfileDto convert(User user);
}
