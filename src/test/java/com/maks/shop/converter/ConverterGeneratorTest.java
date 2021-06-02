package com.maks.shop.converter;

import com.maks.shop.IntegrationTestBase;
import com.maks.shop.dto.ProductDto;
import com.maks.shop.dto.UserProfileDto;
import com.maks.shop.entity.Product;
import com.maks.shop.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ConverterGeneratorTest{

    @Autowired
    private ConversionService conversionService;

    @Test
    void userConverterTest() {

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
    void productConverterTest() {

        Product product = Product.builder()
                .name("борщ")
                .cost(1200)
                .quantity(7)
                .vendorCode("AL-123456789")
                .description("чёткий и красный")
                .build();


        ProductDto dto = conversionService.convert(product, ProductDto.class);

        assertThat(dto)
                .extracting(ProductDto::getName,
                        ProductDto::getCost,
                        ProductDto::getQuantity,
                        ProductDto::getVendorCode,
                        ProductDto::getDescription)
                .containsExactly("борщ", 1200,7, "AL-123456789", "чёткий и красный");
    }
}
