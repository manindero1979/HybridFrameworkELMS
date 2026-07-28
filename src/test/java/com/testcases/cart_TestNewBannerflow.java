package com.testcases;

import org.testng.annotations.BeforeClass;
import com.models.Credentials;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.account_page;
import com.pages.cart_page;
import com.pages.login_page;
import com.pages.logout_page;

public class cart_TestNewBannerflow extends BaseClass {
	account_page accpage = null;
	cart_page cartpage = null;
	login_page lp = null;
	logout_page logpage=null;
	

	@BeforeClass//(alwaysRun = true)
	public void setup() throws Exception {
		intializationcart();
		System.out.println("webdriver intialized");
		lp = new login_page(driver);
		log.info("************** Opening URL *****************");
	}

	@Test(priority = 1)
		public void Create_Accounts() throws Exception {
		cartpage = new cart_page(driver);
		Thread.sleep(7000);
		
		cartpage.selectProductTile();
		Thread.sleep(5000);
		cartpage.selectStartHereLink();
		Thread.sleep(3000);
		cartpage.selectPrivateTraining();
		
		
	}
	


}
