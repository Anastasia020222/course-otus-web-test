package org.example.common;

import com.google.inject.Inject;
import org.example.configuration.AbsDriverPageObject;
import org.example.configuration.DriverControlLaunch;

public class OpenDriverUrl extends AbsDriverPageObject {

  @Inject
  public OpenDriverUrl(DriverControlLaunch driverControlLaunch) {
    super(driverControlLaunch);
  }

  private String url = System.getProperty("webdriver.base.url", "https://otus.ru");

  public void openUrl(String path) {
    driver.get(url + path);
  }
}
