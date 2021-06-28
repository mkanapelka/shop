package com.max.shop.aspect;

import com.max.shop.dto.ProductDto;
import com.max.shop.entity.UserStat;
import com.max.shop.repository.UserStatRepository;
import com.max.shop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Aspect
@Component
@RequiredArgsConstructor
public class UserStatAspect {

    private final UserStatRepository userStatRepository;


    @AfterReturning(pointcut = "@annotation(com.max.shop.aspect.UserStatistics)", returning = "result")
    public void addUserStatisticAfterReturning(JoinPoint joinPoint, Object result) {
        Long userId = SecurityUtil.getUserId();
        ProductDto productDto = (ProductDto) result;
        Long productId = productDto.getId();
        LocalDate date = LocalDate.now();
        UserStat userStat = userStatRepository.findByUserIdAndProductIdAndDateViews(userId, productId, date)
                .orElseGet(UserStat::new);
        userStat.setUserId(userId);
        userStat.setProductId(productId);
        if (userStat.getQuantityViews() == null){
            userStat.setQuantityViews(1);
        } else {
        userStat.setQuantityViews(userStat.getQuantityViews() + 1);
        }
        userStat.setDateViews(date);
        userStatRepository.save(userStat);
    }

}
