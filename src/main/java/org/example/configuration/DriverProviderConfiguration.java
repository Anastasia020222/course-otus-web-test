package org.example.configuration;

import static org.example.common.Constants.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.common.EnvironmentProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverProviderConfiguration {

  private final EnvironmentProperties environmentProperties = new EnvironmentProperties();

  public WebDriver driverProviderConfiguration() {
    ChromeOptions options = new ChromeOptions();
    selectionBrowser();
    options.addArguments("--start-maximized");
    return new ChromeDriver(options);
  }

  public void selectionBrowser() {
    switch (environmentProperties.getBrowser()) {
      case CHROME:
        WebDriverManager.chromedriver().setup();
        break;
      case FIREFOX:
        WebDriverManager.firefoxdriver().setup();
        break;
      case OPERA:
        WebDriverManager.operadriver().setup();
        break;
      default:
        throw new RuntimeException("The specified browser type was not found");
    }
  }
}
