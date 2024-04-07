package org.example.lesson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class LessonConstructor {

  private String name;
  private LocalDate data;

  @Override
  public String toString() {
    return "name: " + name
        + ", data: " + data + "\n";
  }
}
