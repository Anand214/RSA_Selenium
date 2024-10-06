package rahulshettyacademy.SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.SeleniumFrameworkDesign.GenericUtility.GenericUtility;

public class CheckOutPageObjects extends GenericUtility {

	WebDriver driver;

	public CheckOutPageObjects(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	private WebElement suggestiveDropDown;

	@FindBy(css = ".ta-results")
	private List<WebElement> resultOptions;

	@FindBy(css = ".ta-results:nth-child(2)")
	private WebElement selectSuggestiveDropDownResult;

	public void enterCountryAndSelect(String country) {

		waitForWebElementToAppear(suggestiveDropDown);
		suggestiveDropDown.sendKeys(country);
		waitForAllWebElementToAppear(resultOptions);
		selectSuggestiveDropDownResult.click();

	}

	@FindBy(css = ".actions a")
	private WebElement submitButton;

	public confirmationMessagePageObject submitOrder() {

		submitButton.click();

		confirmationMessagePageObject cMPO = new confirmationMessagePageObject(driver);
		return cMPO;

	}

}
