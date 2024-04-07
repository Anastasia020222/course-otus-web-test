package com.otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.example.lesson.CoursesPages;

public class CourseSteps {

  @Inject
  CoursesPages lessonPages;

  @Если("Кликнуть по курсу {string}")
  public void clickCourse(String arg0) {
    lessonPages.actionsClickCourse(arg0);
  }

  @Тогда("Откроется страница курса")
  public void openPagesCourse() {
    lessonPages.checkPagesCourse();
  }

  @И("На странице курса заголовок соответствует названию курса {string}")
  public void checkTitleCourse(String arg0) {
    lessonPages.checkTitleCourse(arg0);
  }
}
