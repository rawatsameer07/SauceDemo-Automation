package stepDefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import businesscomponents.HelperUtils;
import businesscomponents.ReusableFunctions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;
import supportlibraries.WebDriverFactory;

public class UIStepDefCore {
	WebDriver driver;
	LoginPage loginPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    InventoryPage inventoryPage;
    
	 public UIStepDefCore() {
		 System.out.println("test constructo");
	        this.driver = WebDriverFactory.getDriver();
	        this.loginPage = new LoginPage(driver);
	        this.inventoryPage = new InventoryPage(driver);
	        this.cartPage = new CartPage(driver);
	        this.checkoutPage = new CheckoutPage(driver);
	    }
	 
	 @Given("^I launch the application \"([^\"]*)\"$")
	 public void launchApp(String url) {
		 url = HelperUtils.analyzeValue(url);
		 driver.get(url);
	 }
	 
	 @When("^I login as \"([^\"]*)\" with password \"([^\"]*)\"$")
	 public void login(String username, String password) {
		 String uname = HelperUtils.analyzeValue(username);
		 String pwd = HelperUtils.analyzeValue(password);
		 loginPage.login(uname, pwd);
		}
	 
	 @Then("^I should be logged in successfully$")
	 public void validateLogin() {
		 assertTrue("Login failed: "+driver.getCurrentUrl(), driver.getCurrentUrl().endsWith(HelperUtils.analyzeValue("<--config.props/inventoryEndpoint")));
	 }
	 
	 @Then("^I verify the error message \"([^\"]*)\" displayed for locked out user$")
	 public void validateLoginError(String expectedMessage)
	 {
		 String actualMessage = loginPage.getLockedOutUserLoginError();
		 assertEquals("The error message did not match the expected message", expectedMessage, actualMessage);
	 }
	 
	 @When("^I sort items by \"([^\"]*)\"$")
	 public void sort(String option) {
		 assertTrue("Item not sorted",inventoryPage.sortProduct(option));
	 }
	 
	 @And("^I add \"([^\"]*)\" items priced at \"([^\"]*)\" to the cart$")
	 public void selectItems(String quantity, String price) {
		 System.out.println("test");
		 int quantityInt = Integer.parseInt(quantity); 
		 inventoryPage.selectItemBasedOnPrice(quantityInt,price);
	 }
	 
	 @Then("^I confirm \"([^\"]*)\" items are added to the cart$")
	 public void verifyItemsAddedToCart(String expectedQuantity) {
		 String actualQuantity = cartPage.getCartQuantity();
		 assertEquals(expectedQuantity + " items are not added to the cart", expectedQuantity, actualQuantity);
	 }
	 
	 @And("^I proceed to checkout \"([^\"]*)\" items$")
	 public void checkout(String quantity) {
		 cartPage.checkoutProducts(Integer.parseInt(quantity));
	 }
	 
	 @Then("^I fill user information \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	 public void fillUserInformation(String firstname, String lastName, String postalCode) {
		 checkoutPage.enterUserInfo(firstname, lastName, postalCode);
	 }
	 
	 @Then("^I complete the purchase of \"([^\"]*)\" items$")
	 public void completePurchase(String quantity) {
		 String actualMessage = checkoutPage.purchase(Integer.parseInt(quantity));
		 String expectedOrderCompleteMessage = HelperUtils.analyzeValue("<--config.props/checkoutCompleteMessage");
		 assertEquals("Order Failed :)", expectedOrderCompleteMessage, actualMessage);		 
	 }
}
