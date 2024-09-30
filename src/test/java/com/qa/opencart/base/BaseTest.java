package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.CheckOutPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.ResultsPage;
import com.qa.opencart.pages.ShoppingCartPage;

public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	
	protected LoginPage loginPage; // if the access modifier is default, we can't access setUp method in LoginPageTests class. So modifier should be protected.
	
	//we should maintain all page classes with reference in BaseTest, no need to initialize the class, since all the pageTests are extending BaseTest
	//it will be easier to use the reference of other pages.
	
	protected AccountsPage accPage;
	protected RegisterPage registerPage;
	protected ResultsPage resultsPage;
	protected ProductInfoPage productInfoPage;
	protected ShoppingCartPage shoppingCartPage;
	protected CheckOutPage checkOutPage;
	
	//SoftAssert : When an assertion fails, don't throw an exception but record the failure.
   // Calling assertAll() will cause an exception to be thrown if at least one assertion failed.
	//for soft assert we need to create object, because all methods are non-static
	//In case of HardAssertion, test case will be terminated if the assertion fails.
	//In HardAssert, object creation is not required, because all methods are static.

	protected SoftAssert softAssert;
	
	@Parameters({"browser"}) 
	@BeforeTest
	
	//to access the method initDriver() we need to create the object of 'DriverFactory' class
	public void setUp(String browserName) { // here we need to pass browserName to read from testng.xml file
		df = new DriverFactory();
		prop = df.initProp();
		
		//check if browser parameter is coming from testng.xml
		if(browserName != null) { //if browser is not null then we are setting the browserName from testng.xml
			prop.setProperty("browser", browserName);
		}
//		driver = df.initDriver("chrome");//return type is WebDriver, so store it in WebDriver reference variable
		driver = df.initDriver(prop);		
		loginPage = new LoginPage(driver); // when we create the object of LoginPage, constructor will be called, where we need to pass the driver
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	/**
	 * This method is used to quit the browser.
	 */
	public void tearDown() {
		driver.quit();
	}

}
