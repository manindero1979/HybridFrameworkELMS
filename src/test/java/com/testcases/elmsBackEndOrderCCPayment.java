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

public class elmsBackEndOrderCCPayment extends BaseClass {
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

		beo.generateCustomerWorkEmail();
		beo.createNewCustomer();
		Thread.sleep(5000);

		beo.searchCustomerEmail();
		Thread.sleep(3000);// ---
		beo.selectSearchedCustomer();
		Thread.sleep(3000);// ----
		beo.selectProductFromDrpdown();
		Thread.sleep(7000);// ----
		beo.searchEventProduct();
		Thread.sleep(2000);// ---------
		beo.clickOnProductResult();
	
		beo.addProductQty("1");
		Thread.sleep(3000);//---
		beo.clickOKButton();
		Thread.sleep(5000);//---
		beo.clickAddSelectedProductToOrderBtn();
		Thread.sleep(10000);
		beo.clicOnAddRegistrantsBtn();
		Thread.sleep(5000);
		beo.generateNewRegistrantsEmail();
		beo.createNewRegistrant();
		Thread.sleep(3000);

		beo.saveRegistrant();
		Thread.sleep(5000);
		beo.setFreeShipping();
		Thread.sleep(5000);
		beo.setCCPaymentoption();
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
