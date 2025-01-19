package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import businesscomponents.ReusableFunctions;

public class CartPage {

	WebDriver driver;
	ReusableFunctions reusableFunctions;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		reusableFunctions = new ReusableFunctions(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "shopping_cart_badge")
	public WebElement ICON_SHOPPING_CART_BADGE;

	@FindBy(className = "cart_item_label")
	public List<WebElement> LABEL_CART_ITEMS;

	@FindBy(id = "checkout")
	public WebElement BUTTON_CHECKOUT;

	public String getCartQuantity() {
		String numberOfItems = reusableFunctions.GetObjectValue(ICON_SHOPPING_CART_BADGE);
		return numberOfItems;
	}

	public void checkoutProducts(int expectedQuantity) {
		reusableFunctions.clickObject(ICON_SHOPPING_CART_BADGE);
		int actualQuantity = reusableFunctions.getObjectSize(LABEL_CART_ITEMS);
		if (expectedQuantity != actualQuantity)
			throw new AssertionError(
					"Expected " + expectedQuantity + " items in the cart, but found " + actualQuantity);
		reusableFunctions.scrollToElement(BUTTON_CHECKOUT);
		reusableFunctions.clickObject(BUTTON_CHECKOUT);
	}

}
