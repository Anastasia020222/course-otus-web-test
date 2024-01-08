package org.example.configuration;

import static org.example.common.Constants.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class WebDriverFactory {

  private String browser = System.getProperty("browser");

  public WebDriver getDriver() {
    switch (browser) {
      case CHROME:
        return createChromeDriver();
      case FIREFOX:
        return createFirefoxDriver();
      case OPERA:
        return createOperaDriver();
      default:
        throw new RuntimeException("The specified browser type was not found");
    }
  }

  private WebDriver createChromeDriver() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");
    options.addArguments("--remote-allow-origins=*");
    return new ChromeDriver(options);
  }

  private WebDriver createFirefoxDriver() {
    WebDriverManager.firefoxdriver().setup();
    return new FirefoxDriver();
  }

  private WebDriver createOperaDriver() {
    System.setProperty("webdriver.opera.driver", "C:\\Users\\anser\\operadriver.exe");
    OperaOptions options = new OperaOptions();
    options.addArguments("-start-maximized");
    return new OperaDriver(options);
  }
}
