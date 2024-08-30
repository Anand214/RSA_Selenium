package rahulshettyacademy.SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.SeleniumFrameworkDesign.GenericUtility.GenericUtility;

public class confirmationMessagePageObject extends GenericUtility {

	WebDriver driver;

	public confirmationMessagePageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	private WebElement orderSuccessMessage;

	public String OrderConfirmationMessage() {

		String oSM = orderSuccessMessage.getText();
		return oSM;

	}

}
