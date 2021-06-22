package com.max.shop.initializer;

import lombok.val;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;

@TestConfiguration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        val dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(Postgres.container.getJdbcUrl());
        dataSourceBuilder.username(Postgres.container.getUsername());
        dataSourceBuilder.password(Postgres.container.getPassword());
        return dataSourceBuilder.build();
    }
}
