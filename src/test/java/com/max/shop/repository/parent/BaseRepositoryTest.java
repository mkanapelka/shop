package com.max.shop.repository.parent;

import com.max.shop.initializer.Postgres;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//@Sql("/sql/data.sql")
@DataJpaTest
@ContextConfiguration(initializers = {
    Postgres.Initializer.class
})
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public abstract class BaseRepositoryTest {

    @BeforeAll
    static void init() {
        Postgres.start();
    }
}
