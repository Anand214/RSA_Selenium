package rahulshettyacademy.SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.SeleniumFrameworkDesign.GenericUtility.GenericUtility;

public class CartPageObjects extends GenericUtility {

	WebDriver driver;

	public CartPageObjects(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	private List<WebElement> itemsInCart;

	public boolean verifyProductDisplayed(String ProductName) {
		boolean match = itemsInCart.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(ProductName));
		return match;
	}

	@FindBy(css = ".totalRow button")
	private WebElement chechoutButton;

	public CheckOutPageObjects checkOutFromPage() {

		chechoutButton.click();
		CheckOutPageObjects cOPO= new CheckOutPageObjects(driver);
		return cOPO;
	}

}