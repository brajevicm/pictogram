package com.pictogram.pictogram.storage;

/**
 * Project: pictogram
 * Date: 17-Jan-18
 * Author: Milos Brajevic
 * Mail: brajevicms@gmail.com
 */
//@ConfigurationProperties("storage")
public class StorageProperties {
  private String location = "/opt/uploads";

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
