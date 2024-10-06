package rsa.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.SeleniumFrameworkDesign.PageObjects.LoginPageObjects;

public class BaseTest {

	public WebDriver driver;
	Properties prop;
	public LoginPageObjects lPO;

	public BaseTest() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\rahulshettacademy\\resources\\GlobalProperties.properties");
		prop.load(fis);
	}

	public WebDriver initializeDriver() throws IOException {

		// properties in java

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			ChromeOptions co = new ChromeOptions();
			co.addArguments("--disable-notifications");
			co.addArguments("start-maximized");

			driver = WebDriverManager.chromedriver().capabilities(co).avoidShutdownHook().create();
		}

		else if (browserName.equalsIgnoreCase("firefox")) {

			FirefoxOptions fo = new FirefoxOptions();

			fo.addArguments("--disable-notifications");
			fo.addArguments("start-maximized");

			driver = WebDriverManager.firefoxdriver().capabilities(fo).avoidShutdownHook().create();

		}

		else if (browserName.equalsIgnoreCase("edge")) {

			EdgeOptions eo = new EdgeOptions();

			eo.addArguments("--disable-notifications");
			eo.addArguments("start-maximized");

			driver = WebDriverManager.edgedriver().capabilities(eo).avoidShutdownHook().create();

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

	}

	@BeforeMethod
	public LoginPageObjects launchApplication() throws IOException {

		driver = initializeDriver();

		lPO = new LoginPageObjects(driver);
		lPO.goTOApplication(prop.getProperty("URL"));
		return lPO;

	}

	@AfterMethod
	public void teardown() {

		driver.close();
	}
}
