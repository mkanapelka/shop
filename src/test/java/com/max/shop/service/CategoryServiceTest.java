package com.max.shop.service;

import com.max.shop.repository.parent.BaseRepositoryTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class CategoryServiceTest extends BaseRepositoryTest {

    private final ProductCategoryService productCategoryService;

    @Test
    public void testFindCategoryById(){

    }
}
