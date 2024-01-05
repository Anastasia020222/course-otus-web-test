package tests;

import static org.example.common.Constants.URL;

import org.example.annotations.Driver;
import org.example.configuration.DriverControlLaunch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.example.lesson.LessonPages;

@ExtendWith(DriverControlLaunch.class)
public class ActionsTest {

  @Driver
  private WebDriver driver;

  @Test
  @DisplayName("Движение мыши и выбор курса")
  public void actionsMouse() {
    driver.get(URL);
    LessonPages lessonPages = new LessonPages(driver);
    lessonPages
        .actionsCourse();
    lessonPages
        .checkPagesCourse();
  }
}
