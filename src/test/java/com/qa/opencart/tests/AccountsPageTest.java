package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.ShoppingCartPage;

public class AccountsPageTest extends BaseTest{
	
	//to come to AccountsPage & verify the things, pre-requisite is user should be logged in
	
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		String accTitle = accPage.getAccountsPageTitle();
		Assert.assertEquals(accTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void accPageHeadersCountTest() {
		Assert.assertEquals(accPage.getTotalAccountsPageHeader(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> actualHeadersList = accPage.getAccPageHeaders();
		Assert.assertEquals(actualHeadersList, AppConstants.EXPECTED_ACC_PAGE_HEADERS_LIST);
	}
	
	
	@DataProvider
	public Object[][] getSearchKey(){
		return new Object[][] {
			{"macbook",3},
			{"imac",1},
			{"samsung",2}
		};
	}
	
	@Test(dataProvider = "getSearchKey")
	public void searchCountTest(String searchKey, int searchCount) {
		resultsPage = accPage.doSearch(searchKey);
		Assert.assertEquals(resultsPage.getSearchResultsCount(), searchCount);
	}
	
	
	@DataProvider
	public Object[][] getSearchData(){
		return new Object[][] {
			{"macbook","MacBook"},
			{"macbook","MacBook Air"},
			{"macbook","MacBook Pro"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"},
		};
	}
	
	@Test(dataProvider = "getSearchData")
	public void searchTest(String searchKey, String productName) {
		resultsPage = accPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductInfoPageHeader(), productName);
	}
	
	
//	@DataProvider
//	
//	public Object[][] getAddToCartData(){
//		return new Object[][]{
//		{"macbook","MacBook Pro"}
//		};
//	}
//	
//	@Test(dataProvider = "getAddToCartData")
//	public void getAddToCartTest(String searchKey,String productName) {
//		resultsPage = accPage.doSearch(searchKey);
//		productInfoPage = resultsPage.selectProduct(productName);
//		productInfoPage.enterQuantity();
//		shoppingCartPage = productInfoPage.addProductToCart();		
//		
//	}
//	
//	
//	@Test
//	public void addQuantityAndAddToCartTest() {
//		productInfoPage.enterQuantity();
//		Assert.assertEquals(false, null);
//	}
//	@Test
//	public void getProductInfoPageHeaderTest() {
//		
//	}
//
//	@Test
//	public void shoppingCartPageTitleTest() {
//		String accTitle = shoppingCartPage.getShoppingCartPageTitle();
//		Assert.assertEquals(accTitle, AppConstants.SHOPPING_CART_PAGE_TITLE);
//	}
//	
//
//
//	@Test(priority = Integer.MAX_VALUE)
//	public void checkOutPageTitleTest() {
//		checkOutPage = shoppingCartPage.navigateToCheckoutPage();
//		String accTitle = checkOutPage.getCheckOutPageTitle();
//		Assert.assertEquals(accTitle, AppConstants.CHECK_OUT_PAGE_TITLE);
//	}
}
