package com.mengli.mockapp.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.transaction.PlatformTransactionManager

import javax.activation.DataSource


/**
 * Created by mlhuang on 8/10/16.
 */

@Configuration
class DataConfiguration {
    @Value('${spring.datasource.driverClassName')
    private String driverClassName

    @Value('${spring.datasource.url}')
    private String url

    @Value('${spring.datasource.username}')
    private String username

    @Value('${spring.datasource.password')
    private String password

    @Bean
    DataSource db2DataSource() {
        def dataSource = new DriverManagerDataSource()
        dataSource.driverClassName = driverClassName
        dataSource.url = url
        dataSource.username = username
        dataSource.password = password
        dataSource
    }

    @Bean
    JdbcTemplate db2JdbcTemplate() {
        new JdbcTemplate(db2DataSource())
    }

    @Bean
    PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager()
        transactionManager.setDataSource(db2DataSource())
        transactionManager
    }

}
