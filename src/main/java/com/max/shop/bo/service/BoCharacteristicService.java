package com.max.shop.bo.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.CharacteristicDto;
import com.max.shop.entity.Characteristic;
import com.max.shop.repository.CharacteristicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoCharacteristicService {

    private final CharacteristicRepository characteristicRepository;
    private final MapperService conversionService;

    public void saveCharacteristic(CharacteristicDto characteristicDto){
        characteristicRepository.save(conversionService.convert(characteristicDto, Characteristic.class));
    }

}
