package com.pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseClass;
import com.utility.PropertyUtils;
import org.testng.Assert;
public class account_page extends BaseClass {// Call driver from base class so extend it here

	// constructor
	public account_page(WebDriver driver) {
		PageFactory.initElements(driver, this);// InitElement method will initialize all web element in one go
	}

	// javascript for scrolling
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	//Account name
	public String Account_Legal_Name;
	public String Account_phone_data;
	
	public String Account_phone_modified_data;
	
	
	
	// #main links
	// Account link
	@FindBy(xpath = "//li[@id='menu-pragmatic-customadminnewpage-accounts' and @role='menu-item']//a")
	private WebElement link_accounts_menu;

	// Admin Archive link
	@FindBy(id = "menu-pragmatic-customadminnewpage-archival")
	private WebElement link_admin_archive;

	// Account Archive link
	@FindBy(id = "menu-pragmatic-customadminnewpage-account-archival")
	private WebElement link_account_archive;

	// All Accounts link
	@FindBy(xpath = "//li[@class='item-accounts-all    level-1']")
	private WebElement link_all_accounts;

	//All accounts page header
	@FindBy(xpath = "//h1[text()='All Accounts']")
	private WebElement header_all_accounts;

	// create new account link
	@FindBy(xpath = "//li[@class='item-accounts-create    level-1']")
	private WebElement btn_create_account;

	// #creating accounts
	@FindBy(xpath = "//input[@name='account_name']")
	private WebElement acc_name;

	@FindBy(xpath = "//input[@id='legal_name']")
	private WebElement legal_name;

	@FindBy(xpath = "//input[@id='paraent_account']")
	private WebElement parent_account;

	@FindBy(id = "phone")
	private WebElement phone;

	@FindBy(id = "accountSave")
	private WebElement btn_save;

	@FindBy(xpath = "//span[text()='Contacts']")
	private WebElement link_contacts_in_account;
	
	@FindBy(xpath = "//button[text()='Back']")
	private WebElement btn_back;

	// audit log link
	@FindBy(xpath = "//li[@class='item-accounts-logging    level-1']")
	private WebElement link_audit_log;

	@FindBy(xpath = "//input[@name='name']")
	private WebElement search_account;

	@FindBy(xpath = "//input[@name='id']")
	private WebElement enter_Account_id;

	@FindBy(xpath = "//span[contains(text(),'Events & Products')]")
	private WebElement link_events_products_in_account;

	//clear all filter button
	@FindBy(xpath = "//button[contains(text(),'Clear all')]")
	private WebElement btn_clear_all_filter;

	//Filter button
	@FindBy(xpath = "//button[contains(text(),'Filters')]")
	private WebElement btn_filters;
	
	//Apply filters button
	@FindBy(xpath = "//span[contains(text(),'Apply Filters')]")
	private WebElement btn_apply_filters;

	// # deleting account
	@FindBy(xpath = "//button[text()='Select']")
	private WebElement dropdown_select_account;

	@FindBy(xpath = "//a[text()='Delete']")
	private WebElement dropdown_delete_account;

	@FindBy(xpath = "//a[text()='View']")
	private WebElement dropdown_view_account;

	// # restoring account
	@FindBy(xpath = "//a[text()='Restore']")
	private WebElement dropdown_restore_account;
	
	//#account deletion msges
	@FindBy(xpath = "//div[@id='modal-content-15'] //child :: div")
	private WebElement msg_account_delete_confirm;

	@FindBy(xpath = "//button[@class='action-primary action-accept']")
	private WebElement msg_account_delete_confirm_ok;

	@FindBy(xpath = "//div[@class='message message-success success']//div")
	private WebElement msg_account_delete_success;
	
	
	public void clickOnAccountsMenu() throws Exception {
		// js.executeScript("arguments[0].scrollIntoView(true);",link_Accounts_menu);
		js.executeScript("window.scrollBy(0,300)");
		Thread.sleep(2000);
		link_accounts_menu.click();
	}

	public void clickOnCreateNewAccount() {
		btn_create_account.click();
		log.info("CREATE NEW ACCOUNT link selected");
	}

	public void clickOnAdminArchiveMenu() {
		link_admin_archive.click();
	}

	public void clickOnAccountArchive() {
		link_account_archive.click();
	}
	
	public void selectAllAccounts() {
		link_all_accounts.click();
		log.info("ALL ACCOUNT link selected");
	}
	

	public void generateLegalName() throws Exception {
		System.out.println("----------Account_Legal_Name---------");
		String AccountLegalName_initial_data = PropertyUtils.readProperty("AccountLegalName_initial");
		
		Account_Legal_Name = AccountLegalName_initial_data + RandomStringUtils.randomNumeric(4);
		System.out.println("Random Account Name generated is: " + Account_Legal_Name);
		//return Account_Legal_Name;
	}

