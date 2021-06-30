package com.max.shop.bo.controller;

import com.max.shop.bo.service.OrderStatService;
import com.max.shop.constans.Constants;
import com.max.shop.dto.OrderStatDto;
import com.max.shop.dto.request.OrderStatCriteriaDto;
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
@RequestMapping("/api/admin/orderStatistics")
@RequiredArgsConstructor
public class OrderStatController {

    private final OrderStatService orderStatService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<OrderStatDto> listUserStatistic(OrderStatCriteriaDto criteriaDto,
                                                @PageableDefault(size = Constants.DEFAULT_PAGE_SIZE) Pageable pageable){
        return orderStatService.listOrderStat(criteriaDto,pageable);
    }
}
