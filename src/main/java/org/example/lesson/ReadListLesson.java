package org.example.lesson;

import java.time.DateTimeException;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

  public static void filterDateCourse(List<LessonConstructor> listCourse, String data) {
    List<LessonConstructor> listDateCourse = listCourse.stream()
        .filter(date -> {
          LocalDate lessonDate = date.getData();
          return lessonDate != null && lessonDate.isAfter(parseLocalDateString(data));
        })
        .collect(Collectors.toList());
    System.out.println("Курсы, которые стартуют после " + data + ": \n" + listDateCourse);
  }

  public static int getMaxAndMinPrice(HashMap<String, Integer> getPrice, String typePrice) {
    int price = 0;
    if (typePrice.equals("max")) {
      price = getPrice.values().stream().max(Integer::compareTo).orElseThrow();
    } else if (typePrice.equals("min")) {
      price = getPrice.values().stream().min(Integer::compareTo).orElseThrow();
    }
    return price;
  }

  public static void filterPrice(HashMap<String, Integer> getPrice, String typePrice) {
    System.out.println("Вывод курсов с " + typePrice + " стоимостью:");
    getPrice.entrySet().stream()
        .filter(price -> price.getValue() == getMaxAndMinPrice(getPrice, typePrice))
        .forEach(p -> System.out.println("Курс: '" + p.getKey() + "' со стоимостью - " + p.getValue() + "₽"));
  }

  private static String dateChange(String data) {
    String newData = "";
    String deleteFirstSymbol = data.replaceFirst("^[CBСВ]\\s", "");

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
      for (Month month : Month.values()) {
        String getMonth = month.getDisplayName(TextStyle.FULL, new Locale("ru"));
        if (newData.contains(getMonth.substring(0, getMonth.length() - 1))) {
          newData = newData.replaceFirst("^(.*?)(\\d+)", "1 " + getMonth + " $2");
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
