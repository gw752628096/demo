package com.spring.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.Collections;

/**
 * DatabaseConfiguration
 *
 * @author gw
 * @date 2021/12/02
 */
@Configuration
public class DataSourceConfiguration {

    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource masterDataSource(@Value("${spring.datasource.driver}") String driver,
                                            @Value("${spring.datasource.url}") String url,
                                            @Value("${spring.datasource.username}") String username,
                                            @Value("${spring.datasource.password}") String password) throws SQLException {
        return buildDataSource(driver, url, username, password);
    }

    private DruidDataSource buildDataSource(String driver, String url, String username, String password) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setValidationQuery("select 1 from dual");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setFilters("stat");
        dataSource.setConnectionInitSqls(Collections.singletonList("set names utf8mb4;"));
        return dataSource;
    }
}
