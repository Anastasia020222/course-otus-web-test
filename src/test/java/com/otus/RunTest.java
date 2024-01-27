package com.otus;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/feature"}, glue = {"com/otus/hooks", "com/otus/steps"})
public class RunTest {
}
