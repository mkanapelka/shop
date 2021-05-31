package com.example.shop.service;

import com.example.shop.dto.UserProfileDto;
import com.example.shop.dto.request.UserListCriteriaDto;
import com.example.shop.entity.User;
import com.example.shop.exception.UserNotFoundException;
import com.example.shop.repository.UserRepository;
import com.example.shop.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ConversionService conversionService;
    private final UserRepository userRepository;

    public User findUserByName(String name){
        return userRepository.findByName(name).orElseThrow(UserNotFoundException::new);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public Page<UserProfileDto> findAll(/*TODO add filters*/ Pageable pageable) {
        //        return userRepository.findAll(pageable);
        return null;
    }

    public Page<UserProfileDto> listUsers(UserListCriteriaDto userCriteria, Pageable pageable) {
        Page<User> users = userRepository.findAll(UserSpecification.buildListFilter(userCriteria), pageable);
        List<UserProfileDto> profilesList =
            //            conversionService.convert(users.getContent(), (Class<List<UserProfileDto>>)(Class<?>)List.class);
            conversionService.convert(users.getContent(), castClass(List.class));
        return new PageImpl<>(profilesList, pageable, users.getTotalElements());
    }

    //TODO if it works extract to some util class
    @SuppressWarnings("unchecked")
    public static <T> Class<T> castClass(Class<?> aClass) {
        return (Class<T>) aClass;
    }
}
