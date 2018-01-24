package com.pictogram.pictogram.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Configuration
@EnableTransactionManagement
@PropertySource("src/main/resources/application.properties")
@ComponentScan
public class PersistenceConfig {

  @Autowired
  private Environment env;

  @Bean
  public DataSource dataSource() {
    final DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    driverManagerDataSource.setUrl(env.getProperty("spring.datasource.url"));
    driverManagerDataSource.setUsername(env.getProperty("spring.datasource.username"));
    driverManagerDataSource.setPassword(env.getProperty("spring.datasource.password"));

    return driverManagerDataSource;
  }

}
