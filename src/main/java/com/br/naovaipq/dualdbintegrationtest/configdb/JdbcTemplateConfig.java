package com.br.naovaipq.dualdbintegrationtest.configdb;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {

    @Bean(name = "dboneJdbcTemplate")
    public JdbcTemplate dboneJdbcTemplate(@Qualifier("dboneDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "dbtwoJdbcTemplate")
    public JdbcTemplate dbtwoJdbcTemplate(@Qualifier("dbtwoDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
