package com.pictogram.pictogram.commons.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
@ConfigurationProperties("storage")
public class StorageProperties {
  private String location = "/home/brajevicm/Desktop/";

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
