package com.max.shop.repository;

import com.max.shop.dto.request.CategoryCriteriaDto;
import com.max.shop.dto.request.SubCategoryCriteriaDto;
import com.max.shop.repository.parent.BaseRepositoryTest;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;


import static com.max.shop.specification.ProductCategorySpecification.buildListFilter;
import static com.max.shop.specification.ProductCategorySpecification.fetchSubcategories;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Sql("/sql/data.sql")
class CategoryRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void shouldFetchSubCategoriesWithSpecification() {

        val result = productCategoryRepository.findAll(
                buildListFilter(new CategoryCriteriaDto())
                        .and(fetchSubcategories()), PageRequest.of(0, 10));

        assertThat(result.getContent()).asList().isNotEmpty();
    }
}