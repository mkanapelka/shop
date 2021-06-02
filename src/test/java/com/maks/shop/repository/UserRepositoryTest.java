package com.maks.shop.repository;

import com.maks.shop.IntegrationTestBase;
import com.maks.shop.dto.request.UserListCriteriaDto;
import com.maks.shop.entity.User;
import com.maks.shop.specification.UserSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRepositoryTest extends IntegrationTestBase {

    @Autowired
    UserRepository userRepository;

    @Test
    void testFindAllUsersBySpecification() {

        List<User> usersExpected = new ArrayList<>();
        usersExpected.add(
                User.builder()
                        .name("ivan")
                        .firstName("Иванов")
                        .lastName("Иван")
                        .email("ivan@mail.ru")
                        .isActive(true)
                        .build()
        );

        UserListCriteriaDto userCriteria = UserListCriteriaDto.builder()
                .username("ivan")
                .email("ivan@mail.ru")
                .isActive(true)
                .build();

        List<User> userList = userRepository.findAll(UserSpecification.buildListFilter(userCriteria));
        assertEquals(usersExpected.size(), userList.size());
        assertEquals(usersExpected.get(0).getName(), userList.get(0).getName());
        assertEquals(usersExpected.get(0).getFirstName(), userList.get(0).getFirstName());
        assertEquals(usersExpected.get(0).getLastName(), userList.get(0).getLastName());
        assertEquals(usersExpected.get(0).getEmail(), userList.get(0).getEmail());
        assertEquals(usersExpected.get(0).getIsActive(), userList.get(0).getIsActive());

    }
}

