package com.max.shop.repository;

import com.max.shop.dto.request.SubCategoryCriteriaDto;
import com.max.shop.initializer.Postgres;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.max.shop.specification.SubCategorySpecification.buildListFilter;
import static com.max.shop.specification.SubCategorySpecification.fetchCharacteristics;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ContextConfiguration(initializers = {
    Postgres.Initializer.class
})
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class SubProductCategoryRepositoryTest {

    @Autowired
    private SubProductCategoryRepository testSubject;

    @BeforeAll
    static void init() {
        Postgres.container.start();
    }

    @Test
    public void shouldFetchCharacteristicsWithSpecification() {

        val result = testSubject.findAll(
            buildListFilter(new SubCategoryCriteriaDto())
                .and(fetchCharacteristics()), PageRequest.of(0, 10));

        assertThat(result.getContent()).asList().isNotEmpty();
    }


}
