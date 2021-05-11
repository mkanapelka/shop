package com.example.shop.generator;

import com.example.shop.constans.*;
import com.example.shop.entity.Product;
import com.example.shop.generator.parent.Generator;
import com.example.shop.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Component
public class ProductGenerator extends Generator {

    private ProductRepository productRepository;

    public ProductGenerator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void allProductGeneration() {
        try {
            generationMasculineProduct();
            generationFeminineProduct();
            generationNeuterProduct();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generationMasculineProduct() throws IOException {

        List<String> adjectiveMasculine = readAllLines(Constans.MASCULINE_ADJECTIVE_PATH);
        List<String> nounMasculine = readAllLines(Constans.MASCULINE_NOUN_PATH);

        Random random = new Random();

        for (int i = 0; i < Constans.M_SIZE; i++) {
            Product product = new Product();

            product.setName(adjectiveMasculine.get(random.nextInt(100))
                    + " " + nounMasculine.get(random.nextInt(100)));

            product.setCost(random.nextDouble() * 100);

            product.setDescription(adjectiveMasculine.get(random.nextInt(100))
                    + " " + adjectiveMasculine.get(random.nextInt(100))
                    + " " + adjectiveMasculine.get(random.nextInt(100)));

            product.setQuantity(random.nextInt(10));
            productRepository.save(product);
        }
    }

    public void generationFeminineProduct() throws IOException {

        List<String> adjectiveFeminine = readAllLines(Constans.FEMININE_ADJECTIVE_PATH);
        List<String> nounFeminine = readAllLines(Constans.FEMININE_NOUN_PATH);

        Random random = new Random();

        for (int i = 0; i < Constans.F_SIZE; i++) {
            Product product = new Product();

            product.setName(adjectiveFeminine.get(random.nextInt(100))
                    + " " + nounFeminine.get(random.nextInt(100)));

            product.setCost(random.nextDouble() * 100);

            product.setDescription(adjectiveFeminine.get(random.nextInt(100))
                    + " " + adjectiveFeminine.get(random.nextInt(100))
                    + " " + adjectiveFeminine.get(random.nextInt(100)));

            product.setQuantity(random.nextInt(10));
            productRepository.save(product);
        }
    }

    public void generationNeuterProduct() throws IOException {

        List<String> adjectiveNeuter = readAllLines(Constans.NEUTER_ADJECTIVE_PATH);
        List<String> nounNeuter = readAllLines(Constans.NEUTER_NOUN_PATH);

        Random random = new Random();

        for (int i = 0; i < Constans.N_SIZE; i++) {
            Product product = new Product();

            product.setName(adjectiveNeuter.get(random.nextInt(100))
                    + " " + nounNeuter.get(random.nextInt(100)));

            product.setCost(random.nextDouble() * 100);

            product.setDescription(adjectiveNeuter.get(random.nextInt(100))
                    + " " + adjectiveNeuter.get(random.nextInt(100))
                    + " " + adjectiveNeuter.get(random.nextInt(100)));

            product.setQuantity(random.nextInt(10));
            productRepository.save(product);
        }
    }
}
