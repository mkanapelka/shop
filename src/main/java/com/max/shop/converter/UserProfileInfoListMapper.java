package com.max.shop.converter;

import com.max.shop.dto.UserProfileInfoDto;
import com.max.shop.entity.User;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import java.util.Collection;
import java.util.List;

@Mapper
public interface UserProfileInfoListMapper extends Converter<Collection<User>, List<UserProfileInfoDto>> {

    List<UserProfileInfoDto> convert(Collection<User> source);
}
