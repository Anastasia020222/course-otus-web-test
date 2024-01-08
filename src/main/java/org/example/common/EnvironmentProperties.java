package org.example.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvironmentProperties {

  private final Properties properties = new Properties();

  public EnvironmentProperties() {
    loadProperties();
  }

  private void loadProperties() {
    try {
      InputStream input = new FileInputStream("src/main/resources/local.properties");
      properties.load(input);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getBrowser() {
    return properties.getProperty("browser");
  }

  public String getUrl() {
    return properties.getProperty("url");
  }
}
