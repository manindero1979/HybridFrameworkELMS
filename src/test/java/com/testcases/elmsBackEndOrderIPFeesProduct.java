package com.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.models.Credentials;
import com.pages.login_page;
import com.pages.logout_page;
import com.pages.order_backendorder_page;
import com.utility.ExcelUtils;
import com.utility.PropertyUtils;

public class elmsBackEndOrderIPFeesProduct extends BaseClass {
	login_page lp = null;
	order_backendorder_page beo = null;
	logout_page logpage = null;

	@Test(priority = 1)
	public void setup() throws Exception {
		intialization();
		System.out.println("webdriver intialized");
		lp = new login_page(driver);
		log.info("************** Opening URL *****************");
	}

	@Test(priority = 2)
	public void login() throws Exception {

	    log.info("************** Verifying Login Test *****************");

	    Credentials admin = ExcelUtils.getCredentials("elms", "admin");

	    lp.loginToelms(admin.getUsername(), admin.getPassword());

	    Assert.assertEquals(driver.getTitle(), "Dashboard / Magento Admin");

	}


	@Test(priority = 3)
	public void placeOrder() throws Exception {
		beo = new order_backendorder_page(driver);
		log.info("I am in placeorder method");
		Thread.sleep(2000);// added on 31st jan 23 for preprod ins, after this it worked fine

		beo.clickOnOrdersMenu();
		Thread.sleep(1000);
		beo.clickCreateOrderLink();
		Thread.sleep(3000);
		beo.searchExistingCustomerEmail();
		Thread.sleep(5000);//---
		beo.selectSearchedCustomer();
		Thread.sleep(7000);//----
		beo.selectFeesFromDrpdown();
		Thread.sleep(5000);
		beo.selectTestingFees();
		Thread.sleep(5000);//---
		beo.clickAddSelectedProductToOrderBtn();
		Thread.sleep(15000);
		beo.clicOnAddRegistrantsBtn();
		Thread.sleep(5000);
		beo.addFeesRegistrants();
		Thread.sleep(2000);
		beo.saveRegistrant();
		Thread.sleep(3000);
		//beo.setFreeShipping();
		//Thread.sleep(2000);
		beo.setInternalPaymentoption();
		Thread.sleep(2000);
		beo.feeOrderSubmit();
		Thread.sleep(7000);
	}

	@Test(priority = 4)
	public void logout() throws Exception {
		logpage = new logout_page(driver);
		logpage.elmsLogoutApplication();
	}
}
