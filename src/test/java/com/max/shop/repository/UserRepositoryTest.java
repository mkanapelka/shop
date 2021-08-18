package com.max.shop.repository;

import com.max.shop.IntegrationTestBase;
import com.max.shop.dto.request.UserListCriteriaDto;
import com.max.shop.entity.User;
import com.max.shop.exception.EntityNotFountException;
import com.max.shop.specification.UserSpecification;
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
                        .username("ivan")
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
        assertEquals(usersExpected.get(0).getUsername(), userList.get(0).getUsername());
        assertEquals(usersExpected.get(0).getFirstName(), userList.get(0).getFirstName());
        assertEquals(usersExpected.get(0).getLastName(), userList.get(0).getLastName());
        assertEquals(usersExpected.get(0).getEmail(), userList.get(0).getEmail());
        assertEquals(usersExpected.get(0).getIsActive(), userList.get(0).getIsActive());

    }

    @Test
    void testFindByName(){
        User userExpected = User.builder()
                .username("ivan")
                .firstName("Иванов")
                .lastName("Иван")
                .email("ivan@mail.ru")
                .isActive(true)
                .build();

        String name = "ivan";
        User user = userRepository.findByUsername(name).orElseThrow(() -> new EntityNotFountException("User"));
        assertEquals(userExpected.getUsername(), user.getUsername());
        assertEquals(userExpected.getEmail(), user.getEmail());
    }
}

