package com.max.shop.aspect.handler;

import com.max.shop.aspect.handler.Handler;
import com.max.shop.dto.ProductDto;
import com.max.shop.entity.stat.UserStat;
import com.max.shop.repository.UserStatRepository;
import com.max.shop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ProductViewHandler implements Handler {

    private final UserStatRepository userStatRepository;

    @Override
    public Object exist(ProceedingJoinPoint joinPoint) throws Throwable {
        Long userId = SecurityUtil.getUserId();
        ProductDto productDto = (ProductDto) joinPoint.proceed();
        Long productId = productDto.getId();
        LocalDate date = LocalDate.now();
        UserStat userStat = userStatRepository.findByUserIdAndProductIdAndDateViews(userId, productId, date)
                .orElseGet(UserStat::new);
        userStat.setUserId(userId);
        userStat.setProductId(productId);
        if (userStat.getQuantityViews() == null) {
            userStat.setQuantityViews(1);
        } else {
            userStat.setQuantityViews(userStat.getQuantityViews() + 1);
        }
        userStat.setDateViews(date);
        userStatRepository.save(userStat);
        return productDto;
    }
}
