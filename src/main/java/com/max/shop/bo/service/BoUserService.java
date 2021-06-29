package com.max.shop.bo.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.UserProfileDto;
import com.max.shop.dto.UserProfileInfoDto;
import com.max.shop.dto.request.UserFormDto;
import com.max.shop.dto.request.UserListCriteriaDto;
import com.max.shop.entity.User;
import com.max.shop.exception.EntityNotFountException;
import com.max.shop.repository.UserRepository;
import com.max.shop.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoUserService {

    private final MapperService conversionService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserProfileDto saveUser(UserFormDto userForm) {

        User user = Optional.ofNullable(userForm.getId())
            .flatMap(userRepository::findById)
            .orElseGet(User::new);

        conversionService.update(userForm, user);
        if (userForm.getNewPassword() != null) {
            user.setPassword(passwordEncoder.encode(userForm.getNewPassword()));
        }

        return conversionService.convert(userRepository.save(user), UserProfileDto.class);
    }

    public UserProfileDto findUserById(Long id) {
        return userRepository.findById(id)
            .map(user -> conversionService.convert(user, UserProfileDto.class))
            .orElseThrow(() -> new EntityNotFountException("User"));
    }

    public Page<UserProfileInfoDto> listUsers(UserListCriteriaDto userCriteria, Pageable pageable) {
        Page<User> users = userRepository.findAll(UserSpecification.buildListFilter(userCriteria), pageable);
        List<UserProfileInfoDto> profilesList =
            conversionService.convertList(users.getContent(), UserProfileInfoDto.class);
        return new PageImpl<>(profilesList, pageable, users.getTotalElements());
    }
}
