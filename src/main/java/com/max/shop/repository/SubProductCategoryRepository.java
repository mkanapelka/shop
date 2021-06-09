package com.max.shop.repository;

import com.max.shop.entity.SubProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SubProductCategoryRepository extends JpaRepository<SubProductCategory, Long>,
        JpaSpecificationExecutor<SubProductCategory> {
}

