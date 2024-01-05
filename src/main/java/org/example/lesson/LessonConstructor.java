package org.example.lesson;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LessonConstructor {

  private String name;
  private LocalDate data;

  public LessonConstructor(String name, LocalDate data) {
    this.name = name;
    this.data = data;
  }

  @Override
  public String toString() {
    return "name: " + name
        + ", data: " + data + "\n";
  }
}
