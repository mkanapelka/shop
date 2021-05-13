package com.example.shop.generator;

import com.example.shop.constans.Constans;
import com.example.shop.entity.Product;
import com.example.shop.entity.Rank;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.RankRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ProductGenerator {

    private ProductRepository productRepository;
    private RankRepository rankRepository;

    public ProductGenerator(ProductRepository productRepository, RankRepository rankRepository) {
        this.productRepository = productRepository;
        this.rankRepository = rankRepository;
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

            product.setRanks(addRandomRank());
            product.setQuantity(random.nextInt(10));
            product.setVendorCode(genVendorCode());
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

            product.setRanks(addRandomRank());
            product.setQuantity(random.nextInt(10));
            product.setVendorCode(genVendorCode());
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

            product.setRanks(addRandomRank());
            product.setQuantity(random.nextInt(10));
            product.setVendorCode(genVendorCode());
            productRepository.save(product);
        }
    }


    //-------------------------------------------------------
    private List<String> readAllLines(String file) throws IOException {
        return Files.readAllLines(Paths.get(file));
    }


    private List<Rank> addRandomRank() {

        List<Rank> rankList = new ArrayList<>(2);
        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            Long randId = (long) random.nextInt(9);
            rankList.add(rankRepository.findRanksById(randId));
        }
        return rankList;
    }

    private String genVendorCode() {
        int min = 41;
        int max = 90;
        int diff = max - min;
        Random random = new Random();
        char prefix1 = (char) (random.nextInt(diff + 1) + min);
        char prefix2 = (char) (random.nextInt(diff + 1) + min);
        return String.valueOf(prefix1 + prefix2
                + random.nextInt(9)
                + random.nextInt(9)
                + random.nextInt(9)
                + random.nextInt(9));
    }
}
