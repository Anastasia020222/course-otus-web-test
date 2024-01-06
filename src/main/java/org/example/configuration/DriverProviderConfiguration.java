package org.example.configuration;

import static org.example.common.Constants.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.OperaDriverManager;
import org.example.common.EnvironmentProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverProviderConfiguration {

  private final EnvironmentProperties environmentProperties = new EnvironmentProperties();

  public WebDriver selectionBrowser() {
    switch (environmentProperties.getBrowser()) {
      case CHROME:
        WebDriverManager.chromedriver().setup();
        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.addArguments("--start-maximized");
        return new ChromeDriver(optionsChrome);
      case FIREFOX:
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions optionsFireFox = new FirefoxOptions();
        return new FirefoxDriver(optionsFireFox);
      case OPERA:
        System.out.println("зашел");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\anser\\chromedriver.exe");
        System.out.println(System.getProperty("webdriver.chrome.driver"));
        // Создайте ChromeOptions
        ChromeOptions options = new ChromeOptions();

        // Установите местоположение бинарного файла для Opera
        options.setBinary("C:\\Users\\anser\\operadriver.exe");

        // Инициализируйте ChromeDriver с ChromeOptions
        return new ChromeDriver(options);
      default:
        throw new RuntimeException("The specified browser type was not found");
    }
  }
}
