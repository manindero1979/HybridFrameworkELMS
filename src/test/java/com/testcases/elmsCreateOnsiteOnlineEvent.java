package com.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.models.Credentials;
import com.pages.event_page;
import com.pages.login_page;
import com.pages.logout_page;
import com.utility.ExcelUtils;
import com.utility.PropertyUtils;

public class elmsCreateOnsiteOnlineEvent extends BaseClass {
	event_page eventpage = null;
	login_page lp = null;
	logout_page logpage=null;
	

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
	public void Create_Events() throws Exception {
		eventpage = new event_page(driver);
		Thread.sleep(3000);
		eventpage.clickOnEventMenu();
		Thread.sleep(2000);
		eventpage.clickOnCreateEvent();
		Thread.sleep(2000);

		eventpage.setOnsiteEventType();
		Thread.sleep(4000);
		eventpage.setNoTBDEvent();
		Thread.sleep(1000);
		eventpage.setOnlineDeliveryType();
		Thread.sleep(1000);
		// eventpage.setPMICTestPlatform();
		eventpage.setPMPIAccount();
		Thread.sleep(1000);
		String test_onsite_online_event_initial = PropertyUtils.readProperty("testonsiteonlineevent_initial");

		eventpage.generateEventName(test_onsite_online_event_initial);
		eventpage.setEventName();
		Thread.sleep(2000);
		eventpage.setEventTitle();
		Thread.sleep(1000);

		//String event_start_days_from_current_date = PropertyUtils.readProperty("event_startdays_fromcurrent_date");
		//int event_days_in_integer = Integer.valueOf(event_start_days_from_current_date);// check this
		eventpage.setEventStartDateTime();
		
		Thread.sleep(1000);
		//String event_days_enddate_fromevent_startdate_string = PropertyUtils.readProperty("event_days_enddate_fromevent_startdate");
		//int event_days_enddate_fromevent_startdate_int = Integer.valueOf(event_days_enddate_fromevent_startdate_string);// check this
		eventpage.setEventEndDateTime();
		Thread.sleep(2000);
		eventpage.setOnsiteAccount();
		Thread.sleep(4000);
		eventpage.clickProductAndPricingSection();
		Thread.sleep(4000);//increased time from 1000 to 3000 on 29th march
		eventpage.addPMProducts();
		Thread.sleep(2000);
		//String course_start_days_from_current_date_string = PropertyUtils
		//		.readProperty("course_startdays_fromcurrent_date");
		//int course_start_days_from_now_int = Integer.valueOf(course_start_days_from_current_date_string);
		eventpage.setCourseStartDateTime();
		Thread.sleep(1000);
		//String course_end_days_string = PropertyUtils.readProperty("course_days_enddate_fromcourse_startdate");
		//int course_end_days_int = Integer.valueOf(course_end_days_string);
		eventpage.setPMCourseEndDateTime();
		Thread.sleep(1000);
		eventpage.setPMCourseSaleStartDate();
		Thread.sleep(1000);
		eventpage.setPMCourseSaleEndDate();
		Thread.sleep(1000);
		eventpage.setPMCourseMaxQty();
		Thread.sleep(2000);
		// eventpage.saveEvent();
		eventpage.publishEvent();
		Thread.sleep(7000);
		eventpage.clickSODetailsSection();
		Thread.sleep(2000);
		eventpage.setSODetails();
		Thread.sleep(2000);
		eventpage.clickOrdersAndAttendees();
		Thread.sleep(2000);
		eventpage.clickAttendeesbutton();
		Thread.sleep(2000);
		eventpage.addattendee();
		Thread.sleep(2000);
		eventpage.saveEvent();
		Thread.sleep(2000);
	}
	
	@Test(priority = 3)
	public void logout() throws Exception {
		logpage = new logout_page(driver);
		logpage.elmsLogoutApplication();
	}

}
