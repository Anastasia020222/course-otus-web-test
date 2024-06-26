package org.example.configuration;

import static org.example.common.Constants.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {

  private final boolean remote = "true".equals(System.getProperty("remote"));
  private final String versionBrowser = System.getProperty("versionBrowser");
  private final String remoteSelenoidUrl = System.getProperty("remote.url");

  public WebDriver getDriver(String browser) {
    if (!remote) {
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
    } else {
      try {
        return new RemoteWebDriver(new URL(remoteSelenoidUrl), getCapabilities(browser));
      } catch (MalformedURLException e) {
        throw new RuntimeException("Unable to create remote driver", e);
      }
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

  private DesiredCapabilities getCapabilities(String browser) {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    if (CHROME.equals(browser) || FIREFOX.equals(browser)) {
      capabilities.setBrowserName(browser);
    }
    capabilities.setVersion(versionBrowser);
    capabilities.setCapability("enableVNC", true);
    //capabilities.setCapability("enableVideo", true);
    Map<String, Object> selenoidOptions = new HashMap<>();
    if (OPERA.equals(browser)) {
      selenoidOptions.put("browserName", browser);
    }
    capabilities.setCapability("selenoid:options", selenoidOptions);
    return capabilities;
  }
}
