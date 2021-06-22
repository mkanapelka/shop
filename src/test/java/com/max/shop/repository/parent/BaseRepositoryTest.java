package com.max.shop.repository.parent;

import com.max.shop.initializer.DataSourceConfig;
import com.max.shop.initializer.Postgres;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = {
    Postgres.Initializer.class
})
@Import(DataSourceConfig.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public abstract class BaseRepositoryTest {

    @BeforeAll
    static void init() {
        Postgres.start();
    }
}
