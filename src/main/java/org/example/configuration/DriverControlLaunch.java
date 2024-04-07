package org.example.configuration;

import io.cucumber.guice.ScenarioScoped;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

@Getter
@Setter
@ScenarioScoped
public class DriverControlLaunch {

  private String browser;

  private WebDriver driver;

  private void initDriver() {
    if (driver == null) {
      driver = new WebDriverFactory().getDriver(browser);
    }
  }

  public WebDriver getDriver() {
    initDriver();
    return driver;
  }
}
