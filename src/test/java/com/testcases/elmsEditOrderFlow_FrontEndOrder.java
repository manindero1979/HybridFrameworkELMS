package com.testcases;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.models.Credentials;
import com.pages.contacts_page;
import com.pages.createevent_frontendorder_page1;
import com.pages.login_page;
import com.pages.logout_page;
import com.pages.order_backendorder_page;
import com.utility.ExcelUtils;
import com.utility.PropertyUtils;
//**********************this is for engp-547 and 608******************************
public class elmsEditOrderFlow_FrontEndOrder extends BaseClass {

	//placefrontendorderandsearchit_page cefe = null;
	createevent_frontendorder_page1 cefe = null;
	order_backendorder_page op=null;
	login_page lp = null;
	logout_page logpage = null;
	contacts_page cp=null;
	String q;
	JavascriptExecutor jse = (JavascriptExecutor) driver;

	@BeforeClass//(alwaysRun = true)
	public void setup() throws Exception {
		intialization();
		System.out.println("webdriver intialized");
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
		cefe.clearAllEventFilterLink();
		Thread.sleep(10000);
		cefe.clickOnEventFiltersBtn();
		Thread.sleep(5000);
		cefe.setConfiguredEventNameFilter();//testeventselectedforordercases
	
		//cefe.setNewlyCreatedEventNameFilter();
		Thread.sleep(5000);
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
		
		cefe.clickProceedToCheckoutBtn();
		Thread.sleep(5000);
		cefe.generateNewBillerEmail();
		Thread.sleep(2000);
		cefe.setNewBillingInfo();
		Thread.sleep(10000);
		cefe.setCardDetails();
		Thread.sleep(3000);
		cefe.placeOrder();
		Thread.sleep(2000);
	}
	
	@Test(priority=4)
	public void searchOrderAndEdit() throws Exception {
		op = new order_backendorder_page(driver);
		op.clickOnOrdersMenu();
		Thread.sleep(3000);
		op.clickAllOrdersLink();
		Thread.sleep(10000);
		op.clearAllOrderFilterLink();
		Thread.sleep(10000);
		op.clickOnOrderFiltersBtn();
		Thread.sleep(3000);
		op.setNewlyCreatedOrderIDFilter();
		op.clickApplyfilterBtn();
		Thread.sleep(3000);
		op.clickViewOrderLink();
		op.updateorderaccountandMasterAccount();
		Thread.sleep(1000);
		op.backToOrderlistPage();
		Thread.sleep(2000);
		op.orderGridScreenshot();
	}
	
	//@Test(priority=5)
	public void searchContact() throws Exception {
		cp = new contacts_page(driver);
		cp.clickOnContactsMenu();
		Thread.sleep(2000);
		cp.selectAllContacts();
		Thread.sleep(5000);
		cp.clearAllContactFilterLink();
		Thread.sleep(2000);
		cp.clickOnContactFiltersBtn();
		Thread.sleep(2000);
		cp.setBillerEmailFilter();
		Thread.sleep(2000);
		cp.clickOnContactApplyFiltersBtn();
		Thread.sleep(2000);
		cp.clickOnSelectContact();
		Thread.sleep(2000);
		cp.clickViewContact();
		Thread.sleep(4000);
		cp.checkContactCompany();
		//jse.executeScript("scroll(0, 150);");
		
		
		cp.clickBackToContactListPage();
		Thread.sleep(2000);
	}
	

	@Test(priority = 6)
	public void logout() throws Exception {
		logpage = new logout_page(driver);
		logpage.elmsLogoutApplication();
	}

}
