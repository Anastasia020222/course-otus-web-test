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
      if (System.getProperty("browser") != null) {
        driverControlLaunch.setBrowser(System.getProperty("browser").toLowerCase(Locale.ROOT));
      } else {
        driverControlLaunch.setBrowser(browser);
      }
    } catch (Exception e) {
      throw new RuntimeException("Couldn't get the browser");
    }
  }
}
