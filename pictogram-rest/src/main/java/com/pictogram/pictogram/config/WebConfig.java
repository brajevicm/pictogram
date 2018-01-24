package com.pictogram.pictogram.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Project: pictogram
 * Date: 13-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS");
  }
}
