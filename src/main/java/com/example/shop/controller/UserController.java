package com.example.shop.controller;

import com.example.shop.dto.UserProfileDto;
import com.example.shop.dto.request.UserListCriteriaDto;
import com.example.shop.entity.User;
import com.example.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.shop.constans.Constants.DEFAULT_PAGE_SIZE;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    //TODO move somewhere else
    //    @GetMapping("/")
    //    public ResponseEntity<String> showStartPage(){
    //        return new ResponseEntity<>("Hello", HttpStatus.OK);
    //    }

    @GetMapping("/{id}")
    public ResponseEntity<User> showUserInfo(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //TODO it's a part of back office logic. may be should be moved to other controller
    @GetMapping
    public ResponseEntity<Page<UserProfileDto>> listUsers(
        @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable,
        UserListCriteriaDto userCriteria) {
        Page<UserProfileDto> userPage = userService.listUsers(userCriteria, pageable);
        return new ResponseEntity<>(userPage, HttpStatus.OK);
    }


    //    @PostMapping("/registration")
    //    public ResponseEntity<UserDto> registrationUser(@Valid @RequestBody UserRegistryDto userRegistryDto) {
    //        User user = UserConvertForRegistry.convertToModel(userRegistryDto);
    //        userService.saveUser(user);
    //        return new ResponseEntity<>(UserConverter.convertToDto(user), HttpStatus.OK);
    //    }
    //

    //    @PostMapping("/login")
    //    public ResponseEntity<UserProfileDto> loginUser(@Valid @RequestBody UserProfileDto userProfileDto) {
    //        authenticationManager
    //                .authenticate(new UsernamePasswordAuthenticationToken(userProfileDto.getName(), userProfileDto.getPassword()));
    //        User user = userService.findUserByName(userProfileDto.getName());
    //        return new ResponseEntity<>(conversionService.convert(user, UserProfileDto.class), HttpStatus.OK);
    //    }
}
