package com.max.shop.converter;

import com.max.shop.dto.UserStatDto;
import com.max.shop.entity.stat.UserStat;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface UserStatMapper extends Converter<UserStat, UserStatDto> {

    UserStatDto convert(UserStat userStat);
}
