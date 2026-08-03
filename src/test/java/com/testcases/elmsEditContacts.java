package com.testcases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.models.Credentials;
import com.pages.contacts_page;
import com.pages.login_page;
import com.pages.logout_page;
import com.utility.ExcelUtils;
import com.utility.PropertyUtils;

public class elmsEditContacts extends BaseClass{
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

	    Credentials admin = ExcelUtils.getCredentials("elms", "admin");

	    lp.loginToelms(admin.getUsername(), admin.getPassword());

	    Assert.assertEquals(driver.getTitle(), "Dashboard / Magento Admin");

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
		Thread.sleep(2000);

		contactspage.setTitle();
		Thread.sleep(1000);

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
		contactspage.setCountry();
		Thread.sleep(2000);
		contactspage.setState();
		Thread.sleep(2000);

		contactspage.setAddressType();
		Thread.sleep(2000);

		contactspage.clickOnSaveContact();
		Thread.sleep(5000);

		
		log.info("New contact gets created");
		//contactspage.clickOnbackbutton();

	}
	
	@Test(priority = 3)
	public void Edit_Contacts() throws Exception {		
		
		
		contactspage.clickOnContactsMenu();
		Thread.sleep(3000);
		contactspage.selectAllContacts();
		Thread.sleep(3000);
		contactspage.clickOnClearAllFilters();
		Thread.sleep(3000);
		contactspage.clickOnContactFiltersBtn();
		Thread.sleep(3000);
		contactspage.setEmailFilter();
		Thread.sleep(5000);
		contactspage.clickOnContactApplyFiltersBtn();
		Thread.sleep(5000);
		contactspage.clickOnSelectContact();
		Thread.sleep(2000);
		
		contactspage.clickViewContact();
		Thread.sleep(2000);
		contactspage.editContactMainPhone();
		Thread.sleep(2000);
		
		//accpage.clickOnDeleteContacts();
		
			
	}
	
	@AfterClass//(priority = 3)
		public void logout() throws Exception {
			logpage = new logout_page(driver);
			logpage.elmsLogoutApplication();
		}
	
	

}
