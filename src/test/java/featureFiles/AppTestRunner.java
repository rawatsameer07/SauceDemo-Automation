package featureFiles;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import supportlibraries.WebDriverFactory;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/featureFiles/ProductPurchase.feature",
		glue = {"stepDefs","hooks"},
		tags = "@smoke",
		plugin = {
				"pretty",
				"html:target/cucumber-reports/report.html",
				"json:target/cucumer-reports/report.json"
		})

public class AppTestRunner {
	@BeforeClass
    public static void setUp() {
        WebDriverFactory.getDriver(); // Driver will be created based on system property
        System.out.println("AppTestRunner Called");
    }

    @AfterClass
    public static void tearDown() {
    	System.out.println("TearDown Called");
        WebDriverFactory.quitDriver();
    }
    
}