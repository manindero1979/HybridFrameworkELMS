package com.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.models.Credentials;
import com.pages.contacts_page;
import com.pages.login_page;
import com.pages.logout_page;
import com.utility.ExcelUtils;
import com.utility.PropertyUtils;

public class elmsDeleteContacts extends BaseClass{
	contacts_page contactspage = null;
	login_page lp = null;
	logout_page logpage = null;
	

	@BeforeClass(alwaysRun = true)
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
		Thread.sleep(2000);
		contactspage.setLastName();
		Thread.sleep(2000);
		contactspage.setWorkEmail();
		Thread.sleep(2000);
		contactspage.setTitle();
		Thread.sleep(2000);
		contactspage.setCompany();
		Thread.sleep(2000);
		contactspage.setMainPhone();
		Thread.sleep(2000);
		contactspage.setAddress1();
		Thread.sleep(2000);
		contactspage.setAddress2();
		Thread.sleep(2000);
		contactspage.setCity();
		Thread.sleep(2000);
		contactspage.setZipCode();
		Thread.sleep(2000);
		contactspage.setCountry();
		Thread.sleep(2000);
		contactspage.setState();
		Thread.sleep(4000);
		contactspage.setAddressType();
		Thread.sleep(2000);
		contactspage.clickOnSaveContact();
		Thread.sleep(5000);
}
	
	@Test(priority = 3)
	public void Delete_Contacts() throws Exception {		
		contactspage.clickOnContactsMenu();
		Thread.sleep(3000);
		contactspage.selectAllContacts();
		Thread.sleep(5000);
		contactspage.clickOnClearAllFilters();
		Thread.sleep(7000);
		contactspage.clickOnContactFiltersBtn();
		Thread.sleep(3000);
		contactspage.setEmailFilter();
		Thread.sleep(3000);
		contactspage.clickOnContactApplyFiltersBtn();
		Thread.sleep(7000);
		contactspage.clickOnSelectContact();
		Thread.sleep(5000);
		contactspage.clickOnDeleteContact();
		Thread.sleep(2000);
		
}
	@Test(priority = 4)
		public void logout() throws Exception {
			logpage = new logout_page(driver);
			logpage.elmsLogoutApplication();
		}

}
