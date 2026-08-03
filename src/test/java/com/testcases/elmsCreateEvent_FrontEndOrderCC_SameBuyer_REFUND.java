package com.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
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

public class elmsCreateEvent_FrontEndOrderCC_SameBuyer_REFUND extends BaseClass {

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

	@Test(priority = 1)
	public void login() throws Exception {

	    log.info("************** Verifying Login Test *****************");

	    Credentials admin = ExcelUtils.getCredentials("elms", "admin");

	    lp.loginToelms(admin.getUsername(), admin.getPassword());

	    Assert.assertEquals(driver.getTitle(), "Dashboard / Magento Admin");

	}


	@Test(priority = 2)
	public void Create_PublicOnlineEvent() throws Exception {

		cefe = new createevent_frontendorder_page1(driver);
		Thread.sleep(3000);
		cefe.clickOnEventMenu();
		Thread.sleep(2000);
		//////

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

		cefe.setEventStartDateTime();
		Thread.sleep(1000);
																														// this
		cefe.setEventEndDateTime();
		Thread.sleep(1000);
		cefe.clickProductAndPricingSection();
		Thread.sleep(3000);
		cefe.addPMProducts();
		Thread.sleep(5000);

		cefe.setCourseStartDateTime();
		Thread.sleep(1000);

		cefe.setCourseEndDateTime();
		Thread.sleep(1000);

		cefe.setCourseSaleStartDate();
		Thread.sleep(1000);
		cefe.setCourseSaleEndDate();
		Thread.sleep(1000);
		cefe.setCourseMaxQty();
		//cefe.setTemplate();
		Thread.sleep(1000);
		
		
		// cefe.saveEvent();
		cefe.publishEvent();
		Thread.sleep(7000);
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
		cefe.setBillingInfo();
		//Thread.sleep(2000);
		
		Thread.sleep(10000);
		cefe.setCardDetails();
		Thread.sleep(2000);
		cefe.placeOrder();
		Thread.sleep(2000);
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
		Thread.sleep(5000);
		op.setNewlyCreatedOrderIDFilter();
		Thread.sleep(2000);
		op.clickApplyfilterBtn();
		Thread.sleep(10000);
		op.clickViewOrderLink();
		Thread.sleep(10000);
		op.clickCreditMemoLink();
		Thread.sleep(7000);
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
