package com.example.shop.generator;

import com.example.shop.constans.Constans;
import com.example.shop.entity.Characteristic;
import com.example.shop.generator.parent.Generator;
import com.example.shop.repository.CharacteristicRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class CharacteristicGenerator extends Generator {

    private CharacteristicRepository characteristicRepository;

    public CharacteristicGenerator(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    public void addCharacteristic() throws IOException {
        List<String> characteristicsList = readAllLines(Constans.CHARACTERISTIC_LIST);
        for (String line : characteristicsList) {
            Characteristic characteristic = new Characteristic();
            characteristic.setName(line);
            characteristicRepository.save(characteristic);
        }
    }

    //todo: пока не трогать
    public List<Characteristic> genRandomCharacteristic() throws IOException {

        List<String> lineList = readAllLines(Constans.CHARACTERISTIC_LIST);
        List<Characteristic> characteristicList = new ArrayList<>(3);
        Random random = new Random();

        while (characteristicList.size() <= 3) {
            Characteristic characteristic = new Characteristic();
            characteristic.setName(lineList.get(random.nextInt(10)));
            characteristicList.add(characteristic);
        }
        return characteristicList;
    }

}
