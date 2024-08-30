package rahulshettyacademy.SeleniumFrameworkDesign.GenericUtility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.SeleniumFrameworkDesign.PageObjects.CartPageObjects;
import rahulshettyacademy.SeleniumFrameworkDesign.PageObjects.OrderPageObjects;

public class GenericUtility {

	WebDriver driver;
	WebDriverWait wait;

	public GenericUtility(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	public void waitForElementToAppear(By findBy) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}

	public void waitForElementToDisappear(WebElement ele) {

		wait.until(ExpectedConditions.invisibilityOf(ele));

	}

	public void waitForWebElementToAppear(WebElement ele) {

		wait.until(ExpectedConditions.visibilityOf(ele));

	}

	public void waitForAllWebElementToAppear(List<WebElement> eles) {

		wait.until(ExpectedConditions.visibilityOfAllElements(eles));

	}

//	Because header is common for all pages
	@FindBy(css = "[routerlink*='cart']")
	private WebElement cartLink;

	public CartPageObjects navigateToCart() {
		cartLink.click();
		CartPageObjects cartPO = new CartPageObjects(driver);
		return cartPO;
	}

	@FindBy(xpath = "//button[contains(.,'ORDERS')]")
	private WebElement orderLink;

	public OrderPageObjects navigateToOrderPage() {
		waitForWebElementToAppear(orderLink);
		orderLink.click();
		OrderPageObjects ordPO = new OrderPageObjects(driver);
		return ordPO;
	}

	public static void highLighterMethod(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: transparent; border: 2px solid red;');",
				element);
	}
	
	public static void highLighterElementsMethod(WebDriver driver, List<WebElement> element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: transparent; border: 2px solid red;');",
				element);
	}

}
