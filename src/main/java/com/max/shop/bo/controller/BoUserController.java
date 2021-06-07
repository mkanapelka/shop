package com.max.shop.bo.controller;

import com.max.shop.bo.service.BoUserService;
import com.max.shop.constans.Constants;
import com.max.shop.dto.UserProfileDto;
import com.max.shop.dto.UserProfileListDto;
import com.max.shop.dto.request.UserFormDto;
import com.max.shop.dto.request.UserListCriteriaDto;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class BoUserController {

    private final BoUserService userService;

    @GetMapping("/{id}")
    public UserProfileDto showUserInfo(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public ResponseEntity<UserProfileDto> saveUser(@RequestBody @Valid UserFormDto userForm) {
        val user = userService.saveUser(userForm);
        if (userForm.getId() == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @GetMapping
    public Page<UserProfileListDto> listUsers(
        @PageableDefault(size = Constants.DEFAULT_PAGE_SIZE) Pageable pageable,
        UserListCriteriaDto userCriteria) {
        return userService.listUsers(userCriteria, pageable);
    }

}
