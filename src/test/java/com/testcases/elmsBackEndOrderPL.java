package com.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.models.Credentials;
import com.pages.logout_page;
import com.pages.order_backendorder_page;
import com.pages.login_page;
import com.utility.ExcelUtils;
import com.utility.PropertyUtils;

public class elmsBackEndOrderPL extends BaseClass {
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

		beo.clickOnOrdersMenu();
		Thread.sleep(1000);
		beo.clickCreateOrderLink();
		Thread.sleep(3000);

		beo.generateCustomerWorkEmail();
		beo.createNewCustomer();
		Thread.sleep(20000);
		
		beo.searchCustomerEmail();
		Thread.sleep(3000);//---
		beo.selectSearchedCustomer();
		Thread.sleep(4000);//----
		beo.selectProductFromDrpdown();
		Thread.sleep(7000);
		beo.searchEventProduct();
		Thread.sleep(4000);//---------
		beo.clickOnProductResult();
		Thread.sleep(3000);//-----
		beo.addProductQty("3");
		Thread.sleep(3000);//---
		beo.clickOKButton();
		Thread.sleep(5000);//---
		beo.clickAddSelectedProductToOrderBtn();
		Thread.sleep(10000);
		//beo.setAdjustedPrice();
		Thread.sleep(3000);
		beo.clickUpdateItemsAndQtyBtn();
		Thread.sleep(7000);///10 to 7
		beo.clicOnAddRegistrantsBtn();
		Thread.sleep(5000);
		beo.generateNewRegistrantsEmail();
		beo.createNewRegistrant1();
		Thread.sleep(1000);
		beo.addExistingRegistrant2();
		Thread.sleep(1000);
		beo.addBuyerRegistrant3();
		Thread.sleep(4000);
		beo.saveRegistrant();
		Thread.sleep(4000);
		//beo.setFreeShipping();
		//Thread.sleep(3000);
		beo.setPaylaterPaymentoption();
		Thread.sleep(4000);
		beo.order_submit();
		Thread.sleep(7000);
	}

	@Test(priority = 4)
	public void logout() throws Exception {
		logpage = new logout_page(driver);
		logpage.elmsLogoutApplication();
	}
}
