package org.example.lesson;

import java.time.DateTimeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReadListLesson {

  public static void filterNameCourse(List<LessonConstructor> listCourse) {
    listCourse.stream()
        .sorted(Comparator.comparing(LessonConstructor::getName))
        .collect(Collectors.toList());
    listCourse.forEach(course -> System.out.println(course.getName()));
  }

  public static void filterEarlyDate(List<LessonConstructor> listCourse) {
    LessonConstructor listEarlyDate = listCourse.stream()
        .reduce((course1, course2) ->
            course1.getData().isBefore(course2.getData()) ? course1 : course2)
        .orElse(null);
    System.out.println(listEarlyDate);
  }

  public static void filterLateDate(List<LessonConstructor> listCourse) {
    LessonConstructor listLateDate = listCourse.stream()
        .reduce((course1, course2) ->
            course1.getData().isAfter(course2.getData()) ? course1 : course2)
        .orElse(null);
    System.out.println(listLateDate);
  }

  private static String dateChange(String data) {
    String newData = "";
    String deleteFirstSymbol = data.replaceFirst("^[CС]\\s", "");

    if (!data.contains("года") && !data.contains("месяцев")) {
      newData = deleteFirstSymbol + " 2024";
    } else if (data.contains("месяцев") && !data.contains("года")) {
      newData = deleteFirstSymbol.replaceFirst("\\d+\\sмесяцев", "2024");
    } else if (data.contains("года") && !data.contains("месяцев")) {
      newData = deleteFirstSymbol.replaceFirst("\\sгода", "");
    } else if (data.contains("года") && data.contains("месяцев")) {
      newData = deleteFirstSymbol.replaceFirst("\\sгода\\s\\d+\\sмесяцев", "");
    }

    if (!newData.matches("^[0-9].*")) {
      for (Months month : Months.values()) {
        if (newData.contains(month.getMonth().substring(0, month.getMonth().length() - 1))) {
          newData = newData.replaceFirst("^(.*?)(\\d+)", month.getDay() + " " + month.getMonth() + " $2");
        }
      }
    }
    return newData;
  }

  public static LocalDate parseLocalDateString(String dateString) {
    DateTimeFormatter formatterOneDay = DateTimeFormatter.ofPattern("d MMMM yyyy");
    LocalDate localDate = null;
    try {
      localDate = LocalDate.parse(dateChange(dateString), formatterOneDay);
    } catch (DateTimeException e) {
      System.out.println("Не удалось разобрать месяц и год. " + e.getMessage());
    }
    return localDate;
  }
}