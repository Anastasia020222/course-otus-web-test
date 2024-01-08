package org.example.configuration;

import org.example.annotations.Driver;
import org.example.listeners.Listener;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class DriverControlLaunch implements BeforeEachCallback, AfterEachCallback {

  private WebDriver driver;

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    driver = new WebDriverFactory().getDriver();
    driver = new EventFiringWebDriver(driver).register(new Listener());
    for (Field field : getAnnotatedField(context, Driver.class)) {
      if (field.getType().getName().equals(WebDriver.class.getName())) {
        field.setAccessible(true);
        field.set(context.getTestInstance().get(), driver);
      }
    }
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    if (driver != null) {
      driver.quit();
    }
  }

  private Set<Field> getAnnotatedField(ExtensionContext context, Class<? extends Annotation> annotation) {
    Set<Field> fields = new HashSet<>();
    Class<?> testClass = context.getTestClass().get();
    for (Field field : testClass.getDeclaredFields()) {
      if (field.isAnnotationPresent(annotation)) {
        fields.add(field);
      }
    }
    return fields;
  }
}
