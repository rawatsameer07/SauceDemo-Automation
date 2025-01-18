package supportlibraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
	
	private static WebDriver driver;
	
	public static WebDriver getDriver(String browser) {
        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                   	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/browsers/chromedriver.exe");
                  	ChromeOptions options = new ChromeOptions();
                	options.addArguments("--remote-allow-origins=*");
                	driver = new ChromeDriver(options);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriver getDriver() {
    	return getDriver("chrome");
    }

    public static void quitDriver() {
    	System.out.println("quitDriver called");
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
	
}
