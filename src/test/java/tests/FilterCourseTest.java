package tests;

import static org.example.common.Constants.URL;
import static org.example.lesson.ReadListLesson.*;

import org.example.annotations.Driver;
import org.example.configuration.DriverControlLaunch;
import org.example.lesson.LessonPages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(DriverControlLaunch.class)
public class FilterCourseTest {

  @Driver
  private WebDriver driver;

  @Test
  @DisplayName("Фильтры: по названию курса и датам")
  public void listCourse() {
    driver.get(URL);
    LessonPages lessonPages = new LessonPages(driver);
    filterNameCourse(lessonPages.getListCourse());
    filterEarlyDate(lessonPages.getListCourse());
    filterLateDate(lessonPages.getListCourse());
  }
}
