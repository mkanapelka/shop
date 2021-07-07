package com.max.shop.aspect;

import com.max.shop.dto.ProductDto;
import com.max.shop.entity.User;
import com.max.shop.entity.stat.UserStat;
import com.max.shop.exception.EntityNotFountException;
import com.max.shop.repository.ProductRepository;
import com.max.shop.repository.UserRepository;
import com.max.shop.repository.UserStatRepository;
import com.max.shop.repository.parent.IntegrationTestBase;
import com.max.shop.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql("/sql/data.sql")
class UserStatAspectTest extends IntegrationTestBase {

    @Autowired
    ProductService productService;
    @Autowired
    UserStatRepository userStatRepository;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void updateContext(){
        User user = userRepository.findById(10001L).orElseThrow(() -> new EntityNotFountException("User"));

        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(user,"", user.getAuthorities()));
    }

    @AfterEach
    void cleanUp() {
        userStatRepository.deleteAll();
    }

    @Test
    public void testAddUserStatisticAfterReturning() {

        ProductDto productDto = productService.getProduct(10001L);
        UserStat userStat = userStatRepository.findByProductId(10001L);
        assertEquals(productDto.getId(), userStat.getProductId());
    }
}