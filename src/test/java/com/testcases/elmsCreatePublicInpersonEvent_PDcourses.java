package com.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.event_page;
import com.pages.login_page;
import com.pages.logout_page;
import com.utility.PropertyUtils;

public class elmsCreatePublicInpersonEvent_PDcourses extends BaseClass {
	event_page eventpage = null;
	login_page lp = null;
	logout_page logpage = null;

	public String eventname = "Test";
	public static int days = 5;

	@BeforeClass // (alwaysRun = true)
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
	public void Create_PublicInpersonEvents() throws Exception {
		eventpage = new event_page(driver);
		Thread.sleep(3000);
		eventpage.clickOnEventMenu();
		Thread.sleep(2000);
		eventpage.clickOnCreateEvent();
		Thread.sleep(2000);
		eventpage.setYesHideFromListing();
		eventpage.setPublicEventType();
		eventpage.setNoTBDEvent();
		eventpage.setInpersonDeliveryType();
		// eventpage.setPMICTestPlatform();
		eventpage.setPDPIAccount();
		String test_public_inperson_event_initial = PropertyUtils.readProperty("PDpublicinpersonevent_initial");

		eventpage.generateEventName(test_public_inperson_event_initial);
		eventpage.setEventName();
		eventpage.setEventTitle();
		eventpage.setEventStartDateTime();

																														// this
		eventpage.setEventEndDateTime();

		eventpage.setInpersonVenue();
		Thread.sleep(1000);
		eventpage.setInpersonVenueAddress();
		Thread.sleep(1000);
		eventpage.setInpersonVenueCity();
		Thread.sleep(1000);
		eventpage.setInpersonVenueZipcode();
		Thread.sleep(1000);
		eventpage.setInpersonVenueCountry();
		Thread.sleep(1000);
		eventpage.setInpersonVenueState();
		Thread.sleep(1000);
		eventpage.setInpersonVenuePhone();
		Thread.sleep(1000);
		eventpage.clickProductAndPricingSection();
		Thread.sleep(3000);
		eventpage.addPDProducts();
		eventpage.setPDCourseStartDateTime();
		eventpage.setPDCourseEndDateTime();
		eventpage.setPDCourseSaleStartDate();
		eventpage.setPDCourseSaleEndDate();
		eventpage.setPDCourseMaxQty();	
		//Thread.sleep(2000);
		//eventpage.setPDTemplate();
		Thread.sleep(5000);
		eventpage.publishEvent();
		Thread.sleep(7000);
	
		
	}

	@Test(priority = 3)
	public void logout() throws Exception {
		logpage = new logout_page(driver);
		logpage.elmsLogoutApplication();
	}
}
