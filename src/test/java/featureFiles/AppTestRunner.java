package featureFiles;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/featureFiles/", glue = { "stepDefs", "hooks" }, tags = "@smoke", plugin = {
		"pretty", "html:target/cucumber-reports/report.html", "json:target/cucumer-reports/report.json" })

public class AppTestRunner {
}