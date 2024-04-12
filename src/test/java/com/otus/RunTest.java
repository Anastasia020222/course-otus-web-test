package com.otus;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
    features = {"src/test/resources/feature"},
    glue = {"com/otus/hooks", "com/otus/steps"},
    publish = true)
public class RunTest {
}
