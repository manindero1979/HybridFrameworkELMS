package com.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.account_page;
import com.pages.login_page;
import com.pages.logout_page;
import com.utility.PropertyUtils;

public class elmsCreateAccounts extends BaseClass {
	account_page accpage = null;
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
		Thread.sleep(5000);
		log.info("we are inside login method of createaccounts class");
		String elmsadminuser = PropertyUtils.readProperty("elmsadminuser");
		String elmsadminpassword = PropertyUtils.readProperty("elmsadminpassword");

		lp.loginToelms(elmsadminuser, elmsadminpassword);
		Assert.assertEquals(driver.getTitle(), "Dashboard / Magento Admin");
		log.info("after successful login page title is: " + driver.getTitle());
	}

	@Test(priority = 2)
	public void Create_Accounts() throws Exception {
		accpage = new account_page(driver);
		Thread.sleep(3000);
		accpage.clickOnAccountsMenu();
		Thread.sleep(3000);
		accpage.clickOnCreateNewAccount();
		log.info("Create new account page loading");
		Thread.sleep(3000);
		accpage.generateLegalName();
		accpage.setAccountName();
		Thread.sleep(2000);
		accpage.setLegalName();
		Thread.sleep(2000);
		accpage.setPhone();
		Thread.sleep(2000);
		accpage.clickOnSaveAccount();
		Thread.sleep(5000);
		Assert.assertTrue(false, "Intentional failure to verify screenshot capture");
		accpage.clickOnbackbutton();
		Thread.sleep(5000);
	}
	
	@AfterClass//(priority = 3)
		public void logout() throws Exception {
			logpage = new logout_page(driver);
			logpage.elmsLogoutApplication();
			
		}

}
