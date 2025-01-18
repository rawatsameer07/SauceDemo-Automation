package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import businesscomponents.ReusableFunctions;

public class CheckoutPage {

	WebDriver driver;
	ReusableFunctions reusableFunctions;
	CartPage cartPage;
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		reusableFunctions = new ReusableFunctions(driver);
		cartPage = new CartPage(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id = "first-name")
	public WebElement INPUT_FIRST_NAME;
	
	@FindBy(id = "last-name")
	public WebElement INPUT_LAST_NAME;
	
	@FindBy(id = "postal-code")
	public WebElement INPUT_POSTAL_CODE;
	
	@FindBy(id = "continue")
	public WebElement BUTTON_CONTINUE;
	
	@FindBy(xpath = "//div[@data-test='payment-info-value']")
	public WebElement TEXT_PAYMENT_INFO;
	
	@FindBy(xpath = "//div[@data-test='payment-info-value']")
	public WebElement TEXT_SHIPPING_INFO;
	
	@FindBy(className = "summary_subtotal_label")
	public WebElement TEXT_ITEM_TOTAL;
	
	@FindBy(id = "finish")
	public WebElement BUTTON_FINISH;
	
	@FindBy(className = "complete-header")
	public WebElement MESSAGE_THANK_YOU;
	
	public void enterUserInfo(String firstName, String lastName, String postalCode) {
		reusableFunctions.SetObjectValue(INPUT_FIRST_NAME, firstName);
		reusableFunctions.SetObjectValue(INPUT_LAST_NAME, lastName);
		reusableFunctions.SetObjectValue(INPUT_POSTAL_CODE, postalCode);
	}
	
	public String purchase(int quantity) {
		reusableFunctions.clickObject(BUTTON_CONTINUE);
		int actualQuantity = reusableFunctions.getObjectSize(cartPage.LABEL_CART_ITEMS);
		if(quantity!=actualQuantity)
			throw new AssertionError("Expected " + quantity + " items in the cart, but found " + actualQuantity);

		reusableFunctions.VerifyObjectExist(TEXT_PAYMENT_INFO);
		reusableFunctions.VerifyObjectExist(TEXT_SHIPPING_INFO);
		reusableFunctions.VerifyObjectExist(TEXT_ITEM_TOTAL);
		reusableFunctions.scrollToElement(BUTTON_FINISH);
		reusableFunctions.clickObject(BUTTON_FINISH);
		return reusableFunctions.GetObjectValue(MESSAGE_THANK_YOU);
	}
	
	
}
