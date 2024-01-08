package org.example.lesson;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ListenersPages {

  WebDriver driver;
  WebElement infoButton;
  WebDriverWait wait;

  public ListenersPages(WebDriver driver) {
    this.driver = driver;
    this.infoButton = driver.findElement(By.xpath("//div[contains(text(), 'Программирование')]"));
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  public ListenersPages clickInfoButton() {
    infoButton
        .click();
    WebElement pagesCourse = wait.until(visibilityOfElementLocated(By.cssSelector(".sc-x072mc-0.sc-13r6hla-0.hOtCic.dcvVQP")));
    pagesCourse
        .isDisplayed();
    return this;
  }
}
