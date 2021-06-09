package com.max.shop.repository;

import com.max.shop.entity.SubProductCategory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface SubProductCategoryRepository extends JpaRepository<SubProductCategory, Long>,
    JpaSpecificationExecutor<SubProductCategory> {

    @EntityGraph(attributePaths = "characteristics")
    List<SubProductCategory> findTop5ByIdIsNotNull();
}
