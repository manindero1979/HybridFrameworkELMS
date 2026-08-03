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
import com.utility.ExcelUtils;
import com.utility.PropertyUtils;

public class elmsFrontEndOrder_DiscountCode extends BaseClass {

	createevent_frontendorder_page1 cefe = null;
	login_page lp = null;
	logout_page logpage = null;

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		intialization();
		System.out.println("webdriver intialized");
		//System.setProperty("webdriver.chrome.silentOutput","true");////////////
		lp = new login_page(driver);
		log.info("************** Opening URL *****************");
	}

	@Test(priority = 1)
	public void login() throws Exception {

	    log.info("************** Verifying Login Test *****************");

	    Credentials admin = ExcelUtils.getCredentials("elms", "admin");

	    lp.loginToelms(admin.getUsername(), admin.getPassword());

	    Assert.assertEquals(driver.getTitle(), "Dashboard / Magento Admin");

	}


	
	@Test(priority = 2)
	public void searchEvent() throws Exception {
		cefe = new createevent_frontendorder_page1(driver);
		Thread.sleep(2000);
		cefe.clickOnEventMenu();

		Thread.sleep(2000);
		cefe.clickOnAllEventsLink();
		Thread.sleep(15000);
		cefe.clearAllFilterLink();
		Thread.sleep(10000);
		cefe.clickOnFiltersBtn();
		Thread.sleep(5000);
		// cefe.setConfiguredEventNameFilter();
		/////
		cefe.setExistingEventNameFilter();
		Thread.sleep(7000);
	}
	

	@Test(priority = 3)
	public void placeFrontEndOrder() throws Exception {
		cefe.launchEventPurchaseLink();
		
		Thread.sleep(10000);
		String itemqty_data = PropertyUtils.readProperty("itemqty");
		cefe.setRegistrantItemQty(itemqty_data);
		Thread.sleep(7000);
		
		cefe.selectAddtocartAndProceedToRegistration();//added these 2 lines on 28th nov 24
		Thread.sleep(3000);
		
		if(itemqty_data.equals("1"))
		{cefe.generateReg1Email();
		cefe.setRegistrantONEInfo();
		}
		
		if (itemqty_data.equals("2"))
		{cefe.generateReg1Email();
		cefe.setRegistrantONEInfo();
		Thread.sleep(2000);
		cefe.generateReg2Email();
		cefe.setRegistrantTWOInfo();
		Thread.sleep(2000);
		}
		
		if (itemqty_data.equals("3"))
		{cefe.generateReg1Email();
		cefe.setRegistrantONEInfo();
		Thread.sleep(2000);
		cefe.generateReg2Email();
		cefe.setRegistrantTWOInfo();
		Thread.sleep(2000);
		cefe.generateReg3Email();
		cefe.setRegistrantTHREEInfo();
		Thread.sleep(2000);
		}
		
		
		cefe.applyCouponCode();
		Thread.sleep(20000);
		cefe.clickProceedToCheckoutBtnAfterDC();
		Thread.sleep(10000);
		//cefe.generateNewBillerEmail();
		//Thread.sleep(2000);
		//cefe.setNewBillingInfo();
		cefe.setBillingInfo();
		Thread.sleep(10000);
		
		cefe.placeOrder();
		Thread.sleep(2000);
	}

	@Test(priority = 4)
	public void logout() throws Exception {
		logpage = new logout_page(driver);
		logpage.elmsLogoutApplication();
	}

}
