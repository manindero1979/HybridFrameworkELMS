package com.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.base.BaseClass;

import com.pages.login_page;
import com.utility.PropertyUtils;



public class LoginUser extends BaseClass{
	login_page lp=null;
	
	@BeforeClass//(alwaysRun=true)
	public void setup() throws Exception {
		intialization();
		System.out.println("webdriver intialized");
		lp = new login_page(driver);
	}
	
	
	@Test
	public void test_login() throws Exception {
		Thread.sleep(5000);
		log.info("we are inside login method");
		String magadminuser = PropertyUtils.readProperty("elmsadminuser");
		String magadminpassword = PropertyUtils.readProperty("elmsadminpassword");
		lp.loginToelms(magadminuser,magadminpassword);
		Assert.assertEquals(driver.getTitle(), "Dashboard / Magento Admin");
		log.info("after successful login page title is: "+driver.getTitle());
		
	}
		
	@AfterClass
	public void logout() throws Exception {
		Thread.sleep(5000);
		lp.logout();
	}
	
	
	}
	