	public void setAccountName() {//String Account_Legal_Name) {
		log.info("The Account Name used for this Account is " + Account_Legal_Name);
		acc_name.sendKeys(Account_Legal_Name);
	}

	public void setLegalName() {//String Account_Legal_Name) {
		log.info("The Legal Name used for this Account is " + Account_Legal_Name);
		legal_name.sendKeys(Account_Legal_Name);
	}

	/*
	 * public void setContactsCompany() {
	 * print("The Company Name for Contacts used for this Account is " +
	 * Account_Legal_Name) self.driver.find_element(By.ID,
	 * self.txtBxCompany_id).send_keys(Account_Legal_Name)
	 * 
	 * }
	 * 
	 * 
	 * public void setEventAccount() {
	 * print("The Account Name for Event used for this Account is " +
	 * Account_Legal_Name) self.driver.find_element(By.ID,
	 * self.enterAccount_id).send_keys(Account_Legal_Name) }
	 */

	public void setPhone() throws Exception {
		Account_phone_data = PropertyUtils.readProperty("Account_phone");
		phone.sendKeys(Account_phone_data);
		log.info("Phone data added");
	}

	/*
	 * public void setEditedPhone() throws Exception { Account_phone_modified_data =
	 * PropertyUtils.readProperty("Account_phone_modified"); phone.clear();
	 * phone.sendKeys(Account_phone_modified_data); log.info("Phone data modified");
	 * }
	 */
	public void clickContactsInAct() {
		link_contacts_in_account.click();
	}

	public void clickEventsProductsInAct() {
		link_events_products_in_account.click();
	}

	public void clickOnSaveAccount() throws Exception {
		btn_save.click();
		log.info("Account info saved");
		log.info("New account gets created");
		Thread.sleep(5000);
		/*File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/accountcreated.jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}*/

	}

	
	public void clickOnbackbutton() {
		btn_back.click();
		log.info("BACK button clicked");
	}

	public void checkAllAccountHeader() {
		Assert.assertEquals(header_all_accounts.getText(), "ALL ACCOUNTS");
		log.info("we are in All Accounts listing page");
	}

	public void clickOnClearAllFilters() {
		if (btn_clear_all_filter.isDisplayed()) {
			System.out.println("The Clear all Filter Link is displayed");
			btn_clear_all_filter.click();
		} else
			System.out.println("The Clear all Filter Link is not displayed");
	}

	public void clickOnFiltersBtn() {
		// js.executeScript("arguments[0].scrollIntoView(true);", txtBxAccountNameFltr);
		btn_filters.click();
	}

	public void setAccountNameFilter() {
		js.executeScript("arguments[0].scrollIntoView(true);", search_account);
		search_account.sendKeys(Account_Legal_Name);
		log.info("account name field visible");
	}

	public void clickOnApplyFiltersBtn() {
		// js.executeScript("arguments[0].scrollIntoView(true);", btnApplyFilters);
		if (btn_apply_filters.isDisplayed())
			{btn_apply_filters.click();
		log.info("APPLY FILTER button cliked");}
		else
			log.warn("apply filter button not visible");
	}

	/*
	 * public void clickAccountNameResultlnk(By click_accountName_Result) { //
	 * js.executeScript("arguments[0].scrollIntoView();", txtBxAccountNameFltr);
	 * driver.findElement(click_accountName_Result).click(); }
	 */

	public void clickOnSelectAccount() {
		dropdown_select_account.click();
		log.info("Account selected");
	}

	public void clickViewAccount() {
		dropdown_view_account.click();
		log.info("Account VIEW link selected");
	}

	public void editAccountPhone() throws Exception {
		log.info("I am inside editAccountPhone method");
		Thread.sleep(2000);
		phone.clear();
		log.info("Phone data cleared");
		Account_phone_modified_data = PropertyUtils.readProperty("Account_phone_modified");
		phone.sendKeys(Account_phone_modified_data);
		Thread.sleep(1000);
		btn_save.click();
		log.info("account phone modified and saved");
		Thread.sleep(2000);
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/accountedited.jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void clickOnDeleteAccount() throws InterruptedException {
		js.executeScript("arguments[0].scrollIntoView();", search_account);
		dropdown_delete_account.click();
		log.info("Delete account option selected");
		Thread.sleep(3000);
		if (msg_account_delete_confirm.isDisplayed()) {
			msg_account_delete_confirm_ok.click();
			log.info("Account deletion confirmed");
			Thread.sleep(2000);
			String successmsg = msg_account_delete_success.getText();
			log.info(successmsg);
			
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshot, new File("target/screenshots/accountdeleted.jpg"));// "C:\\projectScreenshots\\homePageScreenshot.png"));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		} else
			log.info("account deletion confirmation msg not visible");
	}

	public void clickOnRestoreAccount() {
		js.executeScript("arguments[0].scrollIntoView();", search_account);

		dropdown_restore_account.click();
	}

}
