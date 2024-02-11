package com.br.naovaipq.dualdbintegrationtest.configdb;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "dboneDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dboneDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dbtwoDataSource")
    @ConfigurationProperties(prefix = "spring.second-datasource")
    public DataSource dbtwoDataSource() {
        return DataSourceBuilder.create().build();
    }

}
