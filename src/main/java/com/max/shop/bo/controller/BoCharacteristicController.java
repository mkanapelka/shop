package com.max.shop.bo.controller;

import com.max.shop.bo.service.BoCharacteristicService;
import com.max.shop.dto.CharacteristicDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/characteristics")
@RequiredArgsConstructor
public class BoCharacteristicController {

    private final BoCharacteristicService characteristicService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void saveCharacteristic(@RequestBody CharacteristicDto characteristicDto){
        characteristicService.saveCharacteristic(characteristicDto);
    }
}
