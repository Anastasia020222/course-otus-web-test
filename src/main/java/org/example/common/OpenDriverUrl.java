package org.example.common;

import org.openqa.selenium.WebDriver;

public class OpenDriverUrl {

  private static String url = System.getProperty("webdriver.base.url");

  public static void openUrl(WebDriver driver) {

    driver.get(url);
  }
}
