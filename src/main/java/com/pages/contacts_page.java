package com.pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.BaseClass;
import com.utility.PropertyUtils;

public class contacts_page extends BaseClass {

	// constructor
	public contacts_page(WebDriver driver) {
		PageFactory.initElements(driver, this);// InitElement method will initialize all web element in one go
	}

	// javascript for scrolling
	JavascriptExecutor js = (JavascriptExecutor) driver;

	public String firstnamevalue;

	public String wemail;
	
	String contact_firstname_data;
	String contact_lastname_data;

	// #main links
	// Contacts menu link
	@FindBy(xpath = "//li[@id='menu-pragmatic-customadminnewpage-contacts' and @role='menu-item']//a")
	private WebElement link_contacts_menu;

	// Admin Archive link
	@FindBy(id = "menu-pragmatic-customadminnewpage-archival")
	private WebElement link_admin_archive;

	// Contact Archive link
	@FindBy(id = "menu-pragmatic-customadminnewpage-contact-archival")
	private WebElement link_contact_archive;

	// All Accounts link
	@FindBy(xpath = "//li[@class='item-contacts-all    level-1']")
	private WebElement link_all_contacts;

	// All Contacts page header
	@FindBy(xpath = "//h1[text()='All Contacts']")
	private WebElement header_all_contacts;

	// contact merge
	@FindBy(xpath = "//li[@class='item-contacts-merge    level-1']")
	private WebElement link_contact_merge;

	// Contact Merge page header
	@FindBy(xpath = "//h1[text()='Contacts Merge']")
	private WebElement header_contacts_merge;

	// create new contact link
	@FindBy(xpath = "//li[@class='item-contacts-create    level-1']")
	private WebElement btn_create_contact;

	// CReate new contact page header
	@FindBy(xpath = "//h1[text()='Create New Contact']")
	private WebElement header_create_new_contact;

	// #creating contacts
	@FindBy(xpath = "//input[@id='firstname']")
	private WebElement first_name;

	@FindBy(xpath = "//input[@id='lastname']")
	private WebElement last_name;

	@FindBy(xpath = "//input[@id='workemail']")
	private WebElement work_email;

	@FindBy(xpath = "//input[@id='job_title']")
	private WebElement title;

	@FindBy(xpath = "//input[@id='company']")
	private WebElement company;

	@FindBy(xpath = "//input[@id='telephone']")
	private WebElement main_phone;

	@FindBy(xpath = "//input[@id='address_1']")
	private WebElement address_1;

	@FindBy(xpath = "//input[@id='address2_1']")
	private WebElement address_2;

	@FindBy(xpath = "//input[@id='city_1']")
	private WebElement city;

	@FindBy(xpath = "//input[@id='zip_1']")
	private WebElement zip_code;

	//@FindBy(xpath = "//select[@name='country_1']//following::option[@value='IN']")
	@FindBy(xpath = "//select[@name='country_1']")
	private WebElement country;

	@FindBy(xpath = "//select[@name='state_1']")
	private WebElement state;

	// address type=Home
	@FindBy(xpath = "//input[@name='AaddressType_1[]' and @value='2']")
	private WebElement address_type;

	@FindBy(xpath = "//button[@id='btnsave']")
	private WebElement btn_save;

	@FindBy(xpath = "//div[@id='lblcertification']")
	private WebElement link_certifications_in_contacts;

	@FindBy(xpath = "//div[@id='lblorders']")
	private WebElement link_orders_in_contacts;

	@FindBy(xpath = "//button[text()='Back']")
	private WebElement btn_back;

	// #Edit contact page header
	@FindBy(xpath = "//h1[text()='Edit Contact']")
	private WebElement header_edit_contact;

	// #searching contacts
	// search contact by email
	@FindBy(xpath = "//input[@name='email']")
	private WebElement search_email;

	@FindBy(xpath = "///input[@name='entity_id']")
	private WebElement search_id;

	// clear all filter button
	@FindBy(xpath = "//button[contains(text(),'Clear all')]")
	private WebElement btn_clear_all_filter;

	// Filter button
	@FindBy(xpath = "//button[contains(text(),'Filters')]")
	private WebElement btn_filters;

	// Apply filters button
	@FindBy(xpath = "//span[contains(text(),'Apply Filters')]")
	private WebElement btn_apply_filters;

	// # deleting contact
	@FindBy(xpath = "//button[text()='Select']")
	private WebElement dropdown_select_contact;

	@FindBy(xpath = "//a[text()='Delete']")
	private WebElement dropdown_delete_contact;

	@FindBy(xpath = "//a[text()='View']")
	private WebElement dropdown_view_contact;

	// #account deletion msges
	@FindBy(xpath = "//div[@id='modal-content-15'] //child :: div")
	private WebElement msg_contact_delete_confirm;

