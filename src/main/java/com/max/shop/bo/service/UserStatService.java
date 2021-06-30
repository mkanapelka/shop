package com.max.shop.bo.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.UserStatDto;
import com.max.shop.dto.request.UserStatCriteriaDto;
import com.max.shop.entity.stat.UserStat;
import com.max.shop.repository.UserStatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.max.shop.bo.specification.UserStatSpecification.buildListFilterForUserStat;

@Service
@RequiredArgsConstructor
public class UserStatService {

    private final UserStatRepository userStatRepository;
    private final MapperService conversionService;

    public Page<UserStatDto> listUserStat(UserStatCriteriaDto criteriaDto, Pageable pageable){
        Page<UserStat> users =
                userStatRepository.findAll(buildListFilterForUserStat(criteriaDto), pageable);
        List<UserStatDto> userStatList =
                conversionService.convertList(users.getContent(), UserStatDto.class);
        return new PageImpl<>(userStatList, pageable, users.getTotalElements());
    }
}
