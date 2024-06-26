package com.otus.steps;

import static org.example.lesson.ReadListLesson.filterDateCourse;
import static org.example.lesson.ReadListLesson.filterNameCourse;

import com.google.inject.Inject;
import io.cucumber.java.ru.Тогда;
import org.example.lesson.CoursesPages;

public class StartDateCourseSteps {

  @Inject
  private CoursesPages lessonPages;

  @Тогда("Найти курсы, которые стартуют после {string}")
  public void searchCourse(String course) {
    filterDateCourse(lessonPages.getListCourse(), course);
  }

  @Тогда("Фильтруем курсы по их названию")
  public void cheskFilterNameCourse() {
    filterNameCourse(lessonPages.getListCourse());
  }
}