	@FindBy(xpath = "//button[@class='action-primary action-accept']")
	private WebElement msg_contact_delete_confirm_ok;

	@FindBy(xpath = "//div[@class='message message-success success']//div")
	private WebElement msg_contact_delete_success;

	// # restoring contact
	@FindBy(xpath = "//a[text()='Restore']")
	private WebElement dropdown_restore_contact;

	// #Methods
	public void clickOnContactsMenu() throws Exception {

		js.executeScript("window.scrollBy(0,300)");
		Thread.sleep(2000);
		link_contacts_menu.click();
	}

	public void clickOnCreateNewContact() {
		btn_create_contact.click();
		log.info("create new contact button clicked");
	}

	public void clickOnAdminArchiveMenu() {
		link_admin_archive.click();
		log.info("clicked Admin archive menu");
	}

	public void clickOnContactArchive() {
		link_contact_archive.click();
		log.info("clicked contact archive menu");
	}

	public void clickOnContactMerge() {
		link_contact_merge.click();
		log.info("clicked contact merge menu");
	}

	public void selectAllContacts() {
		link_all_contacts.click();
		log.info("All contacts link is selected");
	}
	
	
	@FindBy(xpath = "//button[text()='Clear all']")
	private WebElement link_clearallcontacts;

	public void clearAllContactFilterLink() {
		log.info("We are inside contact-CLEAR ALL FILTER LINK method");
		
		/*
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
		 * btn_filters);
		 */
		 
		//wait.until(ExpectedConditions.visibilityOf(link_clearall));
		  try   //add this try-catch so tht if elemtn not displayed then ELSE condition will work
		  {

		if (link_clearallcontacts.isDisplayed()){
			link_clearallcontacts.click();
			log.info("CLEARALL link visible and clikced");
		}
		  }      
		  	catch(Exception e)     
		  	{

			log.info("contact-clearall link not visible");}

	}

	
	

	public String generateFirstName() throws Exception {
		System.out.println("----------contact email---------");
		String contact_firstname_data = PropertyUtils.readProperty("contact_firstname");
		firstnamevalue = contact_firstname_data +"."+ RandomStringUtils.randomNumeric(4);
		System.out.println("Random firstname generated is: " + firstnamevalue);
		return firstnamevalue;
	}

	public void setFirstName() throws Exception {
		contact_firstname_data = PropertyUtils.readProperty("contact_firstname");
		first_name.sendKeys(contact_firstname_data);
		log.info("first name is set");
	}

	public void setLastName() throws Exception {
		contact_lastname_data = PropertyUtils.readProperty("contact_lastname");
		last_name.sendKeys(contact_lastname_data);
		log.info("last name is set");
	}

	public String setWorkEmail() throws Exception {
		String contact_domain_data = PropertyUtils.readProperty("contact_domain");
		
		wemail = firstnamevalue+ contact_domain_data;
		work_email.sendKeys(wemail);
		log.info("contact email set is: "+wemail);
		log.info("work email is set");
		return wemail;
	}

	public void setTitle() throws Exception {
		String contact_title_data = PropertyUtils.readProperty("contact_title");
		title.sendKeys(contact_title_data);
		log.info("title is set");
	}

	public void setCompany() throws Exception {
		String contact_company_data = PropertyUtils.readProperty("contact_company");
		company.sendKeys(contact_company_data);
		log.info("company is set");
	}

	public void setMainPhone() throws Exception {
		String contact_phone_data = PropertyUtils.readProperty("contact_phone");
		main_phone.sendKeys(contact_phone_data);
		log.info("phone is set");
	}

	public void setAddress1() throws Exception {
		String contact_address1_data = PropertyUtils.readProperty("contact_address1");
		address_1.sendKeys(contact_address1_data);
		log.info("address1 is set");
	}

	public void setAddress2() throws Exception {
		String contact_address2_data = PropertyUtils.readProperty("contact_address2");
		address_2.sendKeys(contact_address2_data);
		log.info("address2 is set");
	}

	public void setCity() throws Exception {
		String contact_city_data = PropertyUtils.readProperty("contact_city");
		city.sendKeys(contact_city_data);
		log.info("city is set");
	}

	public void setZipCode() throws Exception {
		String contact_zipcode_data = PropertyUtils.readProperty("contact_zipcode");
		zip_code.sendKeys(contact_zipcode_data);
		log.info("zipcode is set");
	}

	public void setCountry() throws Exception {
		String contactcountry_data = PropertyUtils.readProperty("contactcountry");
		Select countrydropdown = new Select(country); 
		countrydropdown.selectByVisibleText(contactcountry_data);//country india is set
		//country.click();
		log.info("country is set");
	}

	public void setState() throws Exception {
		String contact_state_data = PropertyUtils.readProperty("contact_state");
		Select statedropdown = new Select(state); 
		statedropdown.selectByVisibleText(contact_state_data);
		log.info("state is set");
	}

