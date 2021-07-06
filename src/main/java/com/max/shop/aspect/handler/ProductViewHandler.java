package com.max.shop.aspect.handler;

import com.max.shop.aspect.StatisticsType;
import com.max.shop.converter.MapperService;
import com.max.shop.dto.ProductDto;
import com.max.shop.entity.stat.UserStat;
import com.max.shop.repository.UserStatRepository;
import com.max.shop.service.ProductService;
import com.max.shop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ProductViewHandler implements Handler {

    private final UserStatRepository userStatRepository;
    private final ProductService productService;
    private final MapperService conversionService;

    @Override
    public void writeStatistics(Object product) {
        Long userId = SecurityUtil.getUserId();
        ProductDto productDto = (ProductDto) product;
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
    }

    @Override public boolean supports(StatisticsType type) {
        return type == StatisticsType.PRODUCT_VIEW;
    }

    @Override
    public Object getResultByArg(Object object) {
        return conversionService.convert(productService.findOneById((Long) object), ProductDto.class);
    }
}
