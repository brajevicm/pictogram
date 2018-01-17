package com.pictogram.pictogram;

import com.pictogram.pictogram.commons.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Project: pictogram
 * Date: 12-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class PictogramApplication {

  public static void main(String[] args) {
    SpringApplication.run(PictogramApplication.class, args);
  }
}
