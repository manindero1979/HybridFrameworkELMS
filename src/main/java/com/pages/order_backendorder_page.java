package com.pages;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.base.BaseClass;
import com.utility.PropertyUtils;

public class order_backendorder_page extends BaseClass {

	public String new_registrant_email;
	public String search_existing_customer;
	public String existingorderid_data;

	public String customer_work_email;
	public String customerupdatedfname;
	public String customerupdatedlname;
	public String customerupdatedemail;
	public String customerupdatedmasteraccount;
	public String customerupdatedorderaccount;

	public String oldtab;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
	// public String orderid;

	SoftAssert softAssert = new SoftAssert();
	//private WebDriver driver;
	private WebDriverWait wait;

	public order_backendorder_page(WebDriver driver) {
	    this.driver = driver;

	    this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	    PageFactory.initElements(driver, this);
	}

	// #main links
	// Events menu link
	@FindBy(xpath = "//li[@id='menu-pragmatic-customadminnewpage-order' and @role='menu-item']//span[text()='Orders']//parent::a")
	private WebElement link_orders_menu;

	// All Orders link
	@FindBy(xpath = "//li[@class='item-order-all    level-1']")
	private WebElement link_all_orders;

	// create Order link
	@FindBy(xpath = "//li[@class='item-order-create    level-1']")
	private WebElement link_create_order;

	// Audit log link
	@FindBy(xpath = "//li[@class='item-order-logging    level-1']")
	private WebElement link_audit_log;

	// update order addresses link
	@FindBy(xpath = "//li[@class='item-order-sales    level-1']")
	private WebElement link_update_order_addresses;

	public void clickOnOrdersMenu() {
		link_orders_menu.click();
		log.info("ORDER menu clicked");
	}

	public void clickAllOrdersLink() {
		link_all_orders.click();
		log.info("ALL ORDERS link clicked");
	}

	public void clickCreateOrderLink() {
		link_create_order.click();
		log.info("CREATE NEW ORDER link clicked");

	}

	public void clickAuditLogLink() {
		link_audit_log.click();
	}

	public void clickUpdateOrderAddressesLink() {
		link_update_order_addresses.click();
	}

	// create new customer
	@FindBy(xpath = "//button[@id='btnCreateCustomer']")
	private WebElement btn_create_new_customer;

	@FindBy(id = "firstname")
	private WebElement new_customer_firstname;

	@FindBy(id = "lastname")
	private WebElement new_customer_lastname;

	@FindBy(id = "workemail")
	private WebElement new_customer_workemail;
	///

	@FindBy(xpath = "//input[@id='job_title']")
	private WebElement new_customer_title;

	@FindBy(xpath = "//input[@id='company']")
	private WebElement new_customer_company;

	@FindBy(xpath = "//input[@id='telephone']")
	private WebElement new_customer_mainphone;

	@FindBy(xpath = "//input[@id='address_1']")
	private WebElement new_customer_address1;

	@FindBy(xpath = "//input[@id='address2_1']")
	private WebElement new_customer_address2;

	@FindBy(xpath = "//input[@id='city_1']")
	private WebElement new_customer_city;

	@FindBy(xpath = "//input[@id='zip_1']")
	private WebElement new_customer_zipcode;

	@FindBy(xpath = "//select[@id='country_1']")
	private WebElement new_customer_country;

	@FindBy(xpath = "//select[@id='state_1']")
	private WebElement new_customer_state;

	@FindBy(xpath = "//button[@id='btnCheckout_newCustomer_bottom']")
	private WebElement btn_save_new_customer;

	@FindBy(xpath = "//*[@aria-describedby='modal-content-1']//descendant::div[@class='page-actions']")
	private WebElement btn_close_new_customer;

	public void generateCustomerWorkEmail() {
		System.out.println("----------work email---------");
		customer_work_email = "testcustomer." + RandomStringUtils.randomNumeric(4) + "@flipick.com";
		System.out.println("Random work email generated is: " + customer_work_email);
		// return customer_work_email;
	}

	public void createNewCustomer() throws Exception {
		Thread.sleep(2000);
		System.out.println(wait);
		wait.until(ExpectedConditions.elementToBeClickable(btn_create_new_customer)).click();
		// btn_create_new_customer.click();
		log.info("CREATE NEW CUSTOMER button clicked");
		Thread.sleep(2000);

		String new_customer_firstname_data = PropertyUtils.readProperty("newcustomer_firstname");
		String new_customer_lastname_data = PropertyUtils.readProperty("newcustomer_lastname");
		String new_customer_title_data = PropertyUtils.readProperty("newcustomer_title");
		String new_customer_company_data = PropertyUtils.readProperty("newcustomer_company");
		String new_customer_mainphone_data = PropertyUtils.readProperty("newcustomer_mainphone");
		String new_customer_address1_data = PropertyUtils.readProperty("newcustomer_address1");
		String new_customer_address2_data = PropertyUtils.readProperty("newcustomer_address2");
		String new_customer_city_data = PropertyUtils.readProperty("newcustomer_city");
		String new_customer_zipcode_data = PropertyUtils.readProperty("newcustomer_zipcode");
		String new_customer_country_data = PropertyUtils.readProperty("newcustomer_country");
		String new_customer_state_data = PropertyUtils.readProperty("newcustomer_state");

		new_customer_firstname.sendKeys(new_customer_firstname_data);
		Thread.sleep(1000);
		new_customer_lastname.sendKeys(new_customer_lastname_data);
		Thread.sleep(1000);
		new_customer_workemail.sendKeys(customer_work_email);
		Thread.sleep(1000);
		new_customer_title.sendKeys(new_customer_title_data);
		Thread.sleep(1000);
		new_customer_company.sendKeys(new_customer_company_data);
		Thread.sleep(1000);
		new_customer_mainphone.sendKeys(new_customer_mainphone_data);
		Thread.sleep(1000);
		new_customer_address1.sendKeys(new_customer_address1_data);
		Thread.sleep(1000);
		new_customer_address2.sendKeys(new_customer_address2_data);
		Thread.sleep(1000);
		new_customer_city.sendKeys(new_customer_city_data);
		Thread.sleep(1000);
		new_customer_zipcode.sendKeys(new_customer_zipcode_data);

		Select newcustomercountry = new Select(new_customer_country);
		newcustomercountry.selectByVisibleText(new_customer_country_data);
		Thread.sleep(2000);
		Select newcustomerstate = new Select(new_customer_state);
		newcustomerstate.selectByVisibleText(new_customer_state_data);
		Thread.sleep(5000);

		btn_save_new_customer.click();
		log.info("new customer: " + customer_work_email + ", details saved");
		log.info("closed");

	}

	@FindBy(xpath = "//input[@id='sales_order_create_customer_grid_filter_email']")
	private WebElement search_customer_email;

	@FindBy(xpath = "//div[@id='order-customer-selector']//button[@title='Search' and @type='button']")
	private WebElement btn_search;

