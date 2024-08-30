package rahulshettyacademy.SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulshettyacademy.SeleniumFrameworkDesign.GenericUtility.GenericUtility;

public class HomeProductsCataloguePageObjects extends GenericUtility {

	WebDriver driver;

	public HomeProductsCataloguePageObjects(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// pagefactory(c) has initElemets method to initiatlize i.e., provide driver
		// instance for all @fingby
		// we declare it in constructor because in any class this the the first line of
		// code which runs
		// #Initialization
		PageFactory.initElements(driver, this);
	}
//
//	WebElement userEmail = driver.findElement(By.id("userEmail"));
//	WebElement userPswd = driver.findElement(By.id("userPassword"));
//	WebElement loginButton = driver.findElement(By.id("login"));

	// Page factory findBy annotation, #Declaration

	@FindBy(css = ".mb-3")
	private List<WebElement> productList;
	@FindBy(css = "#toast-container [aria-label='Login Successfully']")
	private WebElement loginSuccessMsg;
	@FindBy(css = ".ngx-spinner-overlay")
	private WebElement loadingPage;
	@FindBy(css = "#toast-container [aria-label='Product Added To Cart']")
	private WebElement productToCartMsg;

	By productsBy = By.cssSelector(".mb-3");
	By successMsgBy = By.cssSelector("#toast-container [aria-label='Login Successfully']");

	public List<WebElement> getProductsList() {

		waitForElementToAppear(productsBy);
		return productList;

	}

	public WebElement getProductByName(String ProductName) {

		WebElement DesiredProd = getProductsList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(ProductName))
				.findFirst().orElse(null);
		return DesiredProd;
	}

	public void addToCart(String ProductName) {
//
//		waitForElementToAppear(successMsgBy);
//		Assert.assertEquals(loginSuccessMsg.getText(), "Login Successfully");
		getProductByName(ProductName).findElement(By.cssSelector(".card-body button:last-of-type")).click();
//		Assert.assertEquals(productToCartMsg.getText(), "Product Added To Cart");
		waitForElementToDisappear(loadingPage);
		

	}



}
