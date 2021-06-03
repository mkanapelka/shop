package com.max.shop.controller;

import com.max.shop.constans.Constants;
import com.max.shop.dto.UserProfileDto;
import com.max.shop.dto.request.UserListCriteriaDto;
import com.max.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDto> showUserInfo(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    //TODO it's a part of back office logic. may be should be moved to other controller
    @GetMapping
    public ResponseEntity<Page<UserProfileDto>> listUsers(
        @PageableDefault(size = Constants.DEFAULT_PAGE_SIZE) Pageable pageable,
        UserListCriteriaDto userCriteria) {
        Page<UserProfileDto> userPage = userService.listUsers(userCriteria, pageable);
        return new ResponseEntity<>(userPage, HttpStatus.OK);
    }

}
