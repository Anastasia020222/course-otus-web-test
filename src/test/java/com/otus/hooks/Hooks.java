package com.otus.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import org.example.configuration.DriverControlLaunch;
import org.openqa.selenium.WebDriver;

public class Hooks {

  @Inject
  public DriverControlLaunch driverControlLaunch;

  @After
  public void close() {
    WebDriver driver = driverControlLaunch.getDriver();
    if (driver != null) {
      driver.quit();
    }
  }
}
