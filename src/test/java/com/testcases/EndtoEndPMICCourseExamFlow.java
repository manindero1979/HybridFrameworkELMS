package com.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.models.Credentials;
import com.pages.createevent_frontendorder_page1;
import com.pages.event_page;
import com.pages.login_page;
import com.pages.logout_page;
import com.pages.pmicadmin_page;
import com.pages.pspstudent_page;
import com.utility.ExcelUtils;
import com.utility.PropertyUtils;

public class EndtoEndPMICCourseExamFlow extends BaseClass {
	event_page eventpage = null;
	login_page lp = null;
	logout_page logpage1=null;
	logout_page logpage2=null;
	logout_page logpage3=null;
	createevent_frontendorder_page1 cefe = null;
	pmicadmin_page pmicadmin = null;
	pspstudent_page pspstudent = null;
	
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

	    Credentials admin = ExcelUtils.getCredentials("elms", "admin");

	    lp.loginToelms(admin.getUsername(), admin.getPassword());

	    Assert.assertEquals(driver.getTitle(), "Dashboard / Magento Admin");

	}


	
	@Test(priority = 3)
	public void Create_PublicOnlineEvent() throws Exception {

		cefe = new createevent_frontendorder_page1(driver);
		Thread.sleep(5000);
		cefe.clickOnEventMenu();
		Thread.sleep(3000);
	
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
		Thread.sleep(2000);
		cefe.setEventStartDateTime();

		Thread.sleep(2000);																											// this
		cefe.setEventEndDateTime();
		Thread.sleep(2000);
		cefe.clickProductAndPricingSection();
		Thread.sleep(3000);
		cefe.addPMProducts();
		Thread.sleep(2000);

		cefe.setCourseStartDateTime();
		Thread.sleep(1000);
		cefe.setCourseEndDateTime();
		Thread.sleep(1000);
		cefe.setCourseSaleStartDate();
		Thread.sleep(3000);
		cefe.setCourseSaleEndDate();
		Thread.sleep(1000);
		cefe.setCourseMaxQty();	
		Thread.sleep(2000);
		//cefe.setTemplate();
		//Thread.sleep(5000);
		
		cefe.publishEvent();
		Thread.sleep(2000);
		
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
		Thread.sleep(6000);
		cefe.selectAddtocartAndProceedToRegistration();
		Thread.sleep(3000);
		cefe.generateReg1Email();
		cefe.setRegistrantONEInfo();
		
	
		cefe.applyCouponCode();
		Thread.sleep(10000);
		log.info("I waited for 10 sec" );
		
		cefe.clickProceedToCheckoutBtnAfterDC();
		Thread.sleep(5000);
		cefe.setBillingInfo();
		Thread.sleep(15000);
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
		Thread.sleep(5000);
		pmicadmin.ActivateRecentlyCreatedUser();
	
	}

	/*
	 * @Test (priority=10) public void setuppsp() throws Exception {
	 * intializationpsp(); System.out.println("webdriver intialized"); lp = new
	 * login_page(driver); log.info("************** Opening URL *****************");
	 * }
	 */
	@Test (priority=11)
	public void loginPMICwithRecentlyCreatedStudent() throws Exception {
		log.info("************** Verifying Login Test *****************");
		Thread.sleep(3000);
		log.info("we are inside login method of PMIC admin");
		
		  //String pmicstudentuser_data = PropertyUtils.readProperty("pmicstudentuser");
		  String pmicstudentpassword_data =
		  PropertyUtils.readProperty("pmicstudentpassword");
		 
		lp = new login_page(driver);///added this on 6th jan 2023
		  
		lp.loginToPmicStudent(registrant, pmicstudentpassword_data);
		Thread.sleep(7000);
		//Assert.assertEquals(driver.getTitle(), "Student Portal");
		log.info("after successful login to merge url, page title is: " + driver.getTitle());
	}
	
	@Test (priority=12)
	public void attemptExam() throws Exception {
		pspstudent = new pspstudent_page(driver);
		pspstudent.takeCourseExam();///////////////
		Thread.sleep(5000);
		pspstudent.submitCourseExam();
		Thread.sleep(5000);
		pspstudent.examresult();
		Thread.sleep(3000);
	}
	@Test (priority=13)
	public void logoutpmicstudent() throws Exception {
		logpage2 = new logout_page(driver);
		logpage2.pspstudentLogoutApplication();
		Thread.sleep(2000);
		driver.close();
	
	}
	
	@Test (priority=14)
	public void logoutPMICAdmin() throws Exception {
		
		driver.switchTo().window(oldTab);//---added on 10th jan 2023
		log.info("we are inside logoutPMICAdmin");
		
		
		logpage3 = new logout_page(driver);
		logpage3.pmicadminLogoutApplication();//added on 10th jan 2023
		Thread.sleep(2000);
		driver.close();
	}
	

}
