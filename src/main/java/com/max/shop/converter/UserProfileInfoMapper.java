package com.max.shop.converter;

import com.max.shop.dto.UserProfileListDto;
import com.max.shop.entity.User;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import java.util.Collection;
import java.util.List;

@Mapper(uses = {UserProfileMapper.class})
public interface UserProfileInfoMapper extends Converter<Collection<User>, List<UserProfileListDto>> {

    List<UserProfileListDto> convert(Collection<User> source);
}
