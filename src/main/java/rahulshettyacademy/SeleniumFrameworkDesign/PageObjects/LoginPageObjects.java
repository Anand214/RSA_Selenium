package rahulshettyacademy.SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import rahulshettyacademy.SeleniumFrameworkDesign.GenericUtility.GenericUtility;

public class LoginPageObjects extends GenericUtility {

	WebDriver driver;

	public LoginPageObjects(WebDriver driver) {

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
	@FindBy(id = "userEmail")
	private WebElement userEmail;
	@FindBy(id = "userPassword")
	private WebElement userPswd;
	@FindBy(id = "login")
	private WebElement loginButton;

	// Utilization by creating action methods

	public HomeProductsCataloguePageObjects loginApplication(String email, String password) {

		userEmail.sendKeys(email);
		userPswd.sendKeys(password);
		loginButton.click();
		HomeProductsCataloguePageObjects hPCPO = new HomeProductsCataloguePageObjects(driver);
		return hPCPO;

	}

	public void goTOApplication(String URL) {
		driver.get(URL);
//		String loginPageTitle = driver.getTitle();
//		Assert.assertEquals(loginPageTitle, "Let's Shop");
	}

	// $('.ng-trigger-flyInOut')

	@FindBy(css = ".ng-trigger-flyInOut")
	private WebElement failedMessage;

	public String IncorrectCredMessage() {

		waitForWebElementToAppear(failedMessage);
		return failedMessage.getText();

	}

}
