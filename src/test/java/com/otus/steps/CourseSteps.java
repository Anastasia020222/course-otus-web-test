package com.otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.example.lesson.CoursesPages;

import static org.example.lesson.ReadListLesson.filterEarlyDate;
import static org.example.lesson.ReadListLesson.filterLateDate;

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

  @Тогда("Найти курс, который стартует позже всех")
  public void checkFilterLateDate() {
    filterLateDate(lessonPages.getListCourse());
  }

  @Тогда("Найти курс, который стартует раньше всех")
  public void checkFilterEarlyDate() {
    filterEarlyDate(lessonPages.getListCourse());
  }
}
