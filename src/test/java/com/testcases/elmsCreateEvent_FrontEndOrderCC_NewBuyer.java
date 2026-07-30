package com.testcases;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.createevent_frontendorder_page1;
import com.pages.login_page;
import com.pages.logout_page;
import com.utility.PropertyUtils;

public class elmsCreateEvent_FrontEndOrderCC_NewBuyer extends BaseClass {

	createevent_frontendorder_page1 cefe = null;
	login_page lp = null;
	logout_page logpage = null;
	String q;

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
		Thread.sleep(5000);
		log.info("we are inside login method of createaccounts class");
		String elmsadminuser = PropertyUtils.readProperty("elmsadminuser");
		String elmsadminpassword = PropertyUtils.readProperty("elmsadminpassword");

		lp.loginToelms(elmsadminuser, elmsadminpassword);
		Assert.assertEquals(driver.getTitle(), "Dashboard / Magento Admin");
		log.info("after successful login page title is: " + driver.getTitle());
	}

	@Test(priority = 2)
	public void Create_PublicOnlineEvent() throws Exception {

		cefe = new createevent_frontendorder_page1(driver);
		Thread.sleep(3000);
		cefe.clickOnEventMenu();
		Thread.sleep(2000);
	

		cefe.clickOnCreateEvent();
		Thread.sleep(2000);
		cefe.setYesHideFromListing();
		cefe.setPublicEventType();
		cefe.setNoTBDEvent();
		cefe.setOnlineDeliveryType(); // cefe.setPMICTestPlatform();
		cefe.setPMPIAccount();
		String test_public_online_event_initial = PropertyUtils.readProperty("testpubliconlineevent_initial");

		cefe.generateEventName(test_public_online_event_initial);
		cefe.setEventName();
		cefe.setEventTitle();
		cefe.setEventStartDateTime();																			// this
		cefe.setEventEndDateTime();
		//Thread.sleep(3000);
		
		cefe.clickProductAndPricingSection();
		cefe.addPMProducts();
		cefe.setCourseStartDateTime();
		cefe.setCourseEndDateTime();
		cefe.setCourseSaleStartDate();
		cefe.setCourseSaleEndDate();
		cefe.setCourseMaxQty();
		//cefe.setTemplate();
		Thread.sleep(10000);
		
		
		// cefe.saveEvent();
		cefe.publishEvent();
		Thread.sleep(5000);
		}
		
	
	@Test(priority = 3)
	public void searchEvent() throws Exception {
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
		cefe.setNewlyCreatedEventNameFilter();
		Thread.sleep(5000);
	}
	
	
	@Test(priority = 4)
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
		Thread.sleep(3000);
		cefe.setCardDetails();
		Thread.sleep(2000);
		cefe.placeOrder();
		Thread.sleep(2000);
	}

	@AfterClass//(priority = 5)
	public void logout() throws Exception {
		logpage = new logout_page(driver);
		logpage.elmsLogoutApplication();
	}

}
