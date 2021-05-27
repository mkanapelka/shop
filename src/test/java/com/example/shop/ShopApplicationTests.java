package com.example.shop;

import com.example.shop.dto.UserProfileDto;
import com.example.shop.entity.User;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
class ShopApplicationTests {

    @Autowired
    private ConversionService conversionService;

    @Test
    void contextLoads() {

        val user = User.builder()
            .username("user2")
            .email("usr@mail.com")
            .firstName("Homer")
            .lastName("Simpson")
            .build();

        val dto = conversionService.convert(user, UserProfileDto.class);

        assertThat(dto)
            .extracting(UserProfileDto::getUsername,
                UserProfileDto::getEmail,
                UserProfileDto::getFirstName,
                UserProfileDto::getLastName)
            .containsExactly("user2", "usr@mail.com", "Homer", "Simpson");
    }

}
