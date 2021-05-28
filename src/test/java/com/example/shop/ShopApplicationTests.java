package com.example.shop;

import com.example.shop.dto.UserProfileDto;
import com.example.shop.entity.User;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
class ShopApplicationTests {

    @Autowired
    private ConversionService conversionService;

    @Test
    void contextLoads() {

        User user = User.builder()
                .name("user2")
                .email("usr@mail.com")
                .firstName("Homer")
                .lastName("Simpson")
                .build();


        UserProfileDto dto = conversionService.convert(user, UserProfileDto.class);

        assertThat(dto)
                .extracting(UserProfileDto::getName,
                        UserProfileDto::getEmail,
                        UserProfileDto::getFirstName,
                        UserProfileDto::getLastName)
                .containsExactly("user2", "usr@mail.com", "Homer", "Simpson");
    }

    @Test
    void genPassword(){
        String str1 = "maks";
        String str2 = "maks";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode(str1));
        assertEquals(str1, str2);
    }

}