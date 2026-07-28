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

public class elmsCreatePublicInpersonEvent extends BaseClass {
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
		eventpage.setPMPIAccount();
		String test_public_inperson_event_initial = PropertyUtils.readProperty("testpublicinpersonevent_initial");

		eventpage.generateEventName(test_public_inperson_event_initial);
		eventpage.setEventName();
		eventpage.setEventTitle();
		//String event_start_days_from_current_date = PropertyUtils.readProperty("event_startdays_fromcurrent_date");
		//int event_days_in_integer = Integer.valueOf(event_start_days_from_current_date);// check this
		eventpage.setEventStartDateTime();

		//String event_days_enddate_fromevent_startdate_string = PropertyUtils
		//		.readProperty("event_days_enddate_fromevent_startdate");
		//int event_days_enddate_fromevent_startdate_int = Integer.valueOf(event_days_enddate_fromevent_startdate_string);// check
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

		/*
		 * eventpage.addPMProducts(); String course_start_days_from_current_date_string
		 * = PropertyUtils .readProperty("course_startdays_fromcurrent_date"); int
		 * course_start_days_from_now_int =
		 * Integer.valueOf(course_start_days_from_current_date_string);
		 * eventpage.setCourseStartDateTime(course_start_days_from_now_int);
		 * 
		 * String course_end_days_string =
		 * PropertyUtils.readProperty("course_days_enddate_fromcourse_startdate"); int
		 * course_end_days_int = Integer.valueOf(course_end_days_string);
		 * eventpage.setCourseEndDateTime(course_end_days_int);
		 * 
		 * eventpage.setCourseSaleStartDate(); eventpage.setCourseSaleEndDate();
		 * eventpage.setCourseMaxQty();
		 */

		// for setting 2 products details
		eventpage.add2PMProducts();
		String course1_start_days_from_current_date_string = PropertyUtils .readProperty("course_startdays_fromcurrent_date");
		
		int course1_start_days_from_now_int = Integer.valueOf(course1_start_days_from_current_date_string);
		
		eventpage.set1CourseStartDateTime(course1_start_days_from_now_int);

		String course1_end_days_string = PropertyUtils.readProperty("course_days_enddate_fromcourse_startdate");
		
		int course1_end_days_int = Integer.valueOf(course1_end_days_string);
		
		eventpage.set1CourseEndDateTime(course1_end_days_int);
		eventpage.set1CourseSaleStartDate();
		eventpage.set1CourseSaleEndDate();
		eventpage.set1CourseMaxQty();

		String course2_start_days_from_current_date_string = PropertyUtils.readProperty("course_startdays_fromcurrent_date");

		int course2_start_days_from_now_int = Integer.valueOf(course2_start_days_from_current_date_string);

		eventpage.set2CourseStartDateTime(course2_start_days_from_now_int);

		String course2_end_days_string = PropertyUtils.readProperty("course_days_enddate_fromcourse_startdate");

		int course2_end_days_int = Integer.valueOf(course2_end_days_string);

		eventpage.set2CourseEndDateTime(course2_end_days_int);

		eventpage.set2CourseSaleStartDate();
		eventpage.set2CourseSaleEndDate();
		eventpage.set2CourseMaxQty();

		//eventpage.setPMTemplate();
		Thread.sleep(2000);
		//eventpage.saveEvent();
		eventpage.publishEvent();

	}

	@Test(priority = 3)
	public void logout() throws Exception {
		logpage = new logout_page(driver);
		logpage.elmsLogoutApplication();
	}
}
