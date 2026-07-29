package com.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pages.contacts_page;
import com.pages.login_page;
import com.pages.logout_page;
import com.utility.PropertyUtils;

public class elmsCreateContacts extends BaseClass {

	contacts_page contactspage = null;
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
	public void Create_Contacts() throws Exception {
		contactspage = new contacts_page(driver);
		Thread.sleep(3000);
		contactspage.clickOnContactsMenu();
		Thread.sleep(3000);
		contactspage.clickOnCreateNewContact();

		log.info("Create new contact page loading");
		Thread.sleep(3000);

		contactspage.generateFirstName();
		contactspage.setFirstName();
		Thread.sleep(1000);
		contactspage.setLastName();
		Thread.sleep(1000);
		contactspage.setWorkEmail();
		Thread.sleep(1000);

		contactspage.setTitle();
		Thread.sleep(2000);

		contactspage.setCompany();
		Thread.sleep(1000);
		contactspage.setMainPhone();
		Thread.sleep(1000);
		contactspage.setAddress1();
		Thread.sleep(1000);
		contactspage.setAddress2();
		Thread.sleep(1000);

		contactspage.setCity();
		Thread.sleep(1000);
		contactspage.setZipCode();
		Thread.sleep(1000);
		
		//String contact_country = PropertyUtils.readProperty("contactcountry");
		contactspage.setCountry();
		Thread.sleep(2000);
		contactspage.setState();
		Thread.sleep(4000);

		contactspage.setAddressType();
		Thread.sleep(2000);

		contactspage.clickOnSaveContact();
		
		Thread.sleep(5000);

		
		
		contactspage.clickOnbackbutton();
		Thread.sleep(2000);

	}
	
	@AfterClass//(priority = 3)
		public void logout() throws Exception {
			logpage = new logout_page(driver);
			logpage.elmsLogoutApplication();
		}

}
