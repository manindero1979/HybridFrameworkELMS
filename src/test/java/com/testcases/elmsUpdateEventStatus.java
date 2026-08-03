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
import com.pages.login_page;
import com.pages.logout_page;
import com.utility.ExcelUtils;
import com.utility.PropertyUtils;

public class elmsUpdateEventStatus extends BaseClass {

	createevent_frontendorder_page1 cefe = null;
	login_page lp = null;
	logout_page logpage = null;

	@BeforeClass//(alwaysRun = true)
	public void setup() throws Exception {
		intialization();
		System.out.println("webdriver intialized");
		//System.setProperty("webdriver.chrome.silentOutput","true");////////////
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
	public void searchEvent() throws Exception {
		cefe = new createevent_frontendorder_page1(driver);
		Thread.sleep(3000);
		cefe.clickOnEventMenu();

		Thread.sleep(2000);
		cefe.clickOnAllEventsLink();
		Thread.sleep(12000);
		cefe.clearAllFilterLink();
		Thread.sleep(10000);
		cefe.clickOnFiltersBtn();
		Thread.sleep(8000);
		
		cefe.setExistingEventNameFilter();//config file "existingeventname"
		Thread.sleep(8000);
		cefe.viewEvent();
		Thread.sleep(5000);
		cefe.updateEventStatusCancelled();
		Thread.sleep(10000);
	}
	
	@AfterClass
	public void logout() throws Exception {
		logpage = new logout_page(driver);
		logpage.elmsLogoutApplication();
	}

}
