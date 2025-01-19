package pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import businesscomponents.ReusableFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	WebDriver driver;
	ReusableFunctions reusableFunctions;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		reusableFunctions = new ReusableFunctions(driver);
		PageFactory.initElements(driver, this);
	}

	// Login page objects
	@FindBy(id = "user-name")
	public WebElement INPUT_USERNAME;

	@FindBy(id = "password")
	public WebElement INPUT_PASSWORD;

	@FindBy(id = "login-button")
	public WebElement BUTTON_LOGIN;

	@FindBy(xpath = "//div[@class='error-message-container error']//h3[@data-test='error']")
	public WebElement MESSAGE_LOCKED_OUT_USER;

	public void login(String username, String password) {
		reusableFunctions.SetObjectValue(INPUT_USERNAME, username);
		reusableFunctions.SetObjectValue(INPUT_PASSWORD, password);
		reusableFunctions.clickObject(BUTTON_LOGIN);
	}

	public String getLockedOutUserLoginError() {
		return reusableFunctions.GetObjectValue(MESSAGE_LOCKED_OUT_USER);
	}
}
