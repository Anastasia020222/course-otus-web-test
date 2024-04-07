package com.otus.steps;

import static org.example.lesson.ReadListLesson.filterPrice;

import com.google.inject.Inject;
import io.cucumber.java.ru.Тогда;
import org.example.lesson.OnlineCoursesPages;

public class OnlineCoursesSteps {

  @Inject
  private OnlineCoursesPages onlineCoursesPages;

  @Тогда("Найти курс, у которого {string} стоимость")
  public void getPrice(String typePrice) {
    filterPrice(onlineCoursesPages.getPriceCourses(), typePrice);
  }
}
