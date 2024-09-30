package com.qa.opencart.constants;

import java.util.List;

public class AppConstants {
	
	//we are storing the constant values here, by making them static, it will be stored in CMA & can be accessed using classname
	//making them final , no one can change the value of it
	
	public static final int DEFAULT_SHORT_TIME_OUT = 5;
	public static final int DEFAULT_MEDIUM_TIME_OUT = 10;
	public static final int DEFAULT_LONG_TIME_OUT = 20;
	
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
//	public static final String LOGIN_PAGE_TITLE = "Account Login11";
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	
	public static final int ACCOUNTS_PAGE_HEADERS_COUNT=4;
	
	public static final List<String> EXPECTED_ACC_PAGE_HEADERS_LIST = List.of("My Account","My Orders","My Affiliate Account","Newsletter");
	//List is a Interface, of is a method
	
	
	public static final String USER_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	
	public static final String REGISTER_PAGE_TITLE = "Register Account";
	
	public static final String SHOPPING_CART_PAGE_TITLE = "Shopping Cart";
	
	public static final String CHECK_OUT_PAGE_TITLE = "Checkout";
	
	public static final int PRODUCT_QTY = 2;
	

}
