package pages;

import java.util.ArrayList;
import java.util.List;

import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import businesscomponents.ReusableFunctions;
import dev.failsafe.internal.util.Assert;

public class InventoryPage {

	WebDriver driver;
	ReusableFunctions reusableFunctions;
	public InventoryPage(WebDriver driver) {
		this.driver = driver;
		reusableFunctions = new ReusableFunctions(driver);
		PageFactory.initElements(driver,this);
	}
	
	// Inventory page objects
		@FindBy(className = "product_sort_container")
		public WebElement DROPDOWN_PRODUCT_FILTER;
		
		@FindBy(xpath = "//div[@id='inventory_container']//div[@class='inventory_item_price']")
		public List<WebElement> PRICE_LIST;
		
		
		public boolean sortProduct(String sortOption) {
			reusableFunctions.selectObjectByText(DROPDOWN_PRODUCT_FILTER, sortOption);
			return validateSortedList(sortOption);
		}
		
		public boolean validateSortedList(String sortOption) {
			List<Double> displayedPrices = new ArrayList<>();
		    for (WebElement priceElement : PRICE_LIST) {
		        String priceText = priceElement.getText().replace("$", "").trim();
		        Double price = Double.parseDouble(priceText);
		        displayedPrices.add(price);
		    }
		    
		    boolean isSorted = true;
		    if (sortOption.equals("Price (low to high)")) {
		        // Validate ascending order
		        for (int i = 1; i < displayedPrices.size(); i++) {
		            if (displayedPrices.get(i) < displayedPrices.get(i - 1)) {
		                isSorted = false;
		                break;
		            }
		        }
		}else if (sortOption.equals("Price (high to low)")) {
	        // Validate descending order
	        for (int i = 1; i < displayedPrices.size(); i++) {
	            if (displayedPrices.get(i) > displayedPrices.get(i - 1)) {
	                isSorted = false;
	                break;
	            }
	        }
		}else {
	        throw new IllegalArgumentException("Unsupported sort option: " + sortOption);
	    }
		return isSorted;
	}
	
	public void selectItemBasedOnPrice(int quantity, String price) {
		List<WebElement> addToCartButtons = reusableFunctions.generateDynamicWebElements("addToCart", price); 
		if (addToCartButtons != null) {
		for(int i=0;i<quantity && i<addToCartButtons.size();i++) {
		reusableFunctions.clickObject(addToCartButtons.get(i));
		}
		}else
			throw new AssertionError("Add to cart button for price $" + price + " not found.");
	}
}
