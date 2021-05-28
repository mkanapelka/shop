package com.example.shop.controller;

import com.example.shop.dto.UserProfileDto;
import com.example.shop.entity.User;
import com.example.shop.service.UserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {


    private final ConversionService conversionService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public UserController(ConversionService conversionService, UserService userService, AuthenticationManager authenticationManager) {
        this.conversionService = conversionService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/")
    public ResponseEntity<String> showStartPage(){
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @GetMapping("users/one/{id}")
    public ResponseEntity<User> showUserInfo(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("users/all")
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> userList = userService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
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
