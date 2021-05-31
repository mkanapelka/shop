package com.example.shop.converter;

import com.example.shop.dto.UserProfileDto;
import com.example.shop.entity.User;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import java.util.Collection;
import java.util.List;

@Mapper(uses = {UserProfileMapper.class})
public interface UserProfileListMapper extends Converter<Collection<User>, List<UserProfileDto>> {

    List<UserProfileDto> convert(Collection<User> source);
}