	public void setAddressType() {
		address_type.click();
		log.info("Address type is set");
	}

	public void clickOnSaveContact() throws Exception {
		btn_save.click();
		Thread.sleep(2000);
		log.info("save button is clicked");
		log.info("New contact gets created");
		
		/*File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/contactcreated.jpg"));// "C:\\projectScreenshots\\homePageScreenshot.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}*/
		log.info("New contact gets created");
		
	}

	public void clickOnbackbutton() {
		btn_back.click();
		log.info("back button is clicked");
	}

	public void clickOnClearAllFilters() {
		try
		{
			if (btn_clear_all_filter.isDisplayed())
		
		{
			btn_clear_all_filter.click();
			log.info("The Clear all Filter Link is displayed and clicked");
		} 
		}
		catch (Exception e)

		{
			log.info("clearall link not visible");
		}
		
		
		
	}

	public void clickOnContactFiltersBtn() {
		// js.executeScript("arguments[0].scrollIntoView(true);", txtBxAccountNameFltr);
		btn_filters.click();
		log.info("Filter button is clicked");
	}

	public void setEmailFilter() {
		js.executeScript("arguments[0].scrollIntoView(true);", search_email);
		search_email.clear();
		search_email.sendKeys(wemail);
		log.info("Email is added in filter form");
	}
	
	public void setBillerEmailFilter() {
		js.executeScript("arguments[0].scrollIntoView(true);", search_email);
		search_email.sendKeys(newBiller_email);
		log.info("Email is added in filter form");
	}
	
	public void setRegEmailFilter() {
		js.executeScript("arguments[0].scrollIntoView(true);", search_email);
		search_email.sendKeys(registrant);
		log.info("Email is added in filter form");
	}

	public void clickOnContactApplyFiltersBtn() {
		// js.executeScript("arguments[0].scrollIntoView(true);", btnApplyFilters);
		if (btn_apply_filters.isDisplayed())
			btn_apply_filters.click();
		else
			log.warn("apply filter button not visible");;
	}

	public void clickOnSelectContact() {
		js.executeScript("scroll(0, 150);");
		dropdown_select_contact.click();
		log.info("SELECT link clicked");
	}

	public void clickViewContact() {
		//js.executeScript("arguments[0].scrollIntoView();", dropdown_view_contact);
		dropdown_view_contact.click();
		log.info("VIEW contact link clicked");
	}

	@FindBy(xpath = "//input[@name='company']")
	private WebElement addcontactcompany;
	
	@FindBy(xpath = "//span[text()='Company']")
	private WebElement contactcompanylabel;
	
	

	
	public void checkContactCompany() {
		
		js.executeScript("scroll(0, 200);");
		
		File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot1, new File("target/screenshots/registrantcontactdetails.jpg"));// "C:\\projectScreenshots\\homePageScreenshot.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		log.info("company name reflecting for this contact is: "+addcontactcompany.getAttribute("value"));
		
		if (addcontactcompany.getAttribute("value").equals(updatedorderaccount))
			log.info("updated order account is reflecting as contact company");
		else
			log.info("updated order account is NOT reflecting as contact company");	
		
	}
	
	
	
	
	
	
	
	
	
	public void editContactMainPhone() throws Exception {
		String contact_phone_modified_data = PropertyUtils.readProperty("contact_phone_modified");
		main_phone.clear();
		main_phone.sendKeys(contact_phone_modified_data);
		log.info("Modified phone data added");
		Thread.sleep(2000);
		btn_save.click();
		log.info("contact modified");
		Thread.sleep(2000);
		File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot1, new File("target/screenshots/contactedited.jpg"));// "C:\\projectScreenshots\\homePageScreenshot.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@FindBy(xpath = "//button[text()='Back']")
	private WebElement btn_back_contactdetailspage;
	
	public void clickBackToContactListPage() {
		btn_back_contactdetailspage.click();
	}
	
	public void clickOnDeleteContact() throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", dropdown_delete_contact);
		
		log.info("Delete option clicked");
		Thread.sleep(5000);
		if (msg_contact_delete_confirm.isDisplayed()) {
			msg_contact_delete_confirm_ok.click();
			log.info("contact deletion confirmation OK button clicked");
			Thread.sleep(2000);
			String successmsg = msg_contact_delete_success.getText();
			log.info("contact deletion success msg is: "+successmsg);
			
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshot, new File("target/screenshots/contactdeleted.jpg"));// "C:\\projectScreenshots\\homePageScreenshot.png"));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		} else
			log.info("contact deletion confirmation msg not visible");

	}

	public void clickOnRestoreAccount() {
		js.executeScript("arguments[0].scrollIntoView();", dropdown_restore_contact);

		dropdown_restore_contact.click();
	}

	
}
