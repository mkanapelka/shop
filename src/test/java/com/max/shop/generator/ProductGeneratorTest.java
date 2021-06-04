package com.max.shop.generator;

import com.max.shop.constans.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductGeneratorTest {

    @Autowired
    ProductGenerator productGenerator;

    @Test
    void genVendorCodeTest(){
        int sizeExpected = 19;
        assertEquals(sizeExpected, productGenerator.genVendorCode().length());
    }

    @Test
    void readAllLinesTest() throws IOException {
        assertNotNull(productGenerator.readAllLines(Constants.MASCULINE_NOUN_PATH));
    }

    @Test
    void addRandomSubProductCategoryTest() {
        int sizeExpected = 2;
        assertNotNull(productGenerator.addRandomSubProductCategory());
    }

}