package rahulshettyacademy.SeleniumFrameworkDesign.Tests;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.SeleniumFrameworkDesign.PageObjects.CartPageObjects;
import rahulshettyacademy.SeleniumFrameworkDesign.PageObjects.CheckOutPageObjects;
import rahulshettyacademy.SeleniumFrameworkDesign.PageObjects.HomeProductsCataloguePageObjects;
import rahulshettyacademy.SeleniumFrameworkDesign.PageObjects.confirmationMessagePageObject;
import rsa.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {

	public ErrorValidationsTest() throws IOException {
		super();
	}

	@Test
	public void LogingInWithInvalidCred() throws IOException {

		lPO.loginApplication("ajdaijd@gmail.com", "dovndvnd");

		Assert.assertEquals("Incorrect email or password.", lPO.IncorrectCredMessage());

	}
	
	@Test
	public void ProductErrorValidations() throws IOException {
		String ProductName = "ZARA COAT 3";
		String corctProductName = "ZARA COAT 3";

		HomeProductsCataloguePageObjects hPCPO = lPO.loginApplication("ss987@gmail.com", "Anchorage@214");

		hPCPO.addToCart(corctProductName);
		CartPageObjects cartPO = hPCPO.navigateToCart();

		boolean match = cartPO.verifyProductDisplayed(ProductName);
		Assert.assertTrue(match);
		

	}
}
