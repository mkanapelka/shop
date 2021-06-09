package com.max.shop.repository;

import com.max.shop.entity.SubProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;

public interface SubProductCategoryRepository extends JpaRepository<SubProductCategory, Long>,
        JpaSpecificationExecutor<SubProductCategory> {

    @Override
    @EntityGraph(value = "SubCategory.characteristics")
    Page<SubProductCategory> findAll(Specification<SubProductCategory> specification, Pageable pageable);
}

