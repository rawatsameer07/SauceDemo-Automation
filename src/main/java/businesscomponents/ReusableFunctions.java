package businesscomponents;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ReusableFunctions {
	private static final Logger logger = LoggerFactory.getLogger(ReusableFunctions.class);
	WebDriver driver;
	public WebDriverWait wait;
	public ReusableFunctions(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	public WebElement waitForElementToBeDisplayed(WebElement obj1) {
        return wait.until(ExpectedConditions.visibilityOf(obj1));
    }
	
	public void ClearObjectValue(WebElement obj1) {
		waitForElementToBeDisplayed(obj1);
		if(obj1!=null) {
			obj1.clear();
			logger.info("Element "+obj1+"value is Clear");	
		}else
			logger.info("Element "+obj1+"value is not Clear");	
	}
	
	public void SetObjectValue(WebElement obj1, String objValue){
		waitForElementToBeDisplayed(obj1);
		if (obj1 != null) { 
			ClearObjectValue(obj1);
			obj1.sendKeys(objValue);
			logger.info("Element "+obj1+" is value set to "+objValue);
			} else
				logger.info("Element "+obj1+" not found");
	}
	
	public String GetObjectValue(WebElement obj1){
		String objValue=null;
		waitForElementToBeDisplayed(obj1);
		if (obj1 != null) { 
			objValue=obj1.getText().toString()  ;
			logger.info("Element "+obj1+" is value set to "+objValue);
		} else
			logger.info("Element "+obj1+" not found");
		return objValue; 
	}
	
	public void clickObject(WebElement obj1){
		waitForElementToBeDisplayed(obj1);
		if (obj1 != null) { 
			obj1.click();
			logger.info("Element "+obj1+" is clicked");
		} else
			logger.info("Element  "+obj1+" not found");
	}
	
	public void selectObjectByText(WebElement obj1,String objValue){
		waitForElementToBeDisplayed(obj1);

		if (obj1 != null) { 
			Select select=new Select(obj1);
			select.selectByVisibleText(objValue);
			logger.info("Element "+obj1+" is value selected to "+objValue);
			} else
				logger.info("Element  "+obj1+" or "+ objValue+" not found");
		
	}
	
	public List<WebElement> generateDynamicWebElements(String elementName, String dynamicValue) {
	    List<WebElement> element = null;
	    if (elementName.equalsIgnoreCase("addToCart")) {
	        element = driver.findElements(By.xpath("//div[@class='inventory_item_price' and text()='" + dynamicValue + "']/following-sibling::button[text()='Add to cart']"));
	    }
	    return element;
	}
	
	public int getObjectSize(List<WebElement> obj1) {
			return obj1.size();
	}
	
	public void VerifyObjectExist(WebElement obj1){
		waitForElementToBeDisplayed(obj1);
		try{
			String value = obj1.getText();
			System.out.println(obj1.getText());
			logger.info("Element "+obj1+" ,value: "+value+" is found");
		}catch (NoSuchElementException e) {
		    System.out.println("Element Not Found");
		    logger.info("Element "+obj1+" not found");
		}
	}
	
	public void scrollToElement(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }
	
}
