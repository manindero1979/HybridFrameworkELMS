package com.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.login_page;
import com.pages.logout_page;
import com.pages.pmicstudent_page;
import com.utility.PropertyUtils;

public class pmicstudent_AttemptExam extends BaseClass{
	pmicstudent_page pmicstudent = null;
	login_page lp = null;
	logout_page logpage=null;
	
	
	@BeforeClass//(alwaysRun = true)
	public void setup() throws Exception {
		intializationpmic();
		System.out.println("webdriver intialized");
		lp = new login_page(driver);
		log.info("************** Opening URL *****************");
	}

	@Test(priority = 1)
	public void loginPMIC() throws Exception {
		log.info("************** Verifying Login Test *****************");
		Thread.sleep(5000);
		log.info("we are inside login method of PMIC admin");
		String pmicstudentuser_data = PropertyUtils.readProperty("pmicstudentuser");
		String pmicstudentpassword_data = PropertyUtils.readProperty("pmicstudentpassword");

		lp.loginToPmicStudent(pmicstudentuser_data, pmicstudentpassword_data);
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Library");
		log.info("after successful PMIC admin login, page title is: " + driver.getTitle());
	}
	
	public void loginPMICwithRecentlyCreatedUser() throws Exception {
		log.info("************** Verifying Login Test *****************");
		Thread.sleep(5000);
		log.info("we are inside login method of PMIC admin");
		
		  //String pmicstudentuser_data = PropertyUtils.readProperty("pmicstudentuser");
		  String pmicstudentpassword_data =
		  PropertyUtils.readProperty("pmicstudentpassword");
		 

		lp.loginToPmicStudent(registrant, pmicstudentpassword_data);
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), "Library");
		log.info("after successful PMIC admin login, page title is: " + driver.getTitle());
	}
	
	@Test (priority=2)
	public void startexam() throws Exception {
		pmicstudent = new pmicstudent_page(driver);
		pmicstudent.startCourseExam("Price");
		Thread.sleep(5000);
		pmicstudent.submitCourseExam();
		Thread.sleep(5000);
		pmicstudent.examresult();
	}
	
	

}
