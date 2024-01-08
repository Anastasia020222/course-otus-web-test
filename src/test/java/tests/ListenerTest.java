package tests;

import static org.example.common.OpenDriverUrl.openUrl;

import org.example.annotations.Driver;
import org.example.configuration.DriverControlLaunch;
import org.example.lesson.ListenersPages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(DriverControlLaunch.class)
public class ListenerTest {

  @Driver
  private WebDriver driver;

  @Test
  @DisplayName("Listeners. Подсветка элемента перед нажатием")
  public void actionsMouse() {
    openUrl(driver);
    ListenersPages listenersPages = new ListenersPages(driver);
    listenersPages
        .clickInfoButton();
  }
}
