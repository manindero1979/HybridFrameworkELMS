package com.testcases;

//import org.openqa.selenium.remote.server.handler.SwitchToWindow;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.createevent_frontendorder_page1;
import com.pages.event_page;
import com.pages.login_page;
import com.pages.logout_page;
import com.pages.pmicadmin_page;
import com.pages.pmicstudent_page;
import com.utility.PropertyUtils;

public class EndtoEndFlow_PMIC extends BaseClass {
	//private static final String[] handles = null;
	event_page eventpage = null;
	login_page lp = null;
	logout_page logpage1=null;
	logout_page logpage2=null;
	logout_page logpage3=null;
	createevent_frontendorder_page1 cefe = null;
	public pmicadmin_page pmicadmin = null;
	pmicstudent_page pmicstudent = null;
	
	//@BeforeSuite(alwaysRun = true)
	@Test(priority = 1)
	public void setupelms() throws Exception {
		intialization();
		System.out.println("webdriver intialized");
		lp = new login_page(driver);
		log.info("************** Opening URL *****************");
	}

	@Test(priority = 2)
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

	
	@Test(priority = 3)
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
		Thread.sleep(1000);
		cefe.addPMProducts();
		Thread.sleep(2000);

		cefe.setCourseStartDateTime();

		cefe.setCourseEndDateTime();

		cefe.setCourseSaleStartDate();
		cefe.setCourseSaleEndDate();
		cefe.setCourseMaxQty();	
		Thread.sleep(2000);
		//cefe.setTemplate();
		//Thread.sleep(5000);
		
		
		//cefe.saveEvent();
		cefe.publishEvent();
		Thread.sleep(7000);
		//cefe.setPortalBetaYesFlag();
		//Thread.sleep(2000);
		//cefe.saveEvent();
		
		
		//below methpds
		//cefe.clickProductAndPricingSection();
		//cefe.setSalestartDateAsYesterdaysDate();
		//cefe.saveEvent();
	}
	@Test(priority = 4)
	public void searchEvent() throws Exception {
		//cefe = new createevent_frontendorder_page1(driver);
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
		cefe.searchNewlyCreatedEvent();
		Thread.sleep(7000);
	}
	

	@Test(priority = 5)
	public void placeFrontEndOrder() throws Exception {
		cefe.launchEventPurchaseLink();
		Thread.sleep(10000);
		cefe.setRegistrantItemQty("1");
		Thread.sleep(3000);
		cefe.generateReg1Email();
		cefe.setRegistrantONEInfo();
		
		//cefe.applyCouponCode();
		Thread.sleep(20000);
		cefe.clickProceedToCheckoutBtn();
		Thread.sleep(10000);
		cefe.setBillingInfo();
		Thread.sleep(10000);
		cefe.setCardDetails();
		cefe.placeOrder();
		Thread.sleep(2000);
	}
	
	@Test(priority = 6)
	public void logoutelms() throws Exception {
		logpage1 = new logout_page(driver);
		logpage1.elmsLogoutApplication();
		
	}
	
	@Test(priority = 7)
	public void setuppmic() throws Exception {
		intializationpmic();
		System.out.println("webdriver intialized");
		lp = new login_page(driver);
		log.info("************** Opening URL *****************");
	}

	@Test(priority = 8)
	public void loginPMIC() throws Exception {
		log.info("************** Verifying Login Test *****************");
		Thread.sleep(5000);
		log.info("we are inside login method of PMIC admin");
		String pmicadminuser = PropertyUtils.readProperty("pmicadminuser");
		String pmicadminpassword = PropertyUtils.readProperty("pmicadminpassword");

		lp.loginToPmicAdmin(pmicadminuser, pmicadminpassword);
		Assert.assertEquals(driver.getTitle(), "PMIC");
		log.info("after successful PMIC admin login, page title is: " + driver.getTitle());
	}
	
	@Test (priority=9)
	public void startAndActivateExamOfRegistrant() throws Exception {
	
		pmicadmin = new pmicadmin_page(driver);
		
		
		pmicadmin.examdashClickFilterBtn();
		
		pmicadmin.examdashFilterbyNewlyCreatedEventAndCourse();
		
		//pmicadmin.setupSearchUserAndStartExam();

		
		pmicadmin.setupRecentlyCreatedUserAndStartExam();
		pmicadmin.ActivateRecentlyCreatedUser();
	
	}
	
	@Test (priority=10)
	public void loginPMICwithRecentlyCreatedStudent() throws Exception {
		log.info("************** Verifying Login Test *****************");
		Thread.sleep(3000);
		log.info("we are inside login method of PMIC admin");
		
		  //String pmicstudentuser_data = PropertyUtils.readProperty("pmicstudentuser");
		  String pmicstudentpassword_data =
		  PropertyUtils.readProperty("pmicstudentpassword");
		 
		lp = new login_page(driver);///added this on 6th jan 2023
		  
		lp.loginToPmicStudent(registrant, pmicstudentpassword_data);
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Library");
		log.info("after successful PMIC admin login, page title is: " + driver.getTitle());
	}
	
	@Test (priority=11)
	public void attemptExam() throws Exception {
		pmicstudent = new pmicstudent_page(driver);
		pmicstudent.startCourseExam();
		Thread.sleep(5000);
		pmicstudent.submitCourseExam();
		Thread.sleep(5000);
		pmicstudent.examresult();
	}
	@Test (priority=12)
	public void logoutpmicstudent() throws Exception {
		logpage2 = new logout_page(driver);
		logpage2.pmicstudentLogoutApplication();
		Thread.sleep(2000);
		driver.close();
	
	}
	
	@Test (priority=13)
	public void logoutPMICAdmin() throws Exception {
		
		driver.switchTo().window(oldTab);//---added on 10th jan 2023
		log.info("we are inside logoutPMICAdmin");
		
		
		logpage3 = new logout_page(driver);
		logpage3.pmicadminLogoutApplication();//added on 10th jan 2023
		Thread.sleep(2000);
		driver.close();
		
		
		//commented on 10th jan 2023
		/*
		 * //BaseClass.driver.switchTo().window(tabs.get(0));
		 * driver.switchTo().window(tabs.get(0));
		 * 
		 * //log.info(" done with switching code"); Thread.sleep(2000);
		 * logpage1.pmicadminLogoutApplication();
		 */
		
	}
	

}
