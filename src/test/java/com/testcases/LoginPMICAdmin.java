package com.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.login_page;
import com.utility.PropertyUtils;

public class LoginPMICAdmin extends BaseClass{
	
	login_page lp=null;
	
	@BeforeClass//(alwaysRun=true)
	public void setup() throws Exception {
		intializationpmic();
		System.out.println("webdriver intialized");
		lp = new login_page(driver);
	}

	@Test
	public void testPmicAdminLogin() throws Exception {
		Thread.sleep(5000);
		log.info("we are inside login method");
		String pmicadminuser_data = PropertyUtils.readProperty("pmicadminuser");
		String pmicadminpassword_data = PropertyUtils.readProperty("pmicadminpassword");
		lp.loginToPmicAdmin(pmicadminuser_data,pmicadminpassword_data);
		
	}
		
	@AfterClass
	public void testpmiclogout() throws Exception {
		Thread.sleep(5000);
		lp.pmiclogout();
		driver.quit();
	}
	
	
	}
