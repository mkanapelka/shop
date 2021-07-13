package com.max.shop.bo.controller;

import com.max.shop.bo.security.jwt.JwtTokenProvider;
import com.max.shop.bo.service.BoUserService;
import com.max.shop.constans.Constants;
import com.max.shop.dto.UserPasswordDto;
import com.max.shop.dto.UserProfileDto;
import com.max.shop.dto.UserProfileInfoDto;
import com.max.shop.dto.request.UserFormDto;
import com.max.shop.dto.request.UserListCriteriaDto;
import com.max.shop.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class BoUserController {

    private final BoUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

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
    public Page<UserProfileInfoDto> listUsers(
            @PageableDefault(size = Constants.DEFAULT_PAGE_SIZE) Pageable pageable,
            UserListCriteriaDto userCriteria) {
        return userService.listUsers(userCriteria, pageable);
    }

    @PostMapping("/login")
    public ResponseEntity loginAdmin(@RequestBody UserPasswordDto userPasswordDto) {

        try {
            String username = userPasswordDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, userPasswordDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}
