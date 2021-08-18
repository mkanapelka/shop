package com.max.shop;

import com.max.shop.initializer.DataSourceConfig;
import com.max.shop.initializer.Postgres;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
@ContextConfiguration(initializers = {
    Postgres.Initializer.class
})
@Import(DataSourceConfig.class)
@Transactional
@SqlGroup({
    @Sql("/sql/data.sql"),
    @Sql(value = "/sql/cleanup-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
})
public abstract class IntegrationTestBase {

    @BeforeAll
    static void init() {
        Postgres.start();
    }

    @AfterEach
    void cleanUp() {

    }
}