	public void searchCustomerEmail() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(btn_create_new_customer)).sendKeys(customer_work_email);

		search_customer_email.sendKeys(customer_work_email);

		Thread.sleep(2000);
		btn_search.click();
		log.info("newly customer email is being searched");
	}

	public void searchExistingCustomerEmail() throws Exception {

		String searchdefaultcustomer_data = PropertyUtils.readProperty("searchdefaultcustomer");
		search_customer_email.sendKeys(searchdefaultcustomer_data);
		Thread.sleep(2000);
		btn_search.click();
		log.info("existing customer email is being searched");
	}

	@FindBy(xpath = "//div[@id='order-customer-selector']//child::td[@class=' col-email  ' and @data-column='email']")
	private WebElement select_searched_customer;

	public void selectSearchedCustomer() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(select_searched_customer)).click();
		// new WebDriverWait(driver,
		// 10).until(ExpectedConditions.elementToBeClickable(select_searched_customer)).click();
		// Thread.sleep(2000);
		log.info("customer selected");
	}

	@FindBy(xpath = "//select[@id='prodtab']")
	private WebElement dropdown_product_type;
	
	@FindBy(xpath = "//button[@id='add_products']")
	private WebElement btn_add_products;

	public void selectProductFromDrpdown() throws Exception {
		log.info("I am now under selectProductFromDrpdown method");
		Select dropdown_product_type_element = new Select(dropdown_product_type);
		dropdown_product_type_element.selectByVisibleText("Product");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(btn_add_products)).click();
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 30); WebElement element =
		 * wait.until(ExpectedConditions.elementToBeClickable(btn_add_products));
		 */
		// ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		// element);
		// Thread.sleep(2000);
		log.info("ADD PRODUCT button clicked");
	}

	public void selectFeesFromDrpdown() throws Exception {
		log.info("I am inside selectFeesFromDrpdown method");
		Select dropdown_product_type_element = new Select(dropdown_product_type);
		dropdown_product_type_element.selectByVisibleText("Fees");
		log.info("FEES value selected from Product dropdown");
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(btn_add_products)).click();
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 30); WebElement element =
		 * wait.until(ExpectedConditions.elementToBeClickable(btn_add_products));
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		 * element);
		 */
		// Thread.sleep(2000);

		log.info("ADD PRODUCT button clicked");
	}

	

	@FindBy(id = "sales_order_create_search_grid_filter_name")
	private WebElement search_event;

	@FindBy(xpath = "//div[@class='page-create-order']//button[@title='Search' and @type='button']")
	private WebElement btn_search_event;

	@FindBy(xpath = "//a[@list_type='product_to_add' and text()='Configure']")
	private WebElement btn_configure;

	@FindBy(xpath = "//input[@title='Qty']")
	private WebElement add_product_qty;

	@FindBy(xpath = "//button[contains(@class,'action-primary')]") // updated on 30th jan 23
	private WebElement btn_ok;

	@FindBy(xpath = "//div[@id='addProduct']//button[@title='Add Selected Product(s) to Order' ]")
	private WebElement btn_add_selected_product_to_order;

	@FindBy(xpath = "//strong[@class='admin__collapsible-title']//span[text()='Products']")
	private WebElement header_products_section;

	public void searchEventProduct() throws Exception {
		String test_event_from_property_file = PropertyUtils.readProperty("testeventselectedforordercases");
		log.info("eventname passed is: " + test_event_from_property_file);
		// wait.until(ExpectedConditions.elementToBeClickable(search_event)).sendKeys(test_event_from_property_file);
		search_event.sendKeys(test_event_from_property_file);
		Thread.sleep(4000);// updated on 8 aug 23
		log.info("added public online event in search field");
		wait.until(ExpectedConditions.elementToBeClickable(btn_search_event)).click();
		// btn_search_event.click();
		log.info("SEARCH EVENT button clicked");
		Thread.sleep(2000);// updated on 8 aug 23
	}

	public void searchLMSProduct() throws Exception {
		String testlmscourse_data = PropertyUtils.readProperty("testlmscourse");
		//log.info("eventname passed is: " + test_event_from_property_file);
		// wait.until(ExpectedConditions.elementToBeClickable(search_event)).sendKeys(test_event_from_property_file);
		search_event.sendKeys(testlmscourse_data);
		Thread.sleep(4000);// updated on 8 aug 23
		log.info("added FOD in search field");
		wait.until(ExpectedConditions.elementToBeClickable(btn_search_event)).click();
		// btn_search_event.click();
		log.info("SEARCH product button clicked");
		Thread.sleep(5000);// updated on 8 aug 23
	}
	
	public void clickOnProductResult() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(btn_configure)).click();
		// btn_configure.click();
		log.info("CONFIGURE button clicked");
	}
	
	@FindBy(xpath = "//td[@class=' col-name                                        ']")
	private WebElement lmscourse_select; 
	
	public void clickOnLMSResult() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(lmscourse_select)).click();
		// btn_configure.click();
		log.info("LMS course selected");
	}

	public void addProductQty(String qty) throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(add_product_qty));
		add_product_qty.clear();
		add_product_qty.sendKeys(qty);
		log.info("add product quantity as: " + qty);
	}

	public void clickOKButton() {
		wait.until(ExpectedConditions.elementToBeClickable(btn_ok)).click();
		// btn_ok.click();
		log.info("OK button clicked");
	}

	@FindBy(xpath = "//input[@id='id_1752']")
	private WebElement chkbox_testing_fee;

	public void selectTestingFees() {
		chkbox_testing_fee.click();
		log.info("selected Testing fee option");
	}

	public void clickAddSelectedProductToOrderBtn() throws Exception {

		//WebDriverWait wait1 = new WebDriverWait(driver, 30);
		WebDriverWait wait = new WebDriverWait(
		        driver,
		        Duration.ofSeconds(30)
		);
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30)); 
		WebElement element1 = wait1.until(ExpectedConditions.elementToBeClickable(btn_add_selected_product_to_order));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element1);
		Thread.sleep(5000);// added on 31st jan 23
		// wait.until(ExpectedConditions.elementToBeClickable(btn_add_selected_product_to_order)).click();//--this
		// didnt work here
		log.info("'ADD SELECTED PRODUCTs TO ORDER' button clicked");
	}

	@FindBy(xpath = "//td[@class='col-price']//input[@class='admin__control-checkbox']")
	private WebElement chkbox_adjust_price;

	@FindBy(xpath = "//td[@class='col-price']//input[@class='input-text item-price admin__control-text']")
	private WebElement add_adjusted_price;

	@FindBy(xpath = "//button[@title='Update Items and Quantities']")
	private WebElement btn_update_items_and_quantities;

	
	@FindBy(xpath = "//input[@name='coupon_code']")
	private WebElement discount_code;
	
	@FindBy(xpath = "//button[@title='Apply']")
	private WebElement apply_discount_code;	
	
	
	public void setAdjustedPrice() throws Exception {
		log.info("I am inside setAdjustedPrice method");

		js.executeScript("window.scrollBy(0,-100)", "");// added on 31st jan 23

		Thread.sleep(1000);

		// header_products_section.click();//

		Thread.sleep(1000);

		wait.until(ExpectedConditions.elementToBeClickable(chkbox_adjust_price)).click();
		/*
		 * WebDriverWait wait1 = new WebDriverWait(driver, 10); WebElement element1 =
		 * wait1.until(ExpectedConditions.elementToBeClickable(chkbox_adjust_price));
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		 * element1);
		 */
		log.info("ADJUST PRICE checkbox clciked");
		Thread.sleep(1000);

		add_adjusted_price.clear();
		add_adjusted_price.sendKeys("250");
	}
	
	public void applyDiscountCode() throws Exception {
		log.info("I am inside applyDiscountCode method");
		String discountcouponcode_data = PropertyUtils.readProperty("discountcouponcode");
		discount_code.sendKeys(discountcouponcode_data);
		Thread.sleep(2000);
		apply_discount_code.click();
		
	}

	public void clickUpdateItemsAndQtyBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(btn_update_items_and_quantities)).click();
		// btn_update_items_and_quantities.click();
		log.info("UPDATE ITEMS AND QUANTITY button clicked");
	}

	@FindBy(xpath = "//button[@id='add_registant']")
	private WebElement btn_add_registrants;

	public void clicOnAddRegistrantsBtn() {
		waitForLoaderToDisappear();
		wait.until(ExpectedConditions.elementToBeClickable(btn_add_registrants)).click();
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 10); WebElement element =
		 * wait.until(ExpectedConditions.elementToBeClickable(btn_add_registrants));
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		 * element);
		 */
		log.info("ADD REGISTRANTS button clicked");

	}

	// new registrant fields
	@FindBy(xpath = "//div[text()='Registrant - 1 of 3']//parent::div//child::input[@class='admin__control-text contactemail required-entry']")
	private WebElement new_registrant1_email_address;

	@FindBy(xpath = "//div[text()='Registrant - 1 of 3']//parent::div//child::input[@class='firstnamecl admin__control-text required-entry']")
	private WebElement new_registrant1_first_name;

	@FindBy(xpath = "//div[text()='Registrant - 1 of 3']//parent::div//child::input[@class='lastnamecl admin__control-text required-entry']")
	private WebElement new_registrant1_last_name;

	@FindBy(xpath = "//div[text()='Registrant - 1 of 3']//parent::div//child::input[@class='admin__control-text required-entry phonemobilecl']")
	private WebElement new_registrant1_phone;

	@FindBy(xpath = "//div[text()='Registrant - 1 of 3']//parent::div//child::input[contains(@id,'job_title')]")
	private WebElement new_registrant1_job_title;

	@FindBy(xpath = "//div[text()='Registrant - 1 of 3']//parent::div//child::input[contains(@id,'company')]")
	private WebElement new_registrant1_company;

	@FindBy(xpath = "//div[text()='Registrant - 1 of 3']//parent::div//child::input[contains(@id,'home_address_1')]")
	private WebElement new_registrant1_address1;

	@FindBy(xpath = "//div[text()='Registrant - 1 of 3']//parent::div//child::input[contains(@id,'home_address_2')]")
	private WebElement new_registrant1_address2;

	@FindBy(xpath = "//div[text()='Registrant - 1 of 3']//parent::div//child::input[contains(@id,'home_city')]")
	private WebElement new_registrant1_city;

	@FindBy(xpath = "//div[text()='Registrant - 1 of 3']//parent::div//child::select[contains(@id,'home_country')]")
	private WebElement new_registrant1_country;

	@FindBy(xpath = "//div[text()='Registrant - 1 of 3']//parent::div//child::select[contains(@id,'home_state')]")
	private WebElement new_registrant1_state;

	@FindBy(xpath = "//div[text()='Registrant - 1 of 3']//parent::div//child::input[contains(@id,'home_zip')]")
	private WebElement new_registrant1_zipcode;

	public void generateNewRegistrantsEmail() throws Exception {
		System.out.println("----------new registrants email---------");
		String new_registrant_initial = PropertyUtils.readProperty("newregistrant_initial");
		String new_registrant_domain = PropertyUtils.readProperty("newregistrant_domain");
		new_registrant_email = new_registrant_initial + RandomStringUtils.randomNumeric(4) + new_registrant_domain;
		System.out.println("Random new registrat email generated is: " + new_registrant_email);
	}

	public void createNewRegistrant1() throws Exception {
		// wait.until(ExpectedConditions.elementToBeClickable(new_registrant_email_address)).sendKeys(new_registrant_email);
		new_registrant_email_address.sendKeys(new_registrant_email);

		String new_registrant_firstname_data = PropertyUtils.readProperty("newregistrant_firstname");
		String new_registrant_lastname_data = PropertyUtils.readProperty("newregistrant_lastname");
		String new_registrant_phone_data = PropertyUtils.readProperty("newregistrant_phone");
		String new_registrant_jobtitle_data = PropertyUtils.readProperty("newregistrant_jobtitle");
		String new_registrant_company_data = PropertyUtils.readProperty("newregistrant_company");

		String newregistrant_address1_data = PropertyUtils.readProperty("newregistrant_address1");
		String newregistrant_address2_data = PropertyUtils.readProperty("newregistrant_address2");
		String newregistrant_city_data = PropertyUtils.readProperty("newregistrant_city");
		String newregistrant_country_data = PropertyUtils.readProperty("newregistrant_country");
		String newregistrant_state_data = PropertyUtils.readProperty("newregistrant_state");
		String newregistrant_zipcode_data = PropertyUtils.readProperty("newregistrant_zipcode");

		new_registrant1_first_name.sendKeys(new_registrant_firstname_data);
		new_registrant1_last_name.sendKeys(new_registrant_lastname_data);
		new_registrant1_phone.sendKeys(new_registrant_phone_data);
		new_registrant1_job_title.sendKeys(new_registrant_jobtitle_data);
		new_registrant1_company.sendKeys(new_registrant_company_data);

		new_registrant1_address1.sendKeys(newregistrant_address1_data);
		new_registrant1_address2.sendKeys(newregistrant_address2_data);
		new_registrant1_phone.sendKeys(newregistrant_city_data);

		Select newregistrantcountry = new Select(new_registrant1_country);
		newregistrantcountry.selectByVisibleText(newregistrant_country_data);

		Select newregistrantstate = new Select(new_registrant1_state);
		newregistrantstate.selectByVisibleText(newregistrant_state_data);

		new_registrant1_zipcode.sendKeys(newregistrant_zipcode_data);
		log.info("new registrants: " + new_registrant_email + ", details added");
	}

	@FindBy(xpath = "//div[text()='Registrant - 2 of 3']//parent::div//child::input[@class='admin__control-text contactemail required-entry']")
	private WebElement add_existing_registrant2_email;

	@FindBy(xpath = "//div[text()='Registrant - 2 of 3']//parent::div//child::input[@class='admin__control-text contactemail required-entry']//parent::div//descendant::li")
	private WebElement select_existing_registrant2;

	public void addExistingRegistrant2() throws Exception {
		String existingregistrant_emailid_data = PropertyUtils.readProperty("existingRegistrantemailid");
		add_existing_registrant2_email.sendKeys(existingregistrant_emailid_data);
		WebDriverWait wait = new WebDriverWait(
		        driver,
		        Duration.ofSeconds(20)
		);
		wait.until(ExpectedConditions.elementToBeClickable(select_existing_registrant2))
				.click();
		
		//new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(select_existing_registrant2))
		//.click();
		log.info("Existing Registrants " + existingregistrant_emailid_data + ", details added");
	}

	@FindBy(xpath = "//div[text()='Registrant - 3 of 3']//parent::div//child::input[contains(@name,'email_address')]")
	private WebElement existing_buyer_registrant3_email;

	@FindBy(xpath = "//div[text()='Registrant - 3 of 3']//parent::div//child::input[contains(@name,'email_address')]//parent::div//descendant::li")
	private WebElement select_buyer_existing_registrant3;

	public void addBuyerRegistrant3() throws Exception {

		existing_buyer_registrant3_email.sendKeys(customer_work_email);
		WebDriverWait wait = new WebDriverWait(
		        driver,
		        Duration.ofSeconds(20)
		);
		wait.until(ExpectedConditions.elementToBeClickable(select_buyer_existing_registrant3))
				.click();// code to select from suggestion list
		
		//new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(select_buyer_existing_registrant3))
		//.click();// code to select from suggestion list
		log.info("New customer/Buyer registrants :" + customer_work_email + ", details added");
	}

	@FindBy(xpath = "//select[contains(@id,'feeeventselect')]")
	private WebElement drpdown_select_event;// TESTING FEE-EVENT(9138)

	@FindBy(xpath = "//select[contains(@id,'feeeventCourseselect')]")
	private WebElement drpdown_select_course;

	@FindBy(xpath = "//input[contains(@id,'email_address')]")
	private WebElement add_email_address;

	@FindBy(xpath = "//input[contains(@id,'email_address')]//following::li")
	private WebElement select_email_address;

	// below method for testing fee
	public void addFeesRegistrants() throws Exception {
		log.info("I am under addFeesRegistrants method");
		String Fees_eventname_data = PropertyUtils.readProperty("Fees_eventname");
		Select select_event = new Select(drpdown_select_event);

		select_event.selectByVisibleText(Fees_eventname_data);
		log.info("Event selected");

		Thread.sleep(2000);
		String Fees_coursename_data = PropertyUtils.readProperty("Fees_coursename");
		Select select_eventcourse = new Select(drpdown_select_course);
		select_eventcourse.selectByVisibleText(Fees_coursename_data);
		log.info("Course selected");
		Thread.sleep(2000);
		String Fees_existing_registrant_data = PropertyUtils.readProperty("Fees_existing_registrant");
		add_email_address.sendKeys(Fees_existing_registrant_data);
		log.info("Added existing registrants email");
		Thread.sleep(2000);
		select_email_address.click();
		log.info("selected registrants");
	}

	@FindBy(xpath = "//button[@id='btnCheckout']")
	private WebElement btn_save_registrant;

	public void saveRegistrant() throws Exception {
		// ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,
		// document.body.scrollHeight)");// scroll down
		/*
		 * Thread.sleep(5000); Assert.assertEquals(btn_save_registrant.getText(),
		 * "Save"); log.info("save button is visible");
		 * 
		 * btn_save_registrant.click();
		 */
		wait.until(ExpectedConditions.elementToBeClickable(btn_save_registrant)).click();
		log.info("registrants SAVE button clicked");
	}

	@FindBy(xpath = "//span[text()='Payment & Shipping Information']")
	private WebElement section_payment_and_shipping_header;

	@FindBy(xpath = "//input[@id='s_method_freeshipping_freeshipping']")
	private WebElement radio_free_shipping;

	public void setFreeShipping() throws Exception {
		/*
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
		 * section_payment_and_shipping_header);
		 * 
		 * new WebDriverWait(driver,
		 * 20).until(ExpectedConditions.elementToBeClickable(radio_free_shipping)).click
		 * ();
		 */
		waitForLoaderToDisappear();

		wait.until(ExpectedConditions.visibilityOf(radio_free_shipping));

		wait.until(ExpectedConditions.elementToBeClickable(radio_free_shipping));

		radio_free_shipping.click();

		
		//wait.until(ExpectedConditions.elementToBeClickable(radio_free_shipping)).click();
		log.info("selected free shipping");

	}

	public void setFreeShippingForEditOrder() throws Exception {

		/*
		 * JavascriptExecutor jse = (JavascriptExecutor) driver;
		 * jse.executeScript("arguments[0].click()",
		 * section_payment_and_shipping_header);
		 * 
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
		 * section_payment_and_shipping_header);
		 */

		/*
		 * new WebDriverWait(driver,
		 * 20).until(ExpectedConditions.elementToBeClickable(radio_free_shipping)).click
		 * ();
		 */
		wait.until(ExpectedConditions.elementToBeClickable(section_payment_and_shipping_header)).click();
		wait.until(ExpectedConditions.elementToBeClickable(radio_free_shipping)).click();
		log.info("selected free shipping");
		Thread.sleep(2000);
	}

	@FindBy(xpath = "//input[@value='internalpayment']")
	private WebElement radio_internal_payment;

	@FindBy(xpath = "//input[@id='p_method_paylater']")
	private WebElement radio_paylater_payment;

	@FindBy(xpath = "//input[@id='due_date']")
	private WebElement add_due_date;

	public void setInternalPaymentoption() throws Exception {
		log.info("I am inside setInternalPaymentoption method");
		/*
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
		 * section_payment_and_shipping_header); new WebDriverWait(driver,
		 * 30).until(ExpectedConditions.elementToBeClickable(radio_internal_payment)).
		 * click();
		 */
		wait.until(ExpectedConditions.elementToBeClickable(radio_internal_payment)).click();
		log.info("selected INTERNAL PAYMENT method");
	}

	
	@FindBy(xpath = "//input[@title='Credit Card']")
	private WebElement select_creditcard_option;
	
	@FindBy(xpath = "//input[@title='Credit Card Number']")
	private WebElement add_creditcardnumber;
	
	@FindBy(xpath = "//select[@name='payment[cc_exp_month]']")
	private WebElement add_creditcardmonth;
	
	@FindBy(xpath = "//select[@name='payment[cc_exp_year]']")
	private WebElement add_creditcardyear;
	
	@FindBy(xpath = "//input[@title='Card Verification Number']")
	private WebElement add_creditcard_verificationnumber;
	
	public void setCCPaymentoption() throws Exception{
		wait.until(ExpectedConditions.elementToBeClickable(select_creditcard_option)).click();
		log.info("selected Credit Card method");
		Thread.sleep(4000);
		String creditcardnumber_data = PropertyUtils.readProperty("creditcardnumber");
		add_creditcardnumber.sendKeys(creditcardnumber_data);
		Thread.sleep(2000);
		String creditcardmonth_data = PropertyUtils.readProperty("creditcardmonth");
		Select add_creditcardmonth_data = new Select(add_creditcardmonth);
		add_creditcardmonth_data.selectByVisibleText(creditcardmonth_data);
		Thread.sleep(2000);
		String creditcardyear_data = PropertyUtils.readProperty("creditcardyear");
		Select add_creditcardyear_data = new Select(add_creditcardyear);
		add_creditcardyear_data.selectByVisibleText(creditcardyear_data);
		Thread.sleep(2000);
		String creditcardverificationnumber_data = PropertyUtils.readProperty("creditcardverificationnumber");
		add_creditcard_verificationnumber.sendKeys(creditcardverificationnumber_data);
	}
	
	
	@FindBy(xpath = "//input[@title='Credit Card Request']")
	private WebElement chkbox_ccrequest;

	@FindBy(xpath = "//input[@title='Invoice Request']")
	private WebElement chkbox_invoicerequest;

	public void setPaylaterPaymentoption() throws Exception {
		log.info("i m inside setPaylaterPaymentoption method ");
		/*
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
		 * section_payment_and_shipping_header); new WebDriverWait(driver,
		 * 30).until(ExpectedConditions.elementToBeClickable(radio_paylater_payment)).
		 * click(); Thread.sleep(2000);
		 */
		wait.until(ExpectedConditions.elementToBeClickable(radio_paylater_payment)).click();
		Thread.sleep(2000);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1); // select tomorows date
		String dt = sdf.format(c.getTime());

		add_due_date.sendKeys(dt);
		add_due_date.sendKeys(Keys.TAB);// this will click outside the due date field
		Thread.sleep(1000);
		log.info("selected PAY LATER method and added tomorows date as DUE DATE ");
		chkbox_invoicerequest.click();
		Thread.sleep(2000);

	}
	
	@FindBy(xpath = "//textarea[@id='order-comment']")
	private WebElement order_comment;
	
	public void add_order_comment() throws Exception {
		order_comment.sendKeys("comment");
	
	}

	@FindBy(xpath = "//button[@title='Submit Order']")
	private WebElement btn_submit_order;

	@FindBy(xpath = "//div[@class='page-title-wrapper']//h1")
	private WebElement order_id_label;

	public void order_submit() throws Exception {
		log.info(" i m inside order_submit method");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", btn_submit_order);

		// btn_submit_order.click();
		log.info("order submitted");
		Thread.sleep(5000);
		// ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
		// order_id_label);
		// orderid = new WebDriverWait(driver,
		// 50).until(ExpectedConditions.visibilityOf(order_id_label)).getText();
		orderid = wait.until(ExpectedConditions.elementToBeClickable(order_id_label)).getText();
		log.info(" order generated is: " + orderid);
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/backendordercreated-" + orderid + ".jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		// Thread.sleep(3000);

	}

	public void feeOrderSubmit() throws Exception {
		btn_submit_order.click();
		log.info("order submitted");
		/*
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
		 * order_id_label); orderid = new WebDriverWait(driver,
		 * 50).until(ExpectedConditions.visibilityOf(order_id_label)).getText();
		 */
		orderid = wait.until(ExpectedConditions.elementToBeClickable(order_id_label)).getText();
		log.info(" order generated is: " + orderid);
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/feeordercreated-" + orderid + ".jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		// Thread.sleep(3000);

	}

	@FindBy(xpath = "//div[@class='data-grid-filters-actions-wrap']//following-sibling::div[@class='admin__data-grid-filters-current _show']//button[text()='Clear all']")
	private WebElement link_clearallorder;

	@FindBy(xpath = "//div[@class='admin__data-grid-header']//descendant::button[text()='Filters']")
	private WebElement btn_filter;

	@FindBy(xpath = "//input[@name='increment_id']")
	private WebElement filter_orderid;

	@FindBy(xpath = "//button[@class='action-secondary']//span")
	private WebElement btn_apply_filter;

	@FindBy(xpath = "//td[@class='processing']//a[text()='View']")
	private WebElement link_order_view;

	public void clearAllOrderFilterLink() {
		
		log.info("i m insode clearAllOrderFilterLink method");
		try // add this try-catch so tht if elemtn not displayed then ELSE condition will
			// work
		{// if (wait.until(ExpectedConditions.visibilityOf(link_clearall)) != null) {

			if (link_clearallorder.isDisplayed()) {
				link_clearallorder.click();
				log.info("Order-CLEARALL link visible and clikced");
				Thread.sleep(7000);
			}
		} catch (Exception e)

		{
			log.info("clearall link not visible");
		}

	}

	public void clickOnOrderFiltersBtn() {
		
		log.info("i m inside clickOnOrderFiltersBtn method");
		btn_filter.click();
		log.info("clickOnOrderFiltersBtn-FILTER button clicked");
	}

	public void setNewlyCreatedOrderIDFilter() throws Exception {
		/*
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
		 * filter_orderid); new WebDriverWait(driver,
		 * 20).until(ExpectedConditions.visibilityOf(filter_orderid)).sendKeys(orderid);
		 */
		Thread.sleep(3000);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", filter_orderid);

		filter_orderid.sendKeys(orderid);
		log.info("added newly created order id");
	}

	public void clickApplyfilterBtn() {
		btn_apply_filter.click();
		log.info("APPLY FILTER button clicked");
	}

	@FindBy(xpath = "//*[@name='customer_email'][@class='admin__control-text'][@type='text']")
	private WebElement cust_email_filter;

	public void setCustomerFilter() throws Exception {
		log.info("I am inside setCustomerFilter program");
		String searchexistingcustomer_data = PropertyUtils.readProperty("searchexistingcustomer");

		/*
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
		 * cust_email_filter); cust_email_filter.clear(); new WebDriverWait(driver,
		 * 20).until(ExpectedConditions.visibilityOf(cust_email_filter))
		 * .sendKeys(searchexistingcustomer_data);
		 */

		//wait.until(ExpectedConditions.elementToBeClickable(cust_email_filter)).sendKeys(searchexistingcustomer_data);
		// cust_email_filter.sendKeys(existingregistrant_emailid_data);
		cust_email_filter.clear();

		wait.until(ExpectedConditions.elementToBeClickable(cust_email_filter)).sendKeys(searchexistingcustomer_data);
		
		
		log.info("Existing customer email filter set");
	}

	@FindBy(xpath = "//*[@name='status'][@class='admin__control-select']")
	private WebElement order_status_filter;

	@FindBy(xpath = "//input[@name='increment_id']")
	private WebElement order_id_filter;

	public void setOrderProcessedStatusFilter() throws Exception {

		Select orderstatusfilter = new Select(order_status_filter);
		orderstatusfilter.selectByVisibleText("Processed");
	}

