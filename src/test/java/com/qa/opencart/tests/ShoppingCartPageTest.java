package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class ShoppingCartPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider

	public Object[][] getAddToCartData() {
		return new Object[][] { { "macbook", "MacBook Pro" } };
	}

	@Test(dataProvider = "getAddToCartData")
	public void getAddToCartTest(String searchKey, String productName) {
		resultsPage = accPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(productName);
		productInfoPage.updateQuantity();
		String successMessage = productInfoPage.addProductToCart();
		System.out.println(successMessage);
		Assert.assertEquals(successMessage, "Success: You have added " + productName + " to your shopping cart!");

		shoppingCartPage = productInfoPage.navigateToCartPage();

	}

	@Test
	public void shoppingCartPageTitleTest() {
		String accTitle = shoppingCartPage.getShoppingCartPageTitle();
		Assert.assertEquals(accTitle, AppConstants.SHOPPING_CART_PAGE_TITLE);
	}

//	@Test(priority = Integer.MAX_VALUE)
//	public void checkOutPageTitleTest() {
//		checkOutPage = shoppingCartPage.navigateToCheckoutPage();
//		String accTitle = checkOutPage.getCheckOutPageTitle();
//		Assert.assertEquals(accTitle, AppConstants.CHECK_OUT_PAGE_TITLE);
//	}

}
