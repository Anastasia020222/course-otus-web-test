package org.example.lesson;

import lombok.Getter;

@Getter
public enum Months {

  DECEMBER("1", "декабря"),
  JANUARY("1", "января"),
  FEBRUARY("1", "февраля"),
  MARCH("1", "марта"),
  APRIL("1", "апреля"),
  MAY("1", "мая"),
  JUNE("1", "июня"),
  JULY("1", "июля"),
  AUGUST("1", "августа"),
  SEPTEMBER("1", "сентября"),
  OCTOBER("1", "октября"),
  NOVEMBER("1", "ноября");

  private String day;
  private String month;

  Months(String day, String month) {
    this.day = day;
    this.month = month;
  }
}
