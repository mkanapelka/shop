package com.maks.shop.service;

import com.maks.shop.converter.MapperService;
import com.maks.shop.dto.UserProfileDto;
import com.maks.shop.dto.request.UserListCriteriaDto;
import com.maks.shop.entity.User;
import com.maks.shop.exception.UserNotFoundException;
import com.maks.shop.repository.UserRepository;
import com.maks.shop.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MapperService conversionService;
    private final UserRepository userRepository;

    public User findUserByName(String name) {
        return userRepository.findByName(name).orElseThrow(UserNotFoundException::new);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public UserProfileDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return conversionService.convert(user, UserProfileDto.class);
    }

    public Page<UserProfileDto> listUsers(UserListCriteriaDto userCriteria, Pageable pageable) {
        Page<User> users = userRepository.findAll(UserSpecification.buildListFilter(userCriteria), pageable);
        List<UserProfileDto> profilesList =
            conversionService.convertList(users.getContent(), UserProfileDto.class);
        return new PageImpl<>(profilesList, pageable, users.getTotalElements());
    }
}
