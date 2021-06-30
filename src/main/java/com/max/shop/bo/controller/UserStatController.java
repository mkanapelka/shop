package com.max.shop.bo.controller;

import com.max.shop.bo.service.UserStatService;
import com.max.shop.constans.Constants;
import com.max.shop.dto.UserStatDto;
import com.max.shop.dto.request.UserStatCriteriaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/userStatistics")
@RequiredArgsConstructor
public class UserStatController {

    private final UserStatService userStatService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UserStatDto> listUserStatistic(UserStatCriteriaDto userStatCriteriaDto,
                                               @PageableDefault(size = Constants.DEFAULT_PAGE_SIZE) Pageable pageable){
        return userStatService.listUserStat(userStatCriteriaDto,pageable);
    }
}
