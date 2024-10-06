package rahulshettyacademy.SeleniumFrameworkDesign.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.SeleniumFrameworkDesign.PageObjects.CartPageObjects;
import rahulshettyacademy.SeleniumFrameworkDesign.PageObjects.CheckOutPageObjects;
import rahulshettyacademy.SeleniumFrameworkDesign.PageObjects.HomeProductsCataloguePageObjects;
import rahulshettyacademy.SeleniumFrameworkDesign.PageObjects.OrderPageObjects;
import rahulshettyacademy.SeleniumFrameworkDesign.PageObjects.confirmationMessagePageObject;
import rsa.TestComponents.BaseTest;

public class OrderBookingTest extends BaseTest {

	String ProductName = "ZARA COAT 3";

	public OrderBookingTest() throws IOException {
		super();
	}

	@Test
	public void E2EBooking() throws IOException {

		HomeProductsCataloguePageObjects hPCPO = lPO.loginApplication("ss987@gmail.com", "Anchorage@214");

		List<WebElement> productsList = hPCPO.getProductsList();
		productsList.stream().forEach(product -> System.out.println(product.getText()));
		hPCPO.addToCart(ProductName);
		CartPageObjects cartPO = hPCPO.navigateToCart();

		boolean match = cartPO.verifyProductDisplayed(ProductName);
		Assert.assertTrue(match);
		CheckOutPageObjects cOPO = cartPO.checkOutFromPage();

		cOPO.enterCountryAndSelect("india");
		confirmationMessagePageObject cMPO = cOPO.submitOrder();

		String orderConfMsg = cMPO.OrderConfirmationMessage();
		Assert.assertTrue(orderConfMsg.equalsIgnoreCase("Thankyou for the order."));

		System.out.println("pass");

	}

	@Test(dependsOnMethods = { "E2EBooking" })
	public void OrderHistoryTest() {

		HomeProductsCataloguePageObjects hPCPO = lPO.loginApplication("ss987@gmail.com", "Anchorage@214");
		OrderPageObjects oPO = hPCPO.navigateToOrderPage();
		Assert.assertTrue(oPO.verifyProductInOrderHistory(ProductName));

	}

}

/*
 * package rahulshettyacademy.SeleniumFrameworkDesign;
 * 
 * import java.time.Duration; import java.util.List; import
 * org.openqa.selenium.By; import org.openqa.selenium.JavascriptExecutor; import
 * org.openqa.selenium.WebDriver; import org.openqa.selenium.WebElement; import
 * org.openqa.selenium.chrome.ChromeOptions; import
 * org.openqa.selenium.interactions.Actions; import
 * org.openqa.selenium.support.ui.ExpectedConditions; import
 * org.openqa.selenium.support.ui.WebDriverWait; import org.testng.Assert;
 * import io.github.bonigarcia.wdm.WebDriverManager; import
 * rahulshettyacademy.SeleniumFrameworkDesign.PageObjects.LoginPageObjects;
 * 
 * public class OrderBookingTest {
 * 
 * public static void main(String[] args) {
 * 
 * ChromeOptions co = new ChromeOptions();
 * co.addArguments("--disable-notifications");
 * co.addArguments("start-maximized");
 * 
 * WebDriver driver =
 * WebDriverManager.chromedriver().capabilities(co).avoidShutdownHook().create()
 * ;
 * 
 * driver.get("https://rahulshettyacademy.com/client/");
 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
 * 
 * String ProductName = "ZARA COAT 3";
 * driver.findElement(By.id("userEmail")).sendKeys("ss987@gmail.com");
 * driver.findElement(By.id("userPassword")).sendKeys("Anchorage@214");
 * driver.findElement(By.id("login")).click();
 * 
 * LoginPageObjects lPO= new LoginPageObjects(driver); String loginPageTitle =
 * driver.getTitle();
 * 
 * Assert.assertEquals(loginPageTitle, "Let's Shop");
 * 
 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
 * 
 * List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));
 * wait.until(ExpectedConditions.visibilityOfAllElements(productList));
 * 
 * WebElement DesiredProd = productList.stream() .filter(product ->
 * product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(
 * ProductName)) .findFirst().orElse(null);
 * DesiredProd.findElement(By.cssSelector(".card-body button:last-of-type")).
 * click();
 * 
 * WebElement loginToastMsg = driver .findElement(By.
 * cssSelector("#toast-container [aria-label='Login Successfully']"));
 * wait.until(ExpectedConditions.visibilityOf(loginToastMsg));
 * highLighterMethod(driver, loginToastMsg);
 * 
 * String successMsg =
 * driver.findElement(By.cssSelector("#toast-container")).getText();
 * Assert.assertEquals(successMsg, "Login Successfully");
 * 
 * WebElement pageLoadELe =
 * driver.findElement(By.cssSelector(".ngx-spinner-overlay"));
 * wait.until(ExpectedConditions.invisibilityOf(pageLoadELe));
 * 
 * String successMsg2 = driver.findElement(By.
 * cssSelector("#toast-container [aria-label='Product Added To Cart']"))
 * .getText(); Assert.assertEquals(successMsg2, "Product Added To Cart");
 * 
 * driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
 * 
 * List<WebElement> itemsInCart =
 * driver.findElements(By.cssSelector(".cartSection h3"));
 * 
 * boolean match = itemsInCart.stream() .anyMatch(cartProduct ->
 * cartProduct.getText().equalsIgnoreCase(ProductName));
 * Assert.assertTrue(match);
 * 
 * driver.findElement(By.cssSelector(".totalRow button")).click();
 * 
 * wait.until( ExpectedConditions.visibilityOf(driver.findElement(By.
 * cssSelector("[placeholder='Select Country']"))));
 * 
 * // Actions action = new Actions(driver); //
 * action.sendKeys(driver.findElement(By.
 * cssSelector("[placeholder='Select Country']")), "india");
 * 
 * driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys
 * ("india");
 * wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.
 * cssSelector(".ta-results"))));
 * 
 * driver.findElement(By.cssSelector(".ta-results:nth-child(2)")).click();
 * 
 * driver.findElement(By.cssSelector(".actions a")).click();
 * 
 * String orderConfMsg =
 * driver.findElement(By.cssSelector(".hero-primary")).getText();
 * Assert.assertTrue(orderConfMsg.equalsIgnoreCase("Thankyou for the order."));
 * 
 * System.out.println("pass"); driver.close();
 * 
 * }
 * 
 * public static void highLighterMethod(WebDriver driver, WebElement element) {
 * JavascriptExecutor js = (JavascriptExecutor) driver; js.
 * executeScript("arguments[0].setAttribute('style', 'background: transparent; border: 2px solid red;');"
 * , element); }
 * 
 * }
 * 
 * 
 */
