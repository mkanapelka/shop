package com.max.shop.repository;

import com.max.shop.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT COUNT(id) FROM Image ")
    Long countImage();

}
