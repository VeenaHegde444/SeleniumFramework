package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchHeader = By.cssSelector("div#content h1");
	private By results = By.cssSelector("div.product-thumb");
	
	private By addToCart = By.xpath("(//button[@type='button']//span[contains(text(),'Add to Cart')])[position()=1]");

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);		
	}
	
	public String getSearchHeader() {
		String searchHeaderValue = eleUtil.waitForElementVisible(searchHeader, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
		return searchHeaderValue;
	}
	
	public int getSearchResultsCount() {
		int resultCount = eleUtil.waitForElementsVisible(results, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("search result count ===> "+resultCount);
		return resultCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		eleUtil.doClick(By.linkText(productName)); //here we are directly using By locator because productName is dynamic, there could be multiple 
		//products out of which we are choosing one product, we can't store in By locators, it is impossible to store all products if the count is
		//dynamic
		return new ProductInfoPage(driver);
	}
	

}
