package hooks;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import supportlibraries.WebDriverFactory;

public class Hooks {
  
	@Before
    public void setUp() {
        WebDriverFactory.getDriver(); // Driver will be created based on system property
    }

    @After
    public void tearDown() {
        WebDriverFactory.quitDriver();
    }
}
