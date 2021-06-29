package com.max.shop.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.UserProfileDto;
import com.max.shop.entity.AuthType;
import com.max.shop.entity.User;
import com.max.shop.exception.EntityNotFountException;
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
        User user = userRepository.findById(userContext.getId()).orElseThrow(() -> new EntityNotFountException("User"));
        return conversionService.convert(user, UserProfileDto.class);
    }

    public User createUser(String username) {
        val user = User.builder()
                .username(username)
                .isActive(false)
                .authType(AuthType.ANONYMOUS)
                .build();

        return userRepository.save(user);
    }
}
