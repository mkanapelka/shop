package com.max.shop.bo.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.OrderStatDto;
import com.max.shop.dto.request.OrderStatCriteriaDto;
import com.max.shop.entity.stat.OrderStat;
import com.max.shop.repository.OrderStatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.max.shop.bo.specification.OrderStatSpecification.buildListFilterForOrderStat;

@Service
@RequiredArgsConstructor
public class OrderStatService {

    private final OrderStatRepository orderStatRepository;
    private final MapperService conversionService;

    public Page<OrderStatDto> listOrderStat(OrderStatCriteriaDto criteriaDto, Pageable pageable){
        Page<OrderStat> orders = orderStatRepository.findAll(buildListFilterForOrderStat(criteriaDto),pageable);
        List<OrderStatDto> orderStatList =
                conversionService.convertList(orders.getContent(), OrderStatDto.class);
        return new PageImpl<>(orderStatList, pageable, orders.getTotalElements());
    }
}
