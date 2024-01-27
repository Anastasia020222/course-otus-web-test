package com.otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Дано;
import org.example.configuration.DriverControlLaunch;

import java.util.Locale;

public class OpenBrowserSteps {

  @Inject
  DriverControlLaunch driverControlLaunch;

  @Дано("Я открываю браузер {string}")
  public void newBrowser(String browser) {
    try {
      if (!browser.isEmpty()) {
        driverControlLaunch.setBrowser(browser);
      } else {
        driverControlLaunch.setBrowser(System.getProperty("browser", "chrome").toLowerCase(Locale.ROOT));
      }
    } catch (Exception e) {
      throw new RuntimeException("Couldn't get the browser");
    }
  }
}
