package com.max.shop.converter;

import com.max.shop.dto.UserProfileDto;
import com.max.shop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.core.convert.converter.Converter;

@Mapper(uses = OrderInfoDtoMapper.class)
public interface UserProfileMapper extends Converter<User, UserProfileDto> {

    @Mapping(source = "cart.id", target = "cartId")
    UserProfileDto convert(User user);
}
