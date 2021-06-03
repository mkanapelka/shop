package com.maks.shop.converter;

import com.maks.shop.converter.UserProfileMapper;
import com.maks.shop.dto.UserProfileDto;
import com.maks.shop.entity.User;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import java.util.Collection;
import java.util.List;

@Mapper(uses = {UserProfileMapper.class})
public interface UserProfileListMapper extends Converter<Collection<User>, List<UserProfileDto>> {

    List<UserProfileDto> convert(Collection<User> source);
}
