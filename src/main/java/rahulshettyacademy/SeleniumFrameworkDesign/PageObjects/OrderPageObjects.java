package rahulshettyacademy.SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.SeleniumFrameworkDesign.GenericUtility.GenericUtility;

public class OrderPageObjects extends GenericUtility {

	WebDriver driver;

	public OrderPageObjects(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> itemsInOrderHistory;

	public boolean verifyProductInOrderHistory(String ProductName)  {
		highLighterElementsMethod(driver, itemsInOrderHistory);
		boolean match = itemsInOrderHistory.stream()
				.anyMatch(orderProduct -> orderProduct.getText().equalsIgnoreCase(ProductName));
		return match;
	}

}
