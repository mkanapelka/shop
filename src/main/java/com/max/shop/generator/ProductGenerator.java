package com.max.shop.generator;

import com.max.shop.constans.Constants;
import com.max.shop.entity.Product;
import com.max.shop.entity.SubProductCategory;
import com.max.shop.repository.ProductRepository;
import com.max.shop.repository.SubProductCategoryRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
public class ProductGenerator {

    private ProductRepository productRepository;
    private SubProductCategoryRepository subProductCategoryRepository;

    public ProductGenerator(ProductRepository productRepository, SubProductCategoryRepository subProductCategoryRepository) {
        this.productRepository = productRepository;
        this.subProductCategoryRepository = subProductCategoryRepository;
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

        List<String> adjectiveMasculine = readAllLines(Constants.MASCULINE_ADJECTIVE_PATH);
        List<String> nounMasculine = readAllLines(Constants.MASCULINE_NOUN_PATH);
        saveProduct(adjectiveMasculine, nounMasculine, Constants.M_SIZE);
    }

    public void generationFeminineProduct() throws IOException {

        List<String> adjectiveFeminine = readAllLines(Constants.FEMININE_ADJECTIVE_PATH);
        List<String> nounFeminine = readAllLines(Constants.FEMININE_NOUN_PATH);
        saveProduct(adjectiveFeminine, nounFeminine, Constants.F_SIZE);
    }

    public void generationNeuterProduct() throws IOException {

        List<String> adjectiveNeuter = readAllLines(Constants.NEUTER_ADJECTIVE_PATH);
        List<String> nounNeuter = readAllLines(Constants.NEUTER_NOUN_PATH);
        saveProduct(adjectiveNeuter, nounNeuter, Constants.N_SIZE);
    }


    //-------------------------------------------------------
    public List<String> readAllLines(String file) throws IOException {
        return Files.readAllLines(Paths.get(file));
    }


    public SubProductCategory addRandomSubProductCategory() {

        Random random = new Random();
        Long randId = (long) random.nextInt(19);

        return subProductCategoryRepository.findSubProductCategoryById(randId);
    }

    public String genVendorCode() {
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

            product.setSubProductCategory(addRandomSubProductCategory());
            product.setQuantity(random.nextInt(30));
            product.setVendorCode(genVendorCode());
            product.setCreated(LocalDateTime.now());
            product.setUpdated(LocalDateTime.now());

            try {
                productRepository.save(product);
            } catch (Exception exception) {
                size = size - i;
                saveProduct(adjective, noun, size);
            }

        }
    }
}
