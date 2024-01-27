package org.example.configuration;

import com.google.inject.Inject;
import org.example.listeners.Listener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class AbsDriverPageObject {

  public WebDriver driver;
  public String browser;

  @Inject
  public AbsDriverPageObject(DriverControlLaunch driverControlLaunch) {
    this.browser = driverControlLaunch.getBrowser();
    this.driver = driverControlLaunch.getDriver();
    driver = new EventFiringWebDriver(driver).register(new Listener());
    PageFactory.initElements(driver, this);
  }
}
