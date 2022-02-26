package com.spring.demo.config;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * MyBatisConfiguration
 *
 * @author gw
 * @date 2021/12/02
 */
@Configuration
@MapperScan(basePackages = "com.spring.demo.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisConfiguration {

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resourcePatternResolver.getResources("classpath*:sqlmap/*SqlMap.xml"));
        return sessionFactory.getObject();
    }

    @Bean(name = "sqlSessionTemplate")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.SIMPLE);
    }
}
