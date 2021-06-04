package com.max.shop.converter.update;

import com.max.shop.dto.request.UserFormDto;
import com.max.shop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface UserFormUpdateMapper extends UpdateConverter<UserFormDto, User> {

    void update(UserFormDto userForm, @MappingTarget User user);
}
