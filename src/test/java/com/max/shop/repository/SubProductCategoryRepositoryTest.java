package com.max.shop.repository;

import com.max.shop.dto.request.SubCategoryCriteriaDto;
import com.max.shop.repository.parent.BaseRepositoryTest;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import static com.max.shop.specification.SubCategorySpecification.buildListFilter;
import static com.max.shop.specification.SubCategorySpecification.fetchCharacteristics;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SubProductCategoryRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private SubProductCategoryRepository testSubject;

    @Test
    public void shouldFetchCharacteristicsWithSpecification() {

        val result = testSubject.findAll(
            buildListFilter(new SubCategoryCriteriaDto())
                .and(fetchCharacteristics()), PageRequest.of(0, 10));

        assertThat(result.getContent()).asList().isNotEmpty();
    }


}
