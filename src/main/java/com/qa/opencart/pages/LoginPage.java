package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
		
	//every page class should follow following rules:
	
	//1.private By locators: page objects
	//2.public page constructor
	//3.public page Actions/methods
	
	//Page class should contain only By locators & their methods
	//We should never write any assertion in Page class
	
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.cssSelector("input.btn.btn-primary");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img.img-responsive");
	
	private By registerLink = By.linkText("Register");
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil= new ElementUtil(driver);// whenever we call the LoginPage, constructor will be called, in the constructor we will be 
		//creating the object of ElementUtil class and we can give the same driver & session ID will be same. 
		//By doing this we can access all methods of ElementUtil class.
	}

	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleContainsAndReturn(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Login page title: "+title);
		return title;
	}
	
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContainsAndReturn(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println("Login page url: "+url);
		return url;
	}
	
	public boolean isForgotPwdLinkExist() {
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}
	
	public boolean isLogoExist() {
		return eleUtil.isElementDisplayed(logo);
	}
	//this will return the account page title
	
//	public String doLogin(String userName,String pwd) {
//		driver.findElement(username).sendKeys(userName);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
//		String accPageTitle = driver.getTitle();//Account page title
//		System.out.println("Account page title: "+accPageTitle);
//		return accPageTitle;
//	}
	
	//page chaining: this will return the account page
	
	public AccountsPage doLogin(String userName, String pwd) {
//	public String doLogin(String userName, String pwd)	
		eleUtil.waitForElementVisible(username, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
//		String title = eleUtil.waitForTitleContainsAndReturn(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIME_OUT);
//		System.out.println("Account page title: "+title);
//		return title;
		
		//page chaining : after clicking on Login button new page will be loaded(Accounts Page). So LoginPage should return the landing page object
		//page chaining or fluent pattern: when clicking on any button or link, if we are landing on a new page, then we have to return the landing
		//page object
		
		return new AccountsPage(driver);
		
	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
}
