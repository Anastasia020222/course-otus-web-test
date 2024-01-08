package org.example.lesson;

import static org.example.lesson.ReadListLesson.parseLocalDateString;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
public class LessonPages {

  WebDriver driver;
  List<WebElement> resultListLesson;
  WebElement textCourses;
  WebDriverWait wait;

  Actions actions;

  public LessonPages(WebDriver driver) {
    this.driver = driver;
    this.actions = new Actions(driver);
    this.resultListLesson = driver.findElements(By.cssSelector(".sc-1ftuaec-0.coFpkJ"));
    this.textCourses = driver.findElement(By.xpath("//h5[contains(text(),'Team Lead')]"));
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  public LessonPages actionsCourse() {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", getTextCourses());
    actions
        .moveToElement(getTextCourses())
        .click();
    actions
        .perform();
    return this;
  }

  public LessonPages checkPagesCourse() {
    WebElement pagesCourse = wait.until(visibilityOfElementLocated(By.cssSelector(".sc-s2pydo-6.EOCgR.sc-x072mc-0.hOtCic")));
    pagesCourse
        .isDisplayed();
    return this;
  }

  public List<LessonConstructor> getListCourse() {
    List<LessonConstructor> listLesson = new ArrayList<>();
    String courseName;
    String courseDate;

    for (WebElement el : getResultListLesson()) {
      if (el.findElements(By.cssSelector(".sc-1pljn7g-1.hvCeDA")).isEmpty()) {
        courseName = el.findElement(By.cssSelector(".sc-12yergf-5.AjnvM")).getText();
        courseDate = el.findElement(By.cssSelector(".sc-12yergf-7.dPBnbE")).getText();
      } else {
        courseName = el.findElement(By.cssSelector(".sc-1pljn7g-1.hvCeDA")).getText();
        courseDate = el.findElement(By.cssSelector(".sc-1pljn7g-3.cdTYKW")).getText();
      }
      listLesson.add(new LessonConstructor(courseName, parseLocalDateString(courseDate)));
    }
    return listLesson;
  }
}
