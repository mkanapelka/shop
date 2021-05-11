package com.example.shop.generator;

import com.example.shop.constans.Constans;
import com.example.shop.entity.Category;
import com.example.shop.generator.parent.Generator;
import com.example.shop.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CategoryGenerator extends Generator {

    private CategoryRepository categoryRepository;
    private CharacteristicGenerator characteristicGenerator;

    public CategoryGenerator(CategoryRepository categoryRepository, CharacteristicGenerator characteristicGenerator) {
        this.categoryRepository = categoryRepository;
        this.characteristicGenerator = characteristicGenerator;
    }

    public void addCategory() throws IOException {
        List<String> categoryList = readAllLines(Constans.CATEGORY_LIST);
        for (String line : categoryList) {
            Category category = new Category();
            category.setName(line);
//            category.setCharacteristicList(characteristicGenerator.genRandomCharacteristic());
            categoryRepository.save(category);
        }
    }


}
