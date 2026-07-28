package com.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.login_page;
import com.pages.logout_page;
import com.pages.pmicadmin_page;
import com.utility.PropertyUtils;

public class pmicadmintestingclass extends BaseClass{
	pmicadmin_page pmicadmin = null;
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
		String pmicadminuser = PropertyUtils.readProperty("pmicadminuser");
		String pmicadminpassword = PropertyUtils.readProperty("pmicadminpassword");

		lp.loginToPmicAdmin(pmicadminuser, pmicadminpassword);
		Assert.assertEquals(driver.getTitle(), "PMIC");
		log.info("after successful PMIC admin login, page title is: " + driver.getTitle());
	}
	
	//@Test (priority=2)
	public void editRole() throws Exception {
		pmicadmin = new pmicadmin_page(driver);
		pmicadmin.selectUsersLink();
		Thread.sleep(3000);
		pmicadmin.clickRolesTab();
		Thread.sleep(2000);
		//pmicadmin.searchRoleAndEdit();
		pmicadmin.searchRoleAndDelete();
		
	}
	//@Test (priority=3)
	public void startExamOfRegistrant() throws Exception {
		pmicadmin = new pmicadmin_page(driver);
	
		pmicadmin.clickExamDashboardLink();
		pmicadmin.examdashClickFilterBtn();
		
		pmicadmin.examdashFilterbyEventAndCourse();
		
		pmicadmin.setupSearchUserAndStartExam();
		
	}
	
	
	//@Test (priority=4)
	public void activateRegistrant() throws Exception {
		pmicadmin = new pmicadmin_page(driver);
	
		pmicadmin.clickExamDashboardLink();
		pmicadmin.examdashClickFilterBtn();
		
		pmicadmin.examdashFilterbyEventAndCourse();
		
		//pmicadmin.setupSearchUserAndStartExam();
		pmicadmin.copyactivationSearchUser();
		
	}
	@Test (priority=5)
	public void startAndActivateExamOfRegistrant() throws Exception {
	
		pmicadmin = new pmicadmin_page(driver);
		
		pmicadmin.clickExamDashboardLink();
		pmicadmin.examdashClickFilterBtn();
		
		pmicadmin.examdashFilterbyEventAndCourse();
		
		pmicadmin.setupSearchUserAndStartExam();
		pmicadmin.copyactivationSearchUser();
		
		
	}
	}
	
	
	


