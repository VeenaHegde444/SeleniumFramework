package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutLink = By.linkText("Logout");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");	

	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getAccountsPageTitle() {
		String title = eleUtil.waitForTitleContainsAndReturn(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIME_OUT);
		System.out.println(title);
		return title;
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.isElementDisplayed(logoutLink);
	}
	
	public int getTotalAccountsPageHeader() {
		return eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
	}
	
	public List<String> getAccPageHeaders() {
		List<WebElement> headersList = eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> headersValueList = new ArrayList<String>();
		for(WebElement e: headersList) {
			String header = e.getText();
			headersValueList.add(header);
		}
		return headersValueList;
	}
	
	//first we will create test method, then we will create page class -it is a TDD(Test Driven Development) approach
	//first create test method and then develop the page class & its actions
	
	//when we click on search icon we will land in a results page(new page)
	//whenever we click on any button or link & it is opening a new page then the method should return the new page object
	//it is called method chaining
	public ResultsPage doSearch(String searchKey) {
		System.out.println("Search key ===> "+searchKey);
		WebElement searchEle = eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_SHORT_TIME_OUT);
		eleUtil.doSendKeys(searchEle, searchKey);
		eleUtil.doClick(searchIcon);
		return new ResultsPage(driver);
	}
}
