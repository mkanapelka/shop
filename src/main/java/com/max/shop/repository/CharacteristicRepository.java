package com.max.shop.repository;

import com.max.shop.entity.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
}
