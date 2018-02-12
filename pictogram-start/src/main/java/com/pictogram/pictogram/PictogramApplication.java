package com.pictogram.pictogram;

import com.pictogram.pictogram.config.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@EnableAutoConfiguration
@SpringBootApplication
@Import({RestConfig.class, RepositoryConfig.class, CoreConfig.class, ServiceConfig.class, ModelConfig.class})
public class PictogramApplication {


  public static void main(String[] args) {
    SpringApplication.run(PictogramApplication.class, args);
  }
}
