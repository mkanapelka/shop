package com.maks.shop.repository;

import com.maks.shop.entity.SubProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubProductCategoryRepository extends JpaRepository<SubProductCategory,Long> {
    SubProductCategory findSubProductCategoryById(Long id);
}
