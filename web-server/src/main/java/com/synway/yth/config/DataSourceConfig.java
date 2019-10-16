package com.synway.yth.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.WebApplicationInitializer;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceConfig  {

    @Autowired
    Environment environment;

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }


    public DruidDataSource getDruidDataSource() throws Exception{
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setInitialSize(Integer.parseInt(environment.getProperty("spring.datasource.initialSize")));
        dataSource.setMinIdle(Integer.parseInt(environment.getProperty("spring.datasource.minIdle")));
        dataSource.setMaxActive(Integer.parseInt(environment.getProperty("spring.datasource.maxActive")));
        dataSource.setMaxWait(Long.parseLong(environment.getProperty("spring.datasource.maxWait")));
        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(environment.getProperty("spring.datasource.timeBetweenEvictionRunsMillis")));
        dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(environment.getProperty("spring.datasource.minEvictableIdleTimeMillis")));
        dataSource.setTestWhileIdle(Boolean.parseBoolean(environment.getProperty("spring.datasource.testWhileIdle")));
        dataSource.setTestOnBorrow(Boolean.parseBoolean(environment.getProperty("spring.datasource.testOnBorrow")));
        dataSource.setTestOnReturn(Boolean.parseBoolean(environment.getProperty("spring.datasource.testOnReturn")));
        dataSource.setFilters(environment.getProperty("spring.datasource.filters"));
        Properties properties = new Properties();
        properties.setProperty("spring.datasource.connectionProperties",environment.getProperty("spring.datasource.connectionProperties"));
        dataSource.setConnectProperties(properties);
        dataSource.setUrl(environment.getProperty("spring.datasource.oracle.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.oracle.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.oracle.password"));
        return dataSource;
    }


    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+"mapper/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource(ResourcePatternResolver.CLASSPATH_URL_PREFIX+"mybatis-config.xml"));
//      sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis-config.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("oracleDruidDataSource")
    @Primary
    public DruidDataSource oracleDruidDataSource() throws Exception{
        DruidDataSource dataSource = getDruidDataSource();
        dataSource.setPoolPreparedStatements(Boolean.parseBoolean(environment.getProperty("spring.datasource.poolPreparedStatements")));
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(environment.getProperty("spring.datasource.maxPoolPreparedStatementPerConnectionSize")));
        dataSource.setValidationQuery(environment.getProperty("spring.datasource.validationQuery"));
        return dataSource;
    }

    @Bean("oracleSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("oracleDruidDataSource") DataSource dataSource) throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory(dataSource);
        return sqlSessionFactory;
    }

    @Bean("oracleDruidTransaction")
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("oracleDruidDataSource") DataSource dataSource){
       return new DataSourceTransactionManager(dataSource);
    }

    @Bean("oracleSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("oracleSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
