package com.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.models.Credentials;
import com.pages.createevent_frontendorder_page1;
import com.pages.login_page;
import com.pages.logout_page;
import com.pages.order_backendorder_page;
import com.utility.ExcelUtils;
import com.utility.PropertyUtils;

public class elmsRefund_ExistingOrder extends BaseClass {

	createevent_frontendorder_page1 cefe = null;
	login_page lp = null;
	logout_page logpage = null;
	order_backendorder_page op=null;

	@BeforeClass//(alwaysRun = true)
	public void setup() throws Exception {
		intialization();
		System.out.println("webdriver intialized");
		lp = new login_page(driver);
		log.info("************** Opening URL *****************");
	}

	@Test(priority = 4)
	public void login() throws Exception {

	    log.info("************** Verifying Login Test *****************");

	    Credentials admin = ExcelUtils.getCredentials("elms", "admin");

	    lp.loginToelms(admin.getUsername(), admin.getPassword());

	    Assert.assertEquals(driver.getTitle(), "Dashboard / Magento Admin");

	}


	
	
	@Test(priority = 5)
	public void refundOrder() throws Exception {
		//orderid
		
		op = new order_backendorder_page(driver);
		op.clickOnOrdersMenu();
		Thread.sleep(2000);
		op.clickAllOrdersLink();
		Thread.sleep(5000);
		op.clearAllOrderFilterLink();
		Thread.sleep(5000);
		op.clickOnOrderFiltersBtn();
		Thread.sleep(3000);
		//op.setCustomerFilter();
		//op.setOrderProcessedStatusFilter();
		op.setOrderIdFilter();//this is for searching specific order id
		Thread.sleep(2000);
		op.clickApplyfilterBtn();		
		Thread.sleep(5000);
		op.selectFirstRecord();
		Thread.sleep(3000);
		op.clickCreditMemoLink();
		Thread.sleep(3000);
		op.setItemsToRefund();
		Thread.sleep(3000);
		op.setOrderDetails();
		Thread.sleep(2000);
		op.clickRefundOfflineButton();
		Thread.sleep(10000);
		op.confirmOrderRefund();
		
	}

	@AfterClass//(priority = 6)
	public void logout() throws Exception {
		logpage = new logout_page(driver);
		logpage.elmsLogoutApplication();
	}

}
