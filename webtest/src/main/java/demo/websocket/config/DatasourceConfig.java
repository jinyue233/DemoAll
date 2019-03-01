/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.websocket.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 *
 * @author sunqinwen
 * @version \: DatasourceConfig.java,v 0.1 2019-03-01 14:57 
 *
 */
@Configuration
@MapperScan(basePackages = "demo.websocket.mapper", sqlSessionFactoryRef = "sqlSession", annotationClass = Mapper.class)
public class DatasourceConfig {

    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DruidDataSource druid = new DruidDataSource();
        druid.setUrl("jdbc:mysql://127.0.0.1:3306/kabii_db?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&autoReconnect=true&zeroDateTimeBehavior=convertToNull");
        druid.setDriverClassName("com.mysql.jdbc.Driver");
        druid.setUsername("root");
        druid.setPassword("sun123456");
        druid.setValidationQuery("SELECT 1");
        druid.setTimeBetweenLogStatsMillis(1000);
        druid.setInitialSize(1);
        druid.setRemoveAbandoned(true);
        druid.setTestOnBorrow(true);
        druid.setTimeBetweenEvictionRunsMillis(60000);
        druid.setQueryTimeout(1);
        druid.setMaxActive(2);
        druid.setMinIdle(2);
        druid.setMaxWait(1000L);
        return druid;
    }


    @Bean(name = "sqlSession")
    public SqlSessionFactory sqlSession(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

}
