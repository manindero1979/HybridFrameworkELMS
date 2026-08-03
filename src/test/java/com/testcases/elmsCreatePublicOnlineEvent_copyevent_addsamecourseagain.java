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
import com.pages.event_page;
import com.pages.login_page;
import com.pages.logout_page;
import com.utility.ExcelUtils;
import com.utility.PropertyUtils;

public class elmsCreatePublicOnlineEvent_copyevent_addsamecourseagain extends BaseClass {
	event_page eventpage = null;
	login_page lp = null;
	logout_page logpage = null;
	createevent_frontendorder_page1 cefe = null;

	@BeforeClass(alwaysRun = true)
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
		Thread.sleep(3000);
		//////

		cefe.clickOnCreateEvent();
		Thread.sleep(3000);
		cefe.setYesHideFromListing();
		cefe.setPublicEventType();
		Thread.sleep(2000);
		cefe.setNoTBDEvent();
		cefe.setOnlineDeliveryType(); // cefe.setPMICTestPlatform();
		Thread.sleep(2000);
		cefe.setPMPIAccount();
		String test_public_online_event_initial = PropertyUtils.readProperty("testpubliconlineevent_initial");

		cefe.generateEventName(test_public_online_event_initial);
		cefe.setEventName();
		Thread.sleep(2000);
		cefe.setEventTitle();

		cefe.setEventStartDateTime();

		// this
		cefe.setEventEndDateTime();
		cefe.clickProductAndPricingSection();
		Thread.sleep(3000);
		cefe.addPMProducts();
		Thread.sleep(2000);

		cefe.setCourseStartDateTime();

		cefe.setCourseEndDateTime();

		cefe.setCourseSaleStartDate();
		cefe.setCourseSaleEndDate();
		cefe.setCourseMaxQty();
		//Thread.sleep(2000);
		//cefe.setTemplate();
		Thread.sleep(2000);

		// cefe.saveEvent();
		cefe.publishEvent();
		Thread.sleep(7000);
		cefe.setPortalBetaYesFlag();
		Thread.sleep(2000);
		cefe.saveEvent();
	}

	@Test(priority = 3)
	public void copyEvent() throws Exception {
		Thread.sleep(5000);
		cefe.clickCopyEvent();
		Thread.sleep(2000);
		cefe.setCopyEventName();
		cefe.setEventTitle();
		Thread.sleep(2000);
		cefe.clickProductAndPricingSection();
		Thread.sleep(3000);
		cefe.addPMProducts();
		Thread.sleep(2000);
		
		
		cefe.setCourseStartDateTime();
		cefe.setCourseEndDateTime();
		cefe.setCourseSaleStartDate();
		cefe.setCourseSaleEndDate();
		cefe.setCourseMaxQty();
		Thread.sleep(2000);

	}

	@Test(priority = 4)
	public void logout() throws Exception {
		logpage = new logout_page(driver);
		logpage.elmsLogoutApplication();
	}

}
