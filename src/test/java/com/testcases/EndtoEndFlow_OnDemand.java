package com.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.cart_page;
import com.pages.login_page;
import com.pages.logout_page;
import com.pages.odadmin_page;
import com.pages.odstudent_page;
import com.utility.PropertyUtils;

public class EndtoEndFlow_OnDemand extends BaseClass {
	//private static final String[] handles = null;
	cart_page cartpage = null;
	login_page lp1 = null;
	login_page lp2 = null;
	logout_page logpage1=null;
	logout_page logpage2=null;
	logout_page logpage3=null;
	//createevent_frontendorder_page1 cefe = null;

	odstudent_page odstudentpage=null;
	odadmin_page odadminpage=null;
	//@BeforeSuite(alwaysRun = true)
	@Test(priority = 1)
	public void setupcart() throws Exception {
		intializationcart();
		System.out.println("webdriver intialized");
	
		log.info("************** cart url launching *****************");
	}



	
	@Test(priority = 2)
	public void Place_FODOrder() throws Exception {

		cartpage = new cart_page(driver);
		Thread.sleep(3000);
		cartpage.selectFODregistration();
		Thread.sleep(3000);
		cartpage.generateReg1Email();
		cartpage.setRegistrantONEInfo();

		cartpage.clickProceedToCheckoutBtn();
		Thread.sleep(7000);
		cartpage.setBillingInfo();
		//Thread.sleep(2000);
		
		Thread.sleep(7000);//changes done on 9 jan 2023
		cartpage.setCardDetails();
		Thread.sleep(3000);
		cartpage.placeOrder();
		Thread.sleep(10000);
		driver.quit();
		//driver.close();
		
	}
	
	@Test(priority = 3)
	public void setupod() throws Exception {
		intializationondemand();
		System.out.println("webdriver intialized");
		lp1 = new login_page(driver);
		log.info("************** Opening od URL *****************");
	}
	
	
	@Test(priority = 4)
	public void loginODAdmin() throws Exception {
		log.info("************** Verifying OD Login Test *****************");
		Thread.sleep(5000);
		log.info("we are inside login method of OD admin");
		String pmicadminuser = PropertyUtils.readProperty("pmicadminuser");
		String pmicadminpassword = PropertyUtils.readProperty("pmicadminpassword");

		lp1.loginToODAdmin(pmicadminuser, pmicadminpassword);
		Assert.assertEquals(driver.getTitle(), "LMS");
		log.info("after successful OD admin login, page title is: " + driver.getTitle());
	}
	
	
	
    @Test(priority = 5)
	public void odStudentActivation() throws Exception {
		Thread.sleep(2000);
		odadminpage = new odadmin_page(driver);
		odadminpage.searchStudent();
		odadminpage.ActivateRecentlyCreatedUser();
	
	}
	
	@Test(priority = 6)
	public void loginODwithRecentlyCreatedStudent() throws Exception {
		log.info("************** Verifying Login Test *****************");
		Thread.sleep(2000);
		log.info("we are inside login method of OD student portal");
		
		  //String pmicstudentuser_data = PropertyUtils.readProperty("pmicstudentuser");
		  String pmicstudentpassword_data =
		  PropertyUtils.readProperty("pmicstudentpassword");
		 
		  lp2 = new login_page(driver);
		lp2.loginToODStudent(registrant, pmicstudentpassword_data);
		Thread.sleep(3000);
		Assert.assertEquals(driver.getTitle(), "Student Portal");
		log.info("after successful OD student login, page title is: " + driver.getTitle());
	}
	
	@Test(priority = 7)
	public void attemptcourse() throws Exception {
		odstudentpage = new odstudent_page(driver);
		odstudentpage.clickGotToCourse();
		//Thread.sleep(6000);
		odstudentpage.attemptFODCourseViaPSP();
		Thread.sleep(4000);
		odstudentpage.submitCertificationExam();
		Thread.sleep(2000);
		odstudentpage.certificationExamResult();
		
		
	}
	
	@Test (priority = 8)
	public void logoutodstudent() throws Exception {
		logpage2 = new logout_page(driver);
		logpage2.odstudentLogoutApplication();
		Thread.sleep(4000);
		logpage2.pmicstudentLogoutApplication();
		
		//driver.quit();
		//driver.close();
	
	
	}
	@Test (priority = 9)
	public void logoutodadmin() throws Exception {
		
		driver.switchTo().window(oldTab);//to switch back to original od admin portal tab---added on 10th jan 2023
		Thread.sleep(2000);
		logpage3 = new logout_page(driver);
		logpage3.odadminLogoutApplication();//added on 10th jan 2023
		Thread.sleep(2000);
		driver.quit();
		//driver.close();
	
	}

}