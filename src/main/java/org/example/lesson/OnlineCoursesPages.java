package org.example.lesson;

import com.google.inject.Inject;
import org.example.configuration.AbsDriverPageObject;
import org.example.configuration.DriverControlLaunch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnlineCoursesPages extends AbsDriverPageObject {

  @Inject
  public OnlineCoursesPages(DriverControlLaunch driverControlLaunch) {
    super(driverControlLaunch);
  }

  @FindBy(css = ".js-stats.lessons__new-item.lessons__new-item_hovered")
  private List<WebElement> priceCourses;

  public HashMap<String, Integer> getPriceCourses() {
    HashMap<String, Integer> getCoursesAndPrice = new HashMap<>();
    String nameCourse;
    String price;
    for (WebElement el : priceCourses) {
      nameCourse = el.findElement(By.cssSelector(".lessons__new-item-title.lessons__new-item-title_with-tags")).getText();
      price = el.findElement(By.cssSelector(".lessons__new-item-price")).getText();
      Pattern pattern = Pattern.compile("(\\d+)");
      Matcher matcher = pattern.matcher(price);
      matcher.find();
      getCoursesAndPrice.put(nameCourse, Integer.valueOf(matcher.group()));
    }
    return getCoursesAndPrice;
  }
}
