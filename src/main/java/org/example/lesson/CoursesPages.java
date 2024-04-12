package org.example.lesson;

import static org.example.lesson.ReadListLesson.parseLocalDateString;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import com.google.inject.Inject;
import lombok.Getter;
import org.example.configuration.AbsDriverPageObject;
import org.example.configuration.DriverControlLaunch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CoursesPages extends AbsDriverPageObject {

  private WebDriverWait wait;
  private Actions actions;

  @Inject
  public CoursesPages(DriverControlLaunch driverControlLaunch) {
    super(driverControlLaunch);
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    this.actions = new Actions(driver);
  }

  @FindBy(css = ".sc-1ftuaec-0.coFpkJ")
  private List<WebElement> resultListLesson;

  @FindBy(css = ".sc-12yergf-5.AjnvM")
  private WebElement nameCourses;

  @FindBy(css = ".sc-1og4wiw-0.sc-s2pydo-1.iLVLDh.diGrSa")
  private WebElement titleCourse;

  @FindBy(css = ".sc-s2pydo-6.EOCgR.sc-x072mc-0.hOtCic")
  private WebElement pageCourse;

  @FindBy(css = ".sc-1pljn7g-1.hvCeDA")
  private WebElement popularCourse;

  public CoursesPages actionsClickCourse(String course) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sc-1r3ji37-0.klcrtf")));
    WebElement textCourses = driver.findElement(By.xpath(String.format("//*[contains(text(), '%s')]", course)));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", textCourses);
    actions
        .moveToElement(textCourses)
        .click();
    actions
        .perform();
    wait.until(visibilityOfElementLocated(By.cssSelector(".sc-s2pydo-6.EOCgR.sc-x072mc-0.hOtCic")));
    return this;
  }

  public CoursesPages checkPagesCourse() {
    pageCourse
        .isDisplayed();
    return this;
  }

  public CoursesPages checkTitleCourse(String course) {
    titleCourse.getText().equals(course);
    return this;
  }

  public List<LessonConstructor> getListCourse() {
    List<LessonConstructor> listLesson = new ArrayList<>();
    String courseName;
    String courseDate;
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sc-1pljn7g-1.hvCeDA")));
    for (WebElement el : resultListLesson) {
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
