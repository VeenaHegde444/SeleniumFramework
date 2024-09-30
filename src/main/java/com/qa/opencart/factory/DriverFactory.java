package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {
	/**
	 * @author Veena
	 */
	WebDriver driver;
	Properties prop;
	
	public static String isHighlight;
	
	//ThreadLocal is a java class, it is from lang package
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This method is used to initialize the driver on the basis of given browser name.
	 * @param browserName
	 * @return It returns driver.
	 */

	
	public WebDriver initDriver(Properties prop) { //here we are passing the entire config.properties file
//	public WebDriver initDriver(String browserName) {
		String browserName = prop.getProperty("browser"); // fetching the browser from config file. 
		System.out.println("browser name is: "+browserName);
		
		isHighlight = prop.getProperty("highlight");
		
		//create object of OptionsManager to access its methods
		OptionsManager optionsManager = new OptionsManager(prop);//
		
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
//			driver = new ChromeDriver();
//			driver = new ChromeDriver(optionsManager.getChromeOptions()); //get 'headless' & 'incognito' from config.properties file
//			driver = new ChromeDriver(optionsManager.getChromeOptionsFromSystemClass());//provide 'headless' & 'incognito' from command line
			
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions())); // using ThreadLocal reference to set 
			break;
		case "firefox":
//			driver = new FirefoxDriver();
//			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());//without ThreadLocal
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));//with ThreadLocal
			break;
		case "edge":
//			driver = new EdgeDriver();
//			driver = new EdgeDriver(optionsManager.getEdgeOptions());//without ThreadLocal
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
			//not applicable for Safari

		default:
			System.out.println(AppError.INVALID_BROWSER_MESG+ browserName + "is invalid");
			throw new BrowserException(AppError.INVALID_BROWSER_MESG + browserName); //throwing our own custom exception
//			break; // throw & break can't be together
		}
		
//		driver.manage().window().maximize();// here driver is null because we have commented line no. 49. So we should call getDriver method
		getDriver().manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		driver.get(prop.getProperty("url"));
		
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		
//		return driver; //this will return null
		return getDriver(); // so we have to return thread local copy of driver
		
		//in BaseTest class driver will become automatically threadLocal driver, no need to change anything in baseTest
	}
	
	/**
	 * This method is returning the driver with ThreadLocal
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	
	
	
	
/**
 * This method is used to initialize the properties from the config file.
 * @return 
 */
	
	

/*	
	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");// . means current project directory
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
*/
	
	//mvn clean install -Denv="qa"
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;
		
		String envName = System.getProperty("env");
		System.out.println("Running tests on env: "+envName);
		try {
			if(envName == null) {
				System.out.println("env is null..hence running tests on QA env");
				ip = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
			}
			else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\dev.config.properties");
					break;
				case "uat":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\uat.config.properties");
					break;
				case "stage":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\stage.config.properties");
					break;
				case "prod":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
					break;

				default:
					System.out.println("Please pass the right environment: "+envName);
					throw new FrameworkException("INVALID ENV NAME");
				}
			}
			prop.load(ip);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return prop;	
	}
	
	
	/**
	 * take screenshot
	 */
	
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);//temp dir
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()+ ".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}
