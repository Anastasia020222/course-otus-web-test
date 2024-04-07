package com.otus.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import org.example.common.OpenDriverUrl;

public class OpenPageSteps {

  @Inject
  OpenDriverUrl openDriverUrl;

  @Пусть("Открыта главная страница")
  public void open() {
    openDriverUrl.openUrl("");
  }

  @Когда("Открыта страница подготовительных курсов {string}")
  public void openPreparatoryCourses(String arg0) {
    openDriverUrl.openUrl(arg0);
  }
}
