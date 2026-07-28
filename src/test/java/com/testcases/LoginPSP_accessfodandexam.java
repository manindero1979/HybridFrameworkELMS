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

public class LoginPSP_accessfodandexam extends BaseClass {
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
		intializationpsp();
		System.out.println("webdriver intialized");
	
		log.info("**************  launching psp student portal *****************");
	}


	
	@Test(priority = 2)
	public void loginODwithRecentlyActivatedStudent() throws Exception {
		log.info("************** Verifying Login Test *****************");
		Thread.sleep(2000);
		log.info("we are inside login method of OD student portal");
		
		  //String pmicstudentuser_data = PropertyUtils.readProperty("pmicstudentuser");
		String pmicstudentloginname_data =
		PropertyUtils.readProperty("pmicstudentloginname");
		
		String pmicstudentpassword_data =
		PropertyUtils.readProperty("pmicstudentpassword");
		 
		lp2 = new login_page(driver);
		lp2.loginToODStudent(pmicstudentloginname_data, pmicstudentpassword_data);
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Student Portal");
		log.info("after successful OD student login, page title is: " + driver.getTitle());
	}
	
	@Test(priority = 3)
	public void attemptcourse() throws Exception {
		odstudentpage = new odstudent_page(driver);
		Thread.sleep(3000);
		odstudentpage.clickGotToCourse();
		Thread.sleep(3000);
		odstudentpage.attemptFODCourseViaPSP();
		Thread.sleep(4000);
		odstudentpage.submitCertificationExam();
		Thread.sleep(2000);
		odstudentpage.certificationExamResult();
		
		
	}
	
	@Test (priority = 4)
	public void logoutstudent() throws Exception {
		logpage2 = new logout_page(driver);
		logpage2.odstudentLogoutApplication();
		Thread.sleep(4000);
		logpage2.pmicstudentLogoutApplication();
		
		
		
		driver.quit();
		//driver.close();
	
	
	}
	//@Test (priority = 5)
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