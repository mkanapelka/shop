package com.max.shop.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.UserProfileDto;
import com.max.shop.entity.User;
import com.max.shop.exception.UserNotFoundException;
import com.max.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MapperService conversionService;
    private final UserRepository userRepository;

    public UserProfileDto findUserById() {
        User userContext = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(userContext.getId()).orElseThrow(UserNotFoundException::new);
        return conversionService.convert(user, UserProfileDto.class);
    }

    public User createUser(String username) {
        val user = User.builder()
            .name(username)
            .isActive(false)
            .build();

        return userRepository.save(user);
    }
}