//below method specifically for refunding specific ORDER ID
	public void setOrderIdFilter() throws Exception {
		existingorderid_data = PropertyUtils.readProperty("existingorderid");

		order_id_filter.sendKeys(existingorderid_data);
	}

	public void setOrderPendingStatusFilter() throws Exception {

		Select orderstatusfilter = new Select(order_status_filter);
		orderstatusfilter.selectByVisibleText("Pending");
	}

	//@FindBy(xpath = "//tr[@data-repeat-index='0']//div[contains(text(),'0000')]")
	@FindBy(xpath = "//div [@class='data-grid-cell-content']")
		private WebElement first_record;

	public void selectFirstRecord() {

		js.executeScript("scroll(0, 200);");
		log.info("scrolled");
		try // add this try-catch so tht if elemtn not displayed then ELSE condition will
			// work
		{
			if (first_record.isDisplayed()) {
				first_record.click();
				log.info("first record selected");
			}
		} catch (Exception e) {
			log.info("no record found so closing the browser");
			driver.close();///////////// need to find better solution for it

		}
	}

	public void clickViewOrderLink() throws Exception {
		log.info("I am inside clickViewOrderLink method");
		/*js.executeScript("scroll(0, 125);");
		link_order_view.click();
		log.info("View order link selected");

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/FrontEndOrderDetails-" + orderid + ".jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		*/
		js.executeScript("scroll(0, 200);");
		log.info("scrolled");
		try // add this try-catch so tht if elemtn not displayed then ELSE condition will
			// work
		{
			if (first_record.isDisplayed()) {
				first_record.click();
				log.info("order record selected");
			}
		} catch (Exception e) {
			log.info("no record found so closing the browser");
			//driver.close();///////////// need to find better solution for it

		}
	}

	@FindBy(xpath = "//button[@id='order_creditmemo']")
	private WebElement link_credit_memo;

	@FindBy(xpath = "//div[contains(text(),'Do you want to continue?')]")
	private WebElement msg_confirmation_credit_memo;

	@FindBy(xpath = "//button[@class='action-primary action-accept']")
	private WebElement btn_confirmation_ok;

	public void clickCreditMemoLink() throws Exception {
		try {
			if (link_credit_memo.isDisplayed()) {
				log.info("CREDIT MEMO link enabled");
				link_credit_memo.click();
				log.info("CREDIT MEMO link clicked");
				Thread.sleep(3000);
				/*
				 * try // add this try-catch so tht if elemtn not displayed then ELSE condition
				 * will // work { if ((msg_confirmation_credit_memo).isEnabled()) {
				 * log.info("offline refund confirmation msg is: " +
				 * msg_confirmation_credit_memo.getText()); btn_confirmation_ok.click();
				 * log.info("offline refund confirmed"); } } catch (Exception e) {
				 * log.info("credit memo confirmaiton msg didnt display"); }
				 * 
				 * }
				 */
			}
		}

		catch (Exception e) {
			log.info("credit memo link not visible");
		}

	}

	@FindBy(xpath = "//span[@class='title' and text()='Items to Refund']")
	private WebElement section_items_to_refund;

	@FindBy(xpath = "//input[contains(@name,'[back_to_stock]')]")
	private List<WebElement> chkbx_return_to_stock;

	@FindBy(xpath = "//input[contains(@id,'chbox_')]")
	private List<WebElement> chkbx_registrants;// defined this in case of multiple checkboxes

	public void setItemsToRefund() throws Exception {

		log.info("i m in setItemsToRefund method");
		section_items_to_refund.click();
		Thread.sleep(2000);

		List<WebElement> ReturnToStockAllCheckboxes = chkbx_return_to_stock; // driver.findElements(By.xpath("//input[contains(@id,'chbox_')]"));//this
		for (int i = 0; i < ReturnToStockAllCheckboxes.size(); i++) {
			if (ReturnToStockAllCheckboxes.get(i).isDisplayed() && ReturnToStockAllCheckboxes.get(i).isEnabled()) {
				System.out.println("ReturnToStock Checkbox is displayed at index : " + i + " Clicking on it now");
				ReturnToStockAllCheckboxes.get(i).click();
			}
		} // is xpath for registrants checkboxes

		Thread.sleep(2000);
		List<WebElement> RegistrantsAllCheckboxes = chkbx_registrants;// driver.findElements(By.xpath("//input[contains(@id,'chbox_')]"));//this
		for (int i = 0; i < RegistrantsAllCheckboxes.size(); i++) {
			if (RegistrantsAllCheckboxes.get(i).isDisplayed() && RegistrantsAllCheckboxes.get(i).isEnabled()) {
				System.out.println("Registrants Checkbox is displayed at index : " + i + " Clicking on it now");
				RegistrantsAllCheckboxes.get(i).click();
			}
		}

		log.info("checkboxes selected");
	}

	@FindBy(xpath = "//span[text()='Order Total']")
	private WebElement section_order_total;

	@FindBy(xpath = "//input[@name='creditmemo[send_email]']")
	private WebElement chkbx_email_copy_of_credit_memo;

	public void setOrderDetails() throws Exception {
		section_order_total.click();
		Thread.sleep(2000);
		chkbx_email_copy_of_credit_memo.click();
		log.info("checkbox EMAIL COPY TO CREDIT MEMO is clicked");
	}

	@FindBy(xpath = "//button[@title='Refund Offline']")
	private WebElement btn_refund_offline;

	public void clickRefundOfflineButton() {
		btn_refund_offline.click();
	}

	@FindBy(xpath = "//div[@class='message message-success success']//div")
	private WebElement msg_order_success;

	@FindBy(xpath = "//div[@class='admin__page-section-item order-information']//following::span[@id='order_status']")
	private WebElement order_status;

	public void confirmOrderRefund() {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot,
					new File("target/screenshots/orederrefund-" + existingorderid_data + ".jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		softAssert.assertEquals(msg_order_success.getText(), "You created the credit memo.");
		log.info("Order refund successfull");

		log.info("Order status is: " + order_status.getText());

	}

	// below methods for order CANCEL flow

	@FindBy(xpath = "//button[@id='order-view-cancel-button']")
	private WebElement btn_order_cancel;

	@FindBy(xpath = "//*[@class='modal-popup confirm _show']//div//div//div")
	private WebElement msg_order_cancel_confirm;

	@FindBy(xpath = "//button[@class='action-primary action-accept']")
	private WebElement btn_order_cancel_ok;

	@FindBy(xpath = "//div[@class='message message-success success']")
	private WebElement msg_order_cancel_success;

	/*
	 * @FindBy(xpath =
	 * "//div[@class='admin__page-section-item order-information']//following::span[@id='order_status']"
	 * ) private WebElement order_status;
	 * 
	 * @FindBy(xpath = "//div[@class='message message-success success']//div")
	 * private WebElement msg_order_success;
	 */

	public void clickCancelOrderLink() throws Exception {
		try // add this try-catch so tht if elemtn not displayed then ELSE condition will
		// work
		{
			if (btn_order_cancel.isDisplayed()) {
				log.info("ORDER CANCEL link enabled");
				btn_order_cancel.click();
				log.info("ORDER CANCEL  link clicked");
				Thread.sleep(6000);
				
				
				try // add this try-catch so tht if elemtn not displayed then ELSE condition will
					// work
				{
					if ((msg_order_cancel_confirm).isEnabled()) {
						log.info("Order cancellation confirmation msg is: " + msg_order_cancel_confirm.getText());
						btn_order_cancel_ok.click();
						log.info("Order cancellation confirmed");
					}
				} catch (Exception e) {
					log.info("order cancellation confirmaiton msg didnt display");

				}
			}
		}

		catch (Exception e) {
			log.info("CANCEL ORDER link not visible");
		}

	}

	public void confirmOrderCancel() {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/oredercancel.jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		softAssert.assertEquals(msg_order_success.getText(), "You canceled the order.");
		log.info("Order cancellation successfull");

		log.info("Order status is: " + order_status.getText());

	}

	// Below are methods for EDIT order flow

	@FindBy(xpath = "//button[@id='order_edit']")
	private WebElement btn_order_edit;

	@FindBy(xpath = "//aside[@class='modal-popup edit-order-popup _show']//following::div[@class='modal-content']")
	private WebElement msg_order_edit_confirm;

	//@FindBy(xpath = "//div[@class='modals-wrapper']//following::footer[@class='modal-footer']//button")
	//@FindBy(xpath = "//aside[@role='dialog' and @class='modal-popup edit-order-popup _show']//child::button[@class='action-primary']")
	@FindBy(xpath = "//footer[@class='modal-footer']//button//span")
	
	private WebElement btn_order_edit_ok;
	
	
	//div[@class='modals-wrapper'//

	public void clickEditOrderLink() throws Exception {
		try // add this try-catch so tht if elemtn not displayed then ELSE condition will
		// work
		{
			if (btn_order_edit.isDisplayed()) {
				log.info("EDIT ORDER link enabled");
				btn_order_edit.click();
				log.info("EDIT ORDER link clicked");
				Thread.sleep(5000);
				
				/*Alert alert = driver.switchTo().alert(); // switch to alert

				String alertMessage= driver.switchTo().alert().getText(); // capture alert message
				
				log.info("alert msg is:"+alertMessage);*/
				
				try // add this try-catch so tht if elemtn not displayed then ELSE condition will
					// work
				{
					if ((msg_order_edit_confirm).isEnabled()) {
						log.info("ORDER edit confirmation msg is: " + msg_order_edit_confirm.getText());
						btn_order_edit_ok.click();
						log.info("Edit order confirmed");
					}
				} catch (Exception e) {
					log.info("Edit Order confirmaiton msg didnt display");
				}
			}
		}

		catch (Exception e) {
			log.info("EDIT ORDER link not visible");
		}

	}

	@FindBy(xpath = "//input[contains(@name,'[qty]')]")
	private WebElement add_item_qty;

	@FindBy(xpath = "//*[@class='admin__collapsible-title']//span[text()='Registrants']")
	private WebElement section_registrants;

	/*
	 * @FindBy(xpath = "//button[@title='Update Items and Quantities']") private
	 * WebElement btn_update_items_and_quantities;
	 */

	/*
	 * @FindBy(xpath = "//button[@id='add_registant']") private WebElement
	 * btn_add_registrants;
	 */

	public void modifyItemQty() throws Exception {
		String edititemqty_data = PropertyUtils.readProperty("edititemqty");
		add_item_qty.clear();
		add_item_qty.sendKeys(edititemqty_data);
		log.info("added product quantity as: " + edititemqty_data);
	}

	public void increaseItemQty() throws Exception {
		String edititemqty_data = PropertyUtils.readProperty("editandincreaseitemqty");
		add_item_qty.clear();
		add_item_qty.sendKeys(edititemqty_data);
		log.info("added product quantity as: " + edititemqty_data);
	}

	public void clickRegistrantsSection() {
		log.info("i m inside clickRegistrantsSection method");
		section_registrants.click();

	}

	/*
	 * public void clicOnAddRegistrantsBtn() { WebDriverWait wait = new
	 * WebDriverWait(driver, 10); WebElement element =
	 * wait.until(ExpectedConditions.elementToBeClickable(btn_add_registrants));
	 * ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
	 * element); log.info("ADD REGISTRANTS button clicked");
	 * 
	 * }
	 */
	/*
	 * public void saveRegistrant() throws Exception { ((JavascriptExecutor)
	 * driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");//
	 * scroll down Thread.sleep(5000);
	 * Assert.assertEquals(btn_save_registrant.getText(), "Save");
	 * log.info("save button is visible");
	 * 
	 * btn_save_registrant.click(); log.info("registrants SAVE button clicked"); }
	 */

	@FindBy(xpath = "//button[@title='Update Order']")
	private WebElement btn_order_update;

	public void order_update() throws Exception {
		btn_order_update.click();
		log.info("order edited");
		/*
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
		 * order_id_label); orderid = new WebDriverWait(driver,
		 * 50).until(ExpectedConditions.visibilityOf(order_id_label)).getText();
		 */
		orderid = wait.until(ExpectedConditions.elementToBeClickable(order_id_label)).getText();
		log.info(" order edited is: " + orderid);
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/orderedited.jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		// Thread.sleep(3000);

	}

	public void confirmOrderEdit() {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/orederrefund.jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		softAssert.assertEquals(msg_order_success.getText(), "You created the credit memo.");
		log.info("Order refund successfull");

		log.info("Order status is: " + order_status.getText());

	}

	@FindBy(xpath = "//input[@id='billerfirstname']")
	private WebElement customerfirstname;

	@FindBy(xpath = "//input[@id='billerlastname']")
	private WebElement customerlastname;

	@FindBy(xpath = "//input[@id='billeremail']")
	private WebElement customeremail;

	@FindBy(xpath = "//input[@id='masterAccount']")
	private WebElement masteraccount;

	@FindBy(xpath = "//input[@id='OrderAccount']")
	private WebElement orderaccount;

	@FindBy(xpath = "//button[@id='saveSalesOrder']")
	private WebElement btn_saveaccountinfo;

	@FindBy(xpath = "//span[text()='Address Information']")
	private WebElement section_addressinfo;

	@FindBy(xpath = "//span[text()='Back']")
	private WebElement link_back;

	@FindBy(xpath = "//div[contains(text(),'customernewfname')]")
	private WebElement fnameshiptobilltoname;

	@FindBy(xpath = "//div[contains(text(),'customernewlname')]")
	private WebElement lnameshiptobilltoname;

	@FindBy(xpath = "//div[contains(text(),'customernewfname customernewlname')]")
	private WebElement fnamelnameshiptobilltoname;

	@FindBy(xpath = "//address[contains(text(),'customernewfname')]")
	private WebElement fname_addressinfocustomername;

	@FindBy(xpath = "//address[contains(text(),'customernewlname')]")
	private WebElement lname_addressinfocustomername;

	@FindBy(xpath = "//address[contains(text(),'customernewfname customernewlname')]")
	private WebElement fnamelname_addressinfocustomername;

	public void updateCustomerfirstname() throws Exception {
		log.info("I am inside updateCustomerfirstname method");
		Thread.sleep(2000);
		customerupdatedfname = PropertyUtils.readProperty("customerupdatedfname");
		customerfirstname.clear();
		customerfirstname.sendKeys(customerupdatedfname);
		log.info("Added new customer fname is" + customerupdatedfname);
		Thread.sleep(1000);
		btn_saveaccountinfo.click();
		log.info("SAVE account info button clicked");
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		log.info("alert msg is:- " + alert.getText());
		alert.accept();
		File screenshot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot2, new File("target/screenshots/AccountInfoAftercustomerfnameUPDATE.jpg"));
			log.info("ADDRESS INFO section screenshot captured to check updated info");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		/*
		 * JavascriptExecutor jse = (JavascriptExecutor)driver;
		 * jse.executeScript("arguments[0].click()", section_addressinfo);
		 */

		section_addressinfo.click();
		js.executeScript("scroll(0, 700);");
		log.info("ADDRESS INFO  section twisty opened");
		Thread.sleep(1000);
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/AddressInfoAftercustomerfnameUPDATE.jpg"));
			log.info("ADDRESS INFO section screenshot captured to check updated info");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		log.info("firstname displaying in Adress info section is: " + fname_addressinfocustomername.getText());

		Thread.sleep(1000);
		link_back.click();
		log.info("BACK link clicked");
		Thread.sleep(5000);

		log.info("I am back in order list page");
		js.executeScript("scroll(0, 210);");

		File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot1, new File("target/screenshots/OrderGridAfterCustomerfnameUpdate.jpg"));
			log.info("ORDER GRID screenshot captured");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		if (fnameshiptobilltoname.getText().equals("customernewfname lname"))
			log.info("updated customer firstname visible in order grid");
		else
			log.info("updated customer firstname is NOT visible in order grid");
	}

	public void updateCustomerlastname() throws Exception {
		log.info("I am inside updateCustomerlastname method");
		Thread.sleep(2000);
		customerupdatedlname = PropertyUtils.readProperty("customerupdatedlname");
		customerlastname.clear();
		customerlastname.sendKeys(customerupdatedlname);
		log.info("Added new customer lname is: " + customerupdatedlname);
		Thread.sleep(1000);
		btn_saveaccountinfo.click();
		log.info("SAVE account info button clicked");
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		log.info("alert msg is:- " + alert.getText());
		alert.accept();
		File screenshot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot2, new File("target/screenshots/AccountInfoAftercustomerlnameUPDATE.jpg"));
			log.info("ADDRESS INFO section screenshot captured to check updated info");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		/*
		 * JavascriptExecutor jse = (JavascriptExecutor)driver;
		 * jse.executeScript("arguments[0].click()", section_addressinfo);
		 */

		section_addressinfo.click();
		js.executeScript("scroll(0, 700);");

		log.info("ADDRESS INFO  section twisty opened");
		Thread.sleep(1000);
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/AddressInfoAftercustomerlnameUPDATE.jpg"));
			log.info("ADDRESS INFO section screenshot captured to check updated info");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		log.info("lastname displaying in Adress info section is: " + lname_addressinfocustomername.getText());
		Thread.sleep(1000);
		link_back.click();
		log.info("BACK link clicked");
		Thread.sleep(5000);

		log.info("I am back in order list page");
		js.executeScript("scroll(0, 210);");

		File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot1, new File("target/screenshots/OrderGridAfterCustomerlnameUpdate.jpg"));
			log.info("ORDER GRID screenshot captured");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		if (lnameshiptobilltoname.getText().equals("flipickbiller customernewlname"))
			log.info("updated customer lastname visible in order grid");
		else
			log.info("updated customer lastname is NOT visible in order grid");
	}

	public void updateCustomeremail() throws Exception {
		log.info("I am inside updateCustomeremail method");
		Thread.sleep(2000);
		customerupdatedemail = PropertyUtils.readProperty("customerupdatedemail");
		customeremail.clear();
		customeremail.sendKeys(customerupdatedemail);
		log.info("Added new customer email is: " + customerupdatedemail);
		Thread.sleep(1000);
		btn_saveaccountinfo.click();
		log.info("SAVE account info button clicked");
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		log.info("alert msg is:- " + alert.getText());
		alert.accept();
		File screenshot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot2, new File("target/screenshots/AccountInfoAftercustomeremailUPDATE.jpg"));
			log.info("ADDRESS INFO section screenshot captured to check updated info");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		driver.navigate().refresh();
		log.info("order details page is refreshed");
		Thread.sleep(3000);

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot,
					new File("target/screenshots/RelaunchedFrontEndOrderDetails-" + orderid + ".jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		log.info("After page refresh, Customer email id displaying is: " + customeremail.getAttribute("value"));

		if (customeremail.getAttribute("value").equals(customerupdatedemail))
			log.info("updated customer email retains correctly in customer email field");
		else
			log.info("updated customer email is NOT retaining in customer email field");
	}

	public void updateCustomerfnamelnameemail() throws Exception {
		log.info("I am inside updateCustomerfnamelnameemail method");
		Thread.sleep(2000);
		customerupdatedfname = PropertyUtils.readProperty("customerupdatedfname");
		customerupdatedlname = PropertyUtils.readProperty("customerupdatedlname");
		customerupdatedemail = PropertyUtils.readProperty("customerupdatedemail");
		customerfirstname.clear();
		customerfirstname.sendKeys(customerupdatedfname);
		customerlastname.clear();
		customerlastname.sendKeys(customerupdatedlname);
		customeremail.clear();
		customeremail.sendKeys(customerupdatedemail);
		log.info("Added new customer firstname, lastname and email");
		Thread.sleep(1000);
		btn_saveaccountinfo.click();
		log.info("SAVE account info button clicked");
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		log.info("alert msg is:- " + alert.getText());
		alert.accept();
		File screenshot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot2,
					new File("target/screenshots/AccountInfoAftercustomerfnamelnameemailUPDATE.jpg"));
			log.info("ADDRESS INFO section screenshot captured to check updated info");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		Thread.sleep(2000);

		section_addressinfo.click();
		js.executeScript("scroll(0, 700);");

		log.info("ADDRESS INFO  section twisty opened");
		Thread.sleep(1000);
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot,
					new File("target/screenshots/AddressInfoAftercustomerfnamelnameemailUPDATE.jpg"));
			log.info("ADDRESS INFO section screenshot captured to check updated info");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		log.info("firstname and lastname displaying in Adress info section is: "
				+ fnamelname_addressinfocustomername.getText());

		driver.navigate().refresh();
		log.info("order details page is refreshed");
		Thread.sleep(2000);
		if (customerfirstname.getAttribute("value").equals(customerupdatedfname))
			log.info("updated customer fname retains correctly in customer email field");
		else
			log.info("updated customer fname is NOT retaining in customer email field");

		if (customerlastname.getAttribute("value").equals(customerupdatedlname))
			log.info("updated customer lname retains correctly in customer email field");
		else
			log.info("updated customer lname is NOT retaining in customer email field");

		if (customeremail.getAttribute("value").equals(customerupdatedemail))
			log.info("updated customer email retains correctly in customer email field");
		else
			log.info("updated customer email is NOT retaining in customer email field");

		Thread.sleep(1000);
		link_back.click();
		log.info("BACK link clicked");
		Thread.sleep(5000);

		log.info("I am back in order list page");
		js.executeScript("scroll(0, 210);");

		File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot1, new File("target/screenshots/OrderGridAfterCustomerlnameUpdate.jpg"));
			log.info("ORDER GRID screenshot captured");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		if (lnameshiptobilltoname.getText().equals("customernewfname customernewlname"))
			log.info("updated customer firstname and lastname visible in order grid");
		else
			log.info("updated customer firstname and lastname is NOT visible in order grid");
	}

	String updatedmasteraccount;

	public void updatemasteraccountwithuniquevalue() throws Exception {
		log.info("I am inside updatemasteraccount method");
		Thread.sleep(2000);
		updatedmasteraccount = PropertyUtils.readProperty("updatedmasteraccount");
		masteraccount.clear();
		masteraccount.sendKeys(updatedmasteraccount);
		log.info("Added new master account is: " + updatedmasteraccount);
		Thread.sleep(1000);
		btn_saveaccountinfo.click();
		log.info("SAVE account info button clicked");
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		log.info("alert msg is:- " + alert.getText());
		alert.accept();
		File screenshot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot2, new File("target/screenshots/AccountInfoAftermasteraccountUPDATE.jpg"));
			log.info("ADDRESS INFO section screenshot captured to check updated info");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		driver.navigate().refresh();
		log.info("order details page is refreshed");
		Thread.sleep(3000);

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot,
					new File("target/screenshots/RelaunchedFrontEndOrderDetails-" + orderid + ".jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		log.info("After page refresh, master account id displaying is: " + masteraccount.getAttribute("value"));

		if (masteraccount.getAttribute("value").equals(masteracc))
			log.info("updated master account retains correctly in Master Account field");
		else
			log.info("updated master account is NOT retaining in Master Account field");
	}

	String pickupmasteraccount;

	public void updatemasteraccountfromsuggestionlist() throws Exception {
		log.info("I am inside updatemasteraccountfromsuggestionlist method");
		Thread.sleep(2000);
		pickupmasteraccount = PropertyUtils.readProperty("pickupmasteraccount");
		masteraccount.clear();
		masteraccount.sendKeys(pickupmasteraccount);
		log.info("Added new master account is: " + pickupmasteraccount);
		Thread.sleep(1000);

		masteraccountsuggestionlist.click();
		Thread.sleep(1000);

		btn_saveaccountinfo.click();
		log.info("SAVE account info button clicked");
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		log.info("alert msg is:- " + alert.getText());
		alert.accept();
		File screenshot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot2, new File("target/screenshots/AccountInfoAftermasteraccountUPDATE.jpg"));
			log.info("ADDRESS INFO section screenshot captured to check updated info");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		driver.navigate().refresh();
		log.info("order details page is refreshed");
		Thread.sleep(3000);

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot,
					new File("target/screenshots/RelaunchedFrontEndOrderDetails-" + orderid + ".jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		log.info("After page refresh, master account id displaying is: " + masteraccount.getAttribute("value"));

		if (masteraccount.getAttribute("value").equals(pickupmasteraccount))
			log.info("updated master account retains correctly in Master Account field");
		else
			log.info("updated master account is NOT retaining in Master Account field");
	}

	@FindBy(xpath = "//input[@id='masterAccount']//following-sibling::div//li")
	private WebElement masteraccountsuggestionlist;

	String masteracc;

	public void updateorderaccountandMasterAccount() throws Exception {
		log.info("I am inside updateorderaccountandMasterAccount method");
		Thread.sleep(2000);
		updatedorderaccount = PropertyUtils.readProperty("updatedorderaccount");
		orderaccount.clear();
		orderaccount.sendKeys(updatedorderaccount);
		log.info("Added new order account is: " + updatedorderaccount);
		Thread.sleep(1000);

		masteracc = PropertyUtils.readProperty("updatedmasteraccount");
		masteraccount.clear();
		masteraccount.sendKeys(masteracc);
		masteraccountsuggestionlist.click();
		log.info("Added new master account is: " + masteracc);
		Thread.sleep(1000);

		btn_saveaccountinfo.click();
		log.info("SAVE account info button clicked");
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		log.info("alert msg is:- " + alert.getText());
		alert.accept();
		File screenshot2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot2, new File("target/screenshots/AccountInfoAftermasteraccountUPDATE.jpg"));
			log.info("ADDRESS INFO section screenshot captured to check updated info");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		driver.navigate().refresh();
		log.info("order details page is refreshed");
		Thread.sleep(3000);

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot,
					new File("target/screenshots/RelaunchedFrontEndOrderDetails-" + orderid + ".jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		log.info("After page refresh, order account id displaying is: " + orderaccount.getAttribute("value"));

		if (orderaccount.getAttribute("value").equals(updatedorderaccount))
			log.info("updated order account retains correctly in order Account field");
		else
			log.info("updated order account is NOT retaining in Order Account field");

		log.info("After page refresh, master account id displaying is: " + masteraccount.getAttribute("value"));

		if (masteraccount.getAttribute("value").equals(masteracc))
			log.info("updated master account retains correctly in order Account field");
		else
			log.info("updated master account is NOT retaining in Order Account field");

	}

	public void backToOrderlistPage() {
		link_back.click();
	}

	public void orderGridScreenshot() {
		js.executeScript("scroll(0, 180);");
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/ordergrid-" + orderid + ".jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// -----------------------------TBD flow methods-----------------
	@FindBy(xpath = "//a[span='Create TBD Order']")
	private WebElement linkcreatetbdorder;

	@FindBy(xpath = "//input[@id='account_name']")
	private WebElement inputaccountname;

	/*
	 * @FindBy(xpath = "//div[@id='elmtmasteraccount']//li[text()='Mediawide']")
	 * private WebElement selectaccountnamefromsuggestionlist;
	 */

	@FindBy(xpath = "//input[@id='agreed_price']")
	private WebElement inputagreedprice;

	@FindBy(xpath = "//input[@id='agreed_quantity']")
	private WebElement inputagreedqty;

	@FindBy(xpath = "//input[@id='expiration_date']")
	private WebElement inputexpdate;

	@FindBy(xpath = "//input[@id='po_id']")
	private WebElement inputposoid;

	@FindBy(xpath = "//input[@id='invoice_id']")
	private WebElement inputinvoiceid;

	@FindBy(xpath = "//select[@id='payment_status']")
	private WebElement selectpaymentstatus;

	@FindBy(xpath = "//textarea[@id='note']")
	private WebElement inputnote;

	@FindBy(xpath = "//button[@id='btnsave']")
	private WebElement btnsave;

	public void selectCreateTBDOrderLink() throws Exception {
		linkcreatetbdorder.click();
	}

	public String accountnameinitialstring = "//div[@id='elmtmasteraccount']//li[text()='";
	public String accountnameendstring = "']";

	SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");

	public void generateInvoiceId() {
		System.out.println("----------invoice id---------");
		invoiceid = "invoice" + RandomStringUtils.randomNumeric(4) + "FLP";
		System.out.println("Random invoice id generated is: " + invoiceid);

	}

	@FindBy(xpath = "//select[@id='Salesperson']")
	private WebElement dropdown_salesperson;

	public void createTBDOrder() throws Exception {
		log.info("Create TBD order page opened");
		String twochar_tbdorderaccountname_data = PropertyUtils.readProperty("twochar_tbdorderaccountname");
		String toSelecttbdorderaccountname_data = PropertyUtils.readProperty("toSelecttbdorderaccountname");

		inputaccountname.sendKeys(twochar_tbdorderaccountname_data);
		log.info("Add 2 characters: " + twochar_tbdorderaccountname_data + ", in Account name");
		WebElement selectaccountname = driver.findElement(
				By.xpath(accountnameinitialstring + toSelecttbdorderaccountname_data + accountnameendstring));
		WebDriverWait wait = new WebDriverWait(
		        driver,
		        Duration.ofSeconds(20)
		);
		
		wait.until(ExpectedConditions.elementToBeClickable(selectaccountname)).click();
		log.info("Selected Account name: " + toSelecttbdorderaccountname_data);
		Thread.sleep(2000);

		Select salesperson = new Select(dropdown_salesperson);
		salesperson.selectByVisibleText("Sales User");

		inputagreedprice.sendKeys("10");
		Thread.sleep(1000);
		log.info("Agreed price added");
		inputagreedqty.sendKeys("10");
		Thread.sleep(1000);
		log.info("Agreed qty added");

		/*
		 * Date date = new Date(); SimpleDateFormat sdf = new
		 * SimpleDateFormat("MM-dd-yyyy"); String formattedDate = sdf.format(date);
		 */

		// Create a Calendar Object
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		// Get Current Day as a number
		int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("Today Int: " + todayInt + "\n");
		// Integer to String Conversion
		String todayStr = Integer.toString(todayInt);

		log.info("date to be set as exp date is=" + todayStr);
		inputexpdate.sendKeys(todayStr);
		Thread.sleep(1000);
		log.info("expiry date added");

		inputposoid.sendKeys("10");
		Thread.sleep(1000);
		log.info("poso id added");
		inputinvoiceid.sendKeys(invoiceid);
		Thread.sleep(1000);
		log.info("invoice id added");
		inputnote.sendKeys("test notes");
		Thread.sleep(1000);
		log.info("test notes added");
		Thread.sleep(1000);
		btnsave.click();
		log.info("save button clicked");

	}

	@FindBy(xpath = "//button[text()='Create Actual Order']")
	private WebElement btncreateactualorder;

	public void clickCreateActualOrderbtn() throws Exception {
		btncreateactualorder.click();
		log.info("CREATE ACTUAL ORDER button clicked");
	}

	@FindBy(xpath = "//input[contains(@name,'email_address')]")
	private WebElement new_registrant_email_address;

	@FindBy(xpath = "//input[contains(@name,'first_name')]")
	private WebElement new_registrant_first_name;

	@FindBy(xpath = "//input[contains(@name,'last_name')]")
	private WebElement new_registrant_last_name;

	@FindBy(xpath = "//input[contains(@name,'home_phone')]")
	private WebElement new_registrant_phone;

	@FindBy(xpath = "//input[@class='admin__control-text required-entry' and contains(@name,'_job_title')]")
	private WebElement new_registrant_job_title;

	@FindBy(xpath = "//input[contains(@name,'_company')and @class='contactcompany admin__control-text required-entry']")
	private WebElement new_registrant_company;

	@FindBy(xpath = "//input[contains(@name,'home_address_1')]")
	private WebElement new_registrant_address1;

	@FindBy(xpath = "//input[contains(@name,'home_address_2')]")
	private WebElement new_registrant_address2;

	@FindBy(xpath = "//input[contains(@name,'home_city)]")
	private WebElement new_registrant_city;

	@FindBy(xpath = "//select[contains(@name,'home_country')]")
	private WebElement new_registrant_country;

	@FindBy(xpath = "//select[contains(@name,'ddhome_state')]")
	private WebElement new_registrant_state;

	@FindBy(xpath = "//input[contains(@name,'_home_zip')]")
	private WebElement new_registrant_zipcode;

	public void createNewRegistrant() throws Exception {
		// wait.until(ExpectedConditions.elementToBeClickable(new_registrant_email_address)).sendKeys(new_registrant_email);
		new_registrant_email_address.sendKeys(new_registrant_email);

		String new_registrant_firstname_data = PropertyUtils.readProperty("newregistrant_firstname");
		String new_registrant_lastname_data = PropertyUtils.readProperty("newregistrant_lastname");
		String new_registrant_phone_data = PropertyUtils.readProperty("newregistrant_phone");
		String new_registrant_jobtitle_data = PropertyUtils.readProperty("newregistrant_jobtitle");
		String new_registrant_company_data = PropertyUtils.readProperty("newregistrant_company");

		String newregistrant_address1_data = PropertyUtils.readProperty("newregistrant_address1");
		String newregistrant_address2_data = PropertyUtils.readProperty("newregistrant_address2");
		String newregistrant_city_data = PropertyUtils.readProperty("newregistrant_city");
		String newregistrant_country_data = PropertyUtils.readProperty("newregistrant_country");
		String newregistrant_state_data = PropertyUtils.readProperty("newregistrant_state");
		String newregistrant_zipcode_data = PropertyUtils.readProperty("newregistrant_zipcode");

		new_registrant_first_name.sendKeys(new_registrant_firstname_data);
		new_registrant_last_name.sendKeys(new_registrant_lastname_data);
		new_registrant_phone.sendKeys(new_registrant_phone_data);
		new_registrant_job_title.sendKeys(new_registrant_jobtitle_data);
		new_registrant_company.sendKeys(new_registrant_company_data);

		new_registrant_address1.sendKeys(newregistrant_address1_data);
		new_registrant_address2.sendKeys(newregistrant_address2_data);
		new_registrant_phone.sendKeys(newregistrant_city_data);

		Select newregistrantcountry = new Select(new_registrant_country);
		newregistrantcountry.selectByVisibleText(newregistrant_country_data);

		Select newregistrantstate = new Select(new_registrant_state);
		newregistrantstate.selectByVisibleText(newregistrant_state_data);

		new_registrant_zipcode.sendKeys(newregistrant_zipcode_data);
		log.info("new registrants: " + new_registrant_email + ", details added");
	}

	@FindBy(xpath = "//a[span='TBD Orders']")
	private WebElement linktbdorders;

	public void clickTbdOrdersLink() throws Exception {
		linktbdorders.click();
		log.info("TBD ORDERS link clicked");
	}

	@FindBy(xpath = "//span[text()='ID' and @class='data-grid-cell-content']//parent::th")
	private WebElement sortid;

	@FindBy(xpath = "//tr[@data-repeat-index='0']")
	private WebElement selectfirstrowrecord;

	@FindBy(xpath = "//span[@class='123' and text()='Orders']")
	private WebElement sectionheaderorders;

	@FindBy(xpath = "//a[@class='orderLink']")
	private WebElement attachedorderid;

	@FindBy(xpath = "//button[text()='Clear all']")
	private WebElement link_clearall_tbdorder;

	public void clearAllTBDOrderFilterLink() {
		try // add this try-catch so tht if elemtn not displayed then ELSE condition will
			// work
		{

			if (link_clearallorder.isDisplayed()) {
				link_clearallorder.click();
				log.info("Order-CLEARALL link visible and clikced");
				Thread.sleep(8000);
			}
		} catch (Exception e)

		{
			log.info("clearall link not visible");
		}

	}

	@FindBy(xpath = "//div[@class='data-grid-filters-action-wrap']//descendant::button[text()='Filters']")
	private WebElement btn_tbdorder_filter;

	public void clickOnTBDOrderFiltersBtn() {
		
		log.info("i m inside clickOnTBDOrderFiltersBtn method ");
		btn_tbdorder_filter.click();
		log.info("TBD Order FILTER button clicked");
	}

	@FindBy(xpath = "//input[@name='invoice_id']")
	private WebElement invoiceid_filter;

	public void setInvoiceIdTBDOrderFilter() throws Exception {
		log.info("i am inside setInvoiceIdTBDOrderFilter method");
		invoiceid_filter.clear();
		log.info("invoice id to be searched is"+invoiceid);
		invoiceid_filter.sendKeys(invoiceid);
		log.info("Filtering TBD order on the basis of Invoice id");
	}

	@FindBy(xpath = "//button[@class='action-secondary']//descendant::span[text()='Apply Filters']")
	private WebElement btn_tbdorder_apply_filter;

	public void clickTBDOrderApplyfilterBtn() {
		btn_tbdorder_apply_filter.click();
		log.info("APPLY FILTER button of TBD Order is clicked");
	}

	public void openFilteredTBDOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-50)", "");
		selectfirstrowrecord.click();
		log.info("Opened TBD order filtered record");
	}

	public void sortandOpenTbdOrder() throws Exception {
		sortid.click();
		Thread.sleep(1000);
		sortid.click();
		Thread.sleep(1000);
		selectfirstrowrecord.click();
		Thread.sleep(6000);

	}

	
	

	public void verifyOrderinGrid() throws Exception {
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", sectionheaderorders);
		log.info("i m inside verifyOrderinGrid method");
		//Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1200)", "");
		log.info("scrolled down");
		Thread.sleep(2000);
		
		String attachedorderiddata;
		//sectionheaderorders.click();//this is associated with engp-981
		//log.info("order twisty opened");
		//Thread.sleep(5000);
		attachedorderiddata = "ORDER #" + attachedorderid.getText();
		log.info("Order id visible in grid is= " + attachedorderiddata);
		if (attachedorderiddata.equals(orderid)) {
			log.info("correct order id is visible");
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshot,
						new File("target/screenshots/tbdorder-correctactualorder-" + orderid + ".jpg"));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} else {
			log.info("incorect order id is visible");
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshot,
						new File("target/screenshots/tbdorder-incorrectactualorder-" + orderid + ".jpg"));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@FindBy(xpath = "//a[@class='orderLink']")
	private WebElement orderid_link;

	public void openActualOrderDetailspage() {
		oldtab = driver.getWindowHandle();
		orderid_link.click();

		ArrayList<?> newtab = new ArrayList<Object>(driver.getWindowHandles());
		newtab.remove(oldtab);
		// change focus to new tab
		driver.switchTo().window((String) newtab.get(0));

		/// to switch back to main window follow below steps
		// driver.switchTo().window(oldtab);
	}

	public void paylaterPaymentoptionActualOrder() throws Exception {
		log.info("I am inside paylaterPaymentoptionActualOrder method");
		//wait.until(ExpectedConditions.elementToBeClickable(radio_paylater_payment)).click();
		//log.info("clicked PAYLATER radio button");
		
		//Thread.sleep(2000);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1); // select tomorows date
		String dt = sdf.format(c.getTime());

		add_due_date.sendKeys(dt);
		add_due_date.sendKeys(Keys.TAB);// this will click outside the due date field
		Thread.sleep(3000);
		log.info("selected PAY LATER method and added tomorows date as DUE DATE ");
		//chkbox_invoicerequest.click();
		//Thread.sleep(2000);
	}

	@FindBy(xpath = "//div[text()='Registrant - 2 of 2']//parent::div//child::input[@class='admin__control-text contactemail required-entry']")
	private WebElement add_existing_registrant2_emailid;

	@FindBy(xpath = "//div[text()='Registrant - 2 of 2']//parent::div//child::input[@class='admin__control-text contactemail required-entry']//parent::div//descendant::li")
	private WebElement select_existing_registrant2id;

	public void addExistingRegistrant2forActualOrder() throws Exception {
		String existingregistrant_emailid_data = PropertyUtils.readProperty("existingRegistrantemailid");
		add_existing_registrant2_emailid.sendKeys(existingregistrant_emailid_data);
		WebDriverWait wait = new WebDriverWait(
		        driver,
		        Duration.ofSeconds(20)
		);
		wait.until(ExpectedConditions.elementToBeClickable(select_existing_registrant2id))
				.click();
		log.info("Existing Registrants " + existingregistrant_emailid_data + ", details added");
	}

	public void backToOriginalWindow() {
		driver.switchTo().window(oldtab);
	}

	@FindBy(xpath = "//input[@id='available_quantity']")
	private WebElement availableqty;

	public void refreshTBDOrder() throws Exception {
		driver.navigate().refresh();

	}

	public void checkQtyOfeditActualOrderAndtakeScreenshot() throws Exception {

		js.executeScript("scroll(0, 200);");
		log.info("Available qty: " + availableqty.getAttribute("value"));
		if ((availableqty.getAttribute("value")).equals("8"))
			log.info("Available qty is corect");
		else
			log.info("availabel qty is incorrect");

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/tbdorderavailableqtyafterorderupdate.jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public void checkQtyOfCancelActualOrderAndtakeScreenshot() throws Exception {

		js.executeScript("scroll(0, 200);");
		log.info("Available qty: " + availableqty.getAttribute("value"));
		if ((availableqty.getAttribute("value")).equals("10"))
			log.info("Available qty is corect");
		else
			log.info("availabel qty is incorrect");

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/tbdorderavailableqtyafterorderupdate.jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public void checkQtyOfRefundActualOrderAndtakeScreenshot() throws Exception {

		js.executeScript("scroll(0, 200);");
		Thread.sleep(1000);
		log.info("Available qty: " + availableqty.getAttribute("value"));
		if ((availableqty.getAttribute("value")).equals("10"))
			log.info("Available qty is corect");
		else
			log.info("availabel qty is incorrect");

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/tbdorderavailableqtyafterorderupdate.jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
