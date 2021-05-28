package com.example.shop.generator;

import com.example.shop.constans.Constans;
import com.example.shop.entity.Product;
import com.example.shop.entity.ProductCategory;
import com.example.shop.repository.ProductCategoryRepository;
import com.example.shop.repository.ProductRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ProductGenerator {

    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;

    public ProductGenerator(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
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
        saveProduct(adjectiveMasculine, nounMasculine, Constans.M_SIZE);
    }

    public void generationFeminineProduct() throws IOException {

        List<String> adjectiveFeminine = readAllLines(Constans.FEMININE_ADJECTIVE_PATH);
        List<String> nounFeminine = readAllLines(Constans.FEMININE_NOUN_PATH);
        saveProduct(adjectiveFeminine, nounFeminine, Constans.F_SIZE);
    }

    public void generationNeuterProduct() throws IOException {

        List<String> adjectiveNeuter = readAllLines(Constans.NEUTER_ADJECTIVE_PATH);
        List<String> nounNeuter = readAllLines(Constans.NEUTER_NOUN_PATH);
        saveProduct(adjectiveNeuter, nounNeuter, Constans.N_SIZE);
    }


    //-------------------------------------------------------
    private List<String> readAllLines(String file) throws IOException {
        return Files.readAllLines(Paths.get(file));
    }


    private List<ProductCategory> addRandomProductCategory() {

        List<ProductCategory> productCategoryList = new ArrayList<>(2);
        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            Long randId = (long) random.nextInt(9);
            productCategoryList.add(productCategoryRepository.findProductCategoryById(randId));
        }
        return productCategoryList;
    }

    private String genVendorCode() {
        int min = 65;
        int max = 90;
        int diff = max - min;
        Random random = new Random();
        char prefix1 = (char) (random.nextInt(diff) + min);
        char prefix2 = (char) (random.nextInt(diff) + min);
        String vendor = Character.toString(prefix1) + Character.toString(prefix2) + "-";
        for (int i = 0; i < 16; i++) {
            vendor += random.nextInt(9);
        }
        return vendor;
    }

    @SneakyThrows
    private void saveProduct(List<String> adjective, List<String> noun, int size) {
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            Product product = new Product();

            product.setName(adjective.get(random.nextInt(100))
                    + " " + noun.get(random.nextInt(100)));

            product.setCost(random.nextInt(10000) + 100);

            product.setDescription(adjective.get(random.nextInt(100))
                    + ", " + adjective.get(random.nextInt(100))
                    + ", " + adjective.get(random.nextInt(100)));

            product.setProductCategories(addRandomProductCategory());
            product.setQuantity(random.nextInt(30));
            product.setVendorCode(genVendorCode());
            product.setCreated(LocalDateTime.now());
            product.setUpdated(LocalDateTime.now());
            try {
                productRepository.save(product);
            } catch (Exception exception){
                size = size - i;
                saveProduct(adjective, noun, size);
            }

        }
    }
}
