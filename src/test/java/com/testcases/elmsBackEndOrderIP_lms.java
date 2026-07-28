package com.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.login_page;
import com.pages.logout_page;
import com.pages.order_backendorder_page;
import com.utility.PropertyUtils;

public class elmsBackEndOrderIP_lms extends BaseClass {
	login_page lp = null;
	order_backendorder_page beo = null;
	logout_page logpage = null;

	// @BeforeClass//(alwaysRun = true)
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
		Thread.sleep(5000);
		log.info("we are inside login method of createaccounts class");
		String elmsadminuser = PropertyUtils.readProperty("elmsadminuser");
		String elmsadminpassword = PropertyUtils.readProperty("elmsadminpassword");

		lp.loginToelms(elmsadminuser, elmsadminpassword);
		Assert.assertEquals(driver.getTitle(), "Dashboard / Magento Admin");
		log.info("after successful login page title is: " + driver.getTitle());
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

		beo.generateCustomerWorkEmail();
		beo.createNewCustomer();
		Thread.sleep(5000);

		beo.searchCustomerEmail();
		Thread.sleep(3000);// ---
		beo.selectSearchedCustomer();
		Thread.sleep(3000);// ----
		beo.selectProductFromDrpdown();
		Thread.sleep(7000);// ----
		beo.searchLMSProduct();
		Thread.sleep(2000);
		beo.clickOnLMSResult();
	
		beo.clickAddSelectedProductToOrderBtn();
		Thread.sleep(7000);//updated on 8aug 23
		beo.setAdjustedPrice();
		
		beo.clickUpdateItemsAndQtyBtn();
		Thread.sleep(10000);
		beo.clicOnAddRegistrantsBtn();
		Thread.sleep(5000);
		beo.generateNewRegistrantsEmail();
		beo.createNewRegistrant();
		Thread.sleep(4000);
		beo.saveRegistrant();
		Thread.sleep(5000);
		beo.setFreeShipping();
		Thread.sleep(5000);
		beo.setInternalPaymentoption();
		Thread.sleep(5000);
		beo.order_submit();
	
	}

	// @AfterClass//(priority = 3)
	@Test(priority = 4)
	public void logout() throws Exception {
		logpage = new logout_page(driver);
		logpage.elmsLogoutApplication();
	}
}
