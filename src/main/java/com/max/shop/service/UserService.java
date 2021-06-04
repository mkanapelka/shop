package com.max.shop.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.UserProfileDto;
import com.max.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MapperService conversionService;
    private final UserRepository userRepository;

    public UserProfileDto findUserById() {
        //TODO get userId from security context
        //        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        //        return conversionService.convert(user, UserProfileDto.class);
        return null;
    }
}
