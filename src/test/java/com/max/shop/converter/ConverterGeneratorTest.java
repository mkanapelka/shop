package com.max.shop.converter;

import com.max.shop.dto.ProductDto;
import com.max.shop.dto.UserProfileListDto;
import com.max.shop.entity.Product;
import com.max.shop.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
public class ConverterGeneratorTest {

    @Autowired
    private MapperService conversionService;

    @Test
    void shouldConvertBothUsersAndProductsLists() {

        List<User> users = new ArrayList<>(Collections.singletonList(User.builder()
            .name("user2")
            .email("usr@mail.com")
            .firstName("Homer")
            .lastName("Simpson")
            .build()));

        List<Product> products = new ArrayList<>(Collections.singletonList(Product.builder()
            .name("борщ")
            .cost(1200)
            .quantity(7)
            .vendorCode("AL-123456789")
            .description("чёткий и красный")
            .build()));

        List<UserProfileListDto> usersDtoList = conversionService.convertList(users, UserProfileListDto.class);
        assertThat(usersDtoList).hasSize(1);

        List<ProductDto> productsDtoList = conversionService.convertList(products, ProductDto.class);
        assertThat(productsDtoList).hasSize(1);
    }

    @Test
    void userConverterTest() {

        User user = User.builder()
            .name("user2")
            .email("usr@mail.com")
            .firstName("Homer")
            .lastName("Simpson")
            .build();


        UserProfileListDto dto = conversionService.convert(user, UserProfileListDto.class);

        assertThat(dto)
            .extracting(UserProfileListDto::getName,
                UserProfileListDto::getEmail,
                UserProfileListDto::getFirstName,
                UserProfileListDto::getLastName)
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
            .containsExactly("борщ", 1200, 7, "AL-123456789", "чёткий и красный");
    }
}