package com.pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
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

public class event_page extends BaseClass {

	public static String ename;

	public String eventstartdate;
	public String eventenddate;
	public String coursestartdate;
	public String coursestartdate1;
	public String coursestartdate2;
	public String courseenddate;
	public String courseenddate1;
	public String courseenddate2;
	public String coursesalestartdate;
	public String coursesalestartdate1;
	public String coursesalestartdate2;
	public String coursesaleenddate;
	public String coursesaleenddate1;
	public String coursesaleenddate2;

	// Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
	private WebDriverWait wait;

	// constructor
	public event_page(WebDriver driver) {

	    this.driver = driver;

	    this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));

	    PageFactory.initElements(driver, this);
	}
	// javascript for scrolling
	JavascriptExecutor js = (JavascriptExecutor) driver;

	// #main links
	// Events menu link
	@FindBy(xpath = "//li[@id='menu-pragmatic-customadminnewpage-events' and @role='menu-item']//a")
	private WebElement link_events_menu;

	public void clickOnEventMenu() {
		link_events_menu.click();
		log.info("event menu clicked");
	}

	// All Events link
	@FindBy(xpath = "//li[@class='item-events-manage    level-1']")
	private WebElement link_all_events;

	// All Events page header
	@FindBy(xpath = "//h1[text()='All Events']")
	private WebElement header_all_events;

	// Admin Archive link
	@FindBy(id = "menu-pragmatic-customadminnewpage-archival")
	private WebElement link_admin_archive;

	// Event Archive link
	@FindBy(id = "menu-pragmatic-customadminnewpage-event-archival")
	private WebElement link_event_archive;

	// create new event link
	@FindBy(xpath = "//li[@class='item-events-create    level-1']")
	private WebElement link_create_event;

	// CReate new event page header
	@FindBy(xpath = "//h1[text()='Create New Event']")
	private WebElement header_create_new_event;

	/*
	 * public void clickOnCreateEvent() throws Exception {
	 * link_create_event.click(); Thread.sleep(2000); if
	 * (link_create_event.isDisplayed()) log.info("page header displaying is: " +
	 * header_create_new_event.getText()); else log.info("page header is missing");
	 * }
	 */
	public void clickOnCreateEvent() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(link_create_event)).click();
		//link_create_event.click();
		log.info("CREATE EVENT link selected");
		Thread.sleep(4000);
		
		if (link_create_event.isDisplayed())
			log.info("page title displaying is: " + driver.getTitle() );
		else
			log.info("page title is missing");
	}
	// #creating events
	@FindBy(xpath = "//div[@class='admin__field-control admin__control-fields']//descendant::select[@id='product[tbd_status]']")
	private WebElement dropdown_hide_from_listing_page;

	public void setNoHideFromListing() {
		Select hidefromlisting = new Select(dropdown_hide_from_listing_page);
		hidefromlisting.selectByValue("200");
		log.info("Hide from listing=NO is set");
	}

	public void setYesHideFromListing() {
		Select hidefromlisting = new Select(dropdown_hide_from_listing_page);
		hidefromlisting.selectByValue("201");
		log.info("Hide from listing=YES is set");
	}

	@FindBy(xpath = "//select[@id='event_type']")
	private WebElement dropdown_event_type;

	public void setPublicEventType() {
		Select eventtype = new Select(dropdown_event_type);
		eventtype.selectByVisibleText("Public");
		log.info("Public event type is set");
	}

	public void setOnsiteEventType() {
		Select eventtype = new Select(dropdown_event_type);
		eventtype.selectByVisibleText("Onsite");
		log.info("Onsite event type is set");
	}

	@FindBy(xpath = "//select[@id='product[tbd_event]']")
	private WebElement dropdown_tbd_event;

	public void setYesTBDEvent() {
		Select tbdeventtype = new Select(dropdown_tbd_event);
		tbdeventtype.selectByVisibleText("Yes");
		log.info("TBD event=YES is set");
	}

	public void setNoTBDEvent() {
		Select tbdeventtype = new Select(dropdown_tbd_event);
		tbdeventtype.selectByVisibleText("No");
		log.info("TBD event=NO is set");
	}

	@FindBy(xpath = "//select[@id='product[delivery_type]']")
	private WebElement dropdown_delivery_type;

	public void setInpersonDeliveryType() {
		Select deliverytype = new Select(dropdown_delivery_type);
		deliverytype.selectByVisibleText("In Person");
		log.info("Delivery type=In person is set");
	}

	public void setOnlineDeliveryType() {
		Select deliverytype = new Select(dropdown_delivery_type);
		deliverytype.selectByVisibleText("Online");
		log.info("Delivery type=Online is set");
	}

	@FindBy(xpath = "//select[@id='product[event_status]']")
	private WebElement dropdown_event_status;

	public void setActiveEventStatus() {
		Select eventstatus = new Select(dropdown_event_status);
		eventstatus.selectByVisibleText("Active");
		log.info("Event status=ACTIVE is set");
	}

	public void setDeliveredEventStatus() {
		Select eventstatus = new Select(dropdown_event_status);
		eventstatus.selectByVisibleText("Delivered");
		log.info("Event status=DELIVERED is set");
	}

	public void setCancelledEventStatus() {
		Select eventstatus = new Select(dropdown_event_status);
		eventstatus.selectByVisibleText("Cancelled");
		log.info("Event status=CANCELLED is set");
	}

	@FindBy(xpath = "//select[@id='product[test_platform]']")
	private WebElement dropdown_test_platform;

	public void setPMICTestPlatform() {
		Select testplatform = new Select(dropdown_test_platform);
		testplatform.selectByVisibleText("PMIC");
		log.info("Test Platform=PMIC is set");
	}

	public void setTESTTestPlatform() {
		Select testplatform = new Select(dropdown_test_platform);
		testplatform.selectByVisibleText("Test.com");
		log.info("Test Platform=TEST.COM is set");
	}

	@FindBy(xpath = "//select[@id='product[pi_account]']")
	private WebElement dropdown_piaccount;

	public void setPMPIAccount() {
		Select piaccount = new Select(dropdown_piaccount);
		piaccount.selectByVisibleText("PM");
		log.info("PI account=PM is set");
	}

	public void setPDPIAccount() {
		Select piaccount = new Select(dropdown_piaccount);
		piaccount.selectByVisibleText("PD");
		log.info("PI account=PD is set");
	}

	public void setPLNPIAccount() {
		Select piaccount = new Select(dropdown_piaccount);
		piaccount.selectByVisibleText("PLN");
		log.info("PI account=PLN is set");
	}

	public void setDESPIAccount() {
		Select piaccount = new Select(dropdown_piaccount);
		piaccount.selectByVisibleText("DES");
		log.info("PI account=DES is set");
	}

	@FindBy(xpath = "//input[@id='product[name]']")
	private WebElement event_name;

	public void generateEventName(String pre_eventstring) {
		System.out.println("----------Event_Name---------");

		ename = pre_eventstring + RandomStringUtils.randomNumeric(4);
		eventname=ename;
		log.info("Generated event name is: " + ename);
	}

	public void setEventName() {
		event_name.sendKeys(ename);
		log.info(ename);
	}

	@FindBy(xpath = "//textarea[@id='event_name_external']")
	private WebElement event_title;

	public void setEventTitle() {
		event_title.sendKeys(ename + "-title");
		log.info("set event title is: " + ename + "-title");
	}

	@FindBy(xpath = "//input[@id='product_event_start_date']//following-sibling::button")
	private WebElement icon_start_date;

	@FindBy(xpath = "//div[@class='ui-datepicker-title']//following::select[@data-handler='selectYear']")
	private WebElement calendar_select_year;

	@FindBy(xpath = "//div[@class='ui-datepicker-title']//following::select[@data-handler='selectYear']//preceding-sibling::select")
	private WebElement calendar_select_month;

	@FindBy(xpath = "//button[text()='Done']")
	private WebElement btn_calendar_done;

	@FindBy(xpath = "//div[@class='ui_tpicker_hour_slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all']//a")
	private WebElement slide_hour;

	@FindBy(xpath = "//div[@class='ui_tpicker_minute_slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all']//a")
	private WebElement slide_minute;

	@FindBy(xpath = "//input[@id='product_event_start_date']")
	private WebElement project_start_date;

	@FindBy(xpath = "//input[@id='product_event_end_date']")
	private WebElement project_end_date;

	public void setEventStartDateTime() throws Exception {// int days) {
		String event_start_days_from_current_date = PropertyUtils.readProperty("event_startdays_fromcurrent_date");
		int event_days_in_integer = Integer.valueOf(event_start_days_from_current_date);// check this
		Calendar cal = Calendar.getInstance();
		System.out.println("current date is: " + sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, event_days_in_integer);// days);
		String projectdateAfter = sdf.format(cal.getTime());
		eventstartdate = projectdateAfter + " 12:00 AM";
		System.out.println("start date after adding required days= " + eventstartdate);
		project_start_date.sendKeys(eventstartdate);
		log.info("set event start date is: " + eventstartdate);

	}

	public void setEventEndDateTime() throws Exception {// int event_end_days) throws Exception {

		String event_days_enddate_fromevent_startdate_string = PropertyUtils
				.readProperty("event_days_enddate_fromevent_startdate");
		int event_days_enddate_fromevent_startdate_int = Integer.valueOf(event_days_enddate_fromevent_startdate_string);// check
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(eventstartdate));

		cal.add(Calendar.DAY_OF_MONTH, event_days_enddate_fromevent_startdate_int);// event_end_days);
		String edate = sdf.format(cal.getTime());
		eventenddate = edate + " 12:00 AM";
		project_end_date.sendKeys(eventenddate);
		log.info("set event end date is: " + eventenddate);
	}

	// Below are elements for inperson event

	@FindBy(xpath = "//input[@id='product[loc_venue_name]']")
	private WebElement event_venue_name;

	@FindBy(xpath = "//input[@id='product[loc_address1]']")
	private WebElement event_venue_address1;

	@FindBy(xpath = "//input[@id='product[loc_city]']")
	private WebElement event_venue_city;

	@FindBy(xpath = "//input[@id='product[loc_zip]']")
	private WebElement event_venue_zipcode;

	@FindBy(xpath = "//select[@id='country']")
	private WebElement dropdown_event_venue_country;

	@FindBy(xpath = "//select[@id='state_reg']")
	private WebElement dropdown_event_venue_state;

	@FindBy(xpath = "//input[@id='product[venue_phone]']")
	private WebElement event_venue_phone;

	public void setInpersonVenue() {
		event_venue_name.sendKeys("JW Marriott Hotel New Delhi Aerocity ");
	}

	public void setInpersonVenueAddress() {
		event_venue_address1.sendKeys("Asset Area 4 - Hospitality District Delhi, Aerocity, New Delhi");
	}

	public void setInpersonVenueCity() {
		event_venue_city.sendKeys("Delhi");
	}

	public void setInpersonVenueZipcode() {
		event_venue_zipcode.sendKeys("110037");
	}

	public void setInpersonVenueCountry() {

		Select eventcountry = new Select(dropdown_event_venue_country);
		eventcountry.selectByVisibleText("India");
		log.info("Country set is: " + eventcountry);

	}

	public void setInpersonVenueState() {

		Select eventstate = new Select(dropdown_event_venue_state);
		eventstate.selectByVisibleText("Delhi");// maharashtra
		log.info("state set is: " + eventstate);

	}

	public void setInpersonVenuePhone() {

		event_venue_phone.sendKeys("020 2706 0606");

	}

//Account for Onsite event

	@FindBy(xpath = "//input[@id='accountSearch']")
	private WebElement event_account;

	public void setOnsiteAccount() {
		log.info("I am inside setOnsiteAccount method");
		event_account.sendKeys("Mediawide");
		log.info("Onsite Account is added");
	}

	@FindBy(xpath = "//span[text()='Products & Pricing']")
	private WebElement section_products_and_pricing;

	@FindBy(xpath = "//select[@id='ddmastercourses']")
	private WebElement add_product;

	@FindBy(xpath = "//button[@id='btnaddmastercourse']")
	private WebElement btn_add_product;

	public void clickProductAndPricingSection() throws InterruptedException {
		log.info("I am inside clickProductAndPricingSection method");
				
		section_products_and_pricing.click();
		log.info("clicked Products & Pricing section");
		Thread.sleep(2000);
	}

	public void addPMProducts() throws Exception {
		String setPMcourse_data = PropertyUtils.readProperty("setBUILDPMcourse");
		coursename=setPMcourse_data;
		Select product = new Select(add_product);
		product.selectByVisibleText(setPMcourse_data);
		log.info("selected course: "+setPMcourse_data);
		btn_add_product.click();
		
	}
	
	public void addPDProducts() throws Exception {
		String setPDcourse_data = PropertyUtils.readProperty("setPDcourse");
		coursename=setPDcourse_data;
		Select product = new Select(add_product);
		product.selectByVisibleText(setPDcourse_data);
		log.info("selected course: "+setPDcourse_data);
		btn_add_product.click();
	}

	@FindBy(xpath = "//input[@id='grid_course_date_68_1']")
	private WebElement pmcourse_start_date;
	
	@FindBy(xpath = "//input[@id='grid_course_end_date_68_1']")
	private WebElement pmcourse_end_date;
	
	
	@FindBy(xpath = "//input[@id='grid_course_date_7471_1']")
	private WebElement pdcourse_start_date;
	
	@FindBy(xpath = "//input[@id='grid_course_end_date_7471_1']")
	private WebElement pdcourse_end_date;
	
	

	public void setCourseStartDateTime() throws Exception {
		String course_start_days_from_current_date_string = PropertyUtils
				.readProperty("course_startdays_fromcurrent_date");
		int course_start_days_from_now_int = Integer.valueOf(course_start_days_from_current_date_string);
		Calendar cal = Calendar.getInstance();
		System.out.println("current date is: " + sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, course_start_days_from_now_int);// days);
		String coursedateAfter = sdf.format(cal.getTime());
		coursestartdate = coursedateAfter + " 12:00 AM";
		log.info("calculated start date after adding required days (" + course_start_days_from_now_int + ")= "
				+ coursestartdate);
		pmcourse_start_date.sendKeys(coursestartdate);
		log.info("set course start date is: " + coursestartdate);
	}
	
	public void setPDCourseStartDateTime() throws Exception {
		String course_start_days_from_current_date_string = PropertyUtils
				.readProperty("course_startdays_fromcurrent_date");
		int course_start_days_from_now_int = Integer.valueOf(course_start_days_from_current_date_string);
		Calendar cal = Calendar.getInstance();
		System.out.println("current date is: " + sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, course_start_days_from_now_int);// days);
		String coursedateAfter = sdf.format(cal.getTime());
		coursestartdate = coursedateAfter + " 12:00 AM";
		log.info("calculated start date after adding required days (" + course_start_days_from_now_int + ")= "
				+ coursestartdate);
		pdcourse_start_date.sendKeys(coursestartdate);
		log.info("set course start date is: " + coursestartdate);
	}

	public void setPMCourseEndDateTime() throws Exception {// int course_enddays) throws Exception {
		String course_end_days_string = PropertyUtils.readProperty("course_days_enddate_fromcourse_startdate");
		int course_end_days_int = Integer.valueOf(course_end_days_string);
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(coursestartdate));
		cal.add(Calendar.DAY_OF_MONTH, course_end_days_int);// course_enddays);
		String edate = sdf.format(cal.getTime());
		courseenddate = edate + " 12:00 AM";
		pmcourse_end_date.sendKeys(courseenddate);
		log.info("set course end date is: " + courseenddate);
	}
	
	public void setPDCourseEndDateTime() throws Exception {// int course_enddays) throws Exception {
		String course_end_days_string = PropertyUtils.readProperty("course_days_enddate_fromcourse_startdate");
		int course_end_days_int = Integer.valueOf(course_end_days_string);
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(coursestartdate));
		cal.add(Calendar.DAY_OF_MONTH, course_end_days_int);// course_enddays);
		String edate = sdf.format(cal.getTime());
		courseenddate = edate + " 12:00 AM";
		pdcourse_end_date.sendKeys(courseenddate);
		log.info("set course end date is: " + courseenddate);
	}

	@FindBy(xpath = "//input[@id='grid_sale_start_date_68_1']")
	private WebElement pmcourse_sale_start_date;

	@FindBy(xpath = "//input[@id='grid_sale_end_date_68_1']")
	private WebElement pmcourse_sale_end_date;
	
	@FindBy(xpath = "//input[@id='grid_sale_start_date_7471_1']")
	private WebElement pdcourse_sale_start_date;

	@FindBy(xpath = "//input[@id='grid_sale_end_date_7471_1']")
	private WebElement pdcourse_sale_end_date;

	public void setPMCourseSaleStartDate() {
		Calendar cal = Calendar.getInstance();
		String salestartdate = sdf.format(cal.getTime());
		coursesalestartdate = salestartdate + " 12:00 AM";
		pmcourse_sale_start_date.sendKeys(coursesalestartdate);
		log.info("set course sale start date is: " + coursesalestartdate);
	}

	public void setPMCourseSaleEndDate() {
		pmcourse_sale_end_date.sendKeys(coursestartdate);
		log.info("set course sale end date is: " + coursestartdate);
	}
	
	public void setPDCourseSaleStartDate() {
		Calendar cal = Calendar.getInstance();
		String salestartdate = sdf.format(cal.getTime());
		coursesalestartdate = salestartdate + " 12:00 AM";
		pdcourse_sale_start_date.sendKeys(coursesalestartdate);
		log.info("set course sale start date is: " + coursesalestartdate);
	}

	public void setPDCourseSaleEndDate() {
		pdcourse_sale_end_date.sendKeys(coursestartdate);
		log.info("set course sale end date is: " + coursestartdate);
	}

	@FindBy(xpath = "//input[@id='grid[maxqty_68_1]']")
	private WebElement pmcourse_max_qty;
	
	@FindBy(xpath = "//input[@id='grid[maxqty_7471_1]']")
	private WebElement pdcourse_max_qty;

	public void setPMCourseMaxQty() {
		pmcourse_max_qty.sendKeys("9999");
	}
	
	public void setPDCourseMaxQty() {
		pdcourse_max_qty.sendKeys("9999");
	}

	/// in case of 2 p;roducts below methods are written

	// ,mthod for adding 2 products, PRICE and MARKET
	public void add2PMProducts() throws InterruptedException {
		Select product1 = new Select(add_product);
		product1.selectByVisibleText("Price");
		btn_add_product.click();
		Thread.sleep(2000);
		Select product2 = new Select(add_product);
		product2.selectByVisibleText("Market");
		btn_add_product.click();
	}
	
	

	@FindBy(xpath = "//input[@id='grid_course_date_161_1']")
	private WebElement course1_start_date;

	@FindBy(xpath = "//input[@id='grid_course_end_date_161_1']")
	private WebElement course1_end_date;

	// method to set PRICE course start date
	public void set1CourseStartDateTime(int days) {
		Calendar cal = Calendar.getInstance();
		System.out.println("current date is: " + sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, days);
		String coursedateAfter = sdf.format(cal.getTime());
		coursestartdate1 = coursedateAfter + " 12:00 AM";
		log.info("calculated start date after adding required days (" + days + ")= " + coursestartdate1);
		course1_start_date.sendKeys(coursestartdate1);
		log.info("set course start date is: " + coursestartdate1);
	}

	// method to set PRICE course END date
	public void set1CourseEndDateTime(int course_enddays) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(coursestartdate1));
		cal.add(Calendar.DAY_OF_MONTH, course_enddays);
		String edate = sdf.format(cal.getTime());
		courseenddate1 = edate + " 12:00 AM";
		course1_end_date.sendKeys(courseenddate1);
		log.info("set course end date is: " + courseenddate1);
	}

	@FindBy(xpath = "//input[@id='grid_sale_start_date_161_1']")
	private WebElement course1_sale_start_date;

	@FindBy(xpath = "//input[@id='grid_sale_end_date_161_1']")
	private WebElement course1_sale_end_date;

	// method to set PRICE course SALE start date
	public void set1CourseSaleStartDate() {
		Calendar cal = Calendar.getInstance();
		String salestartdate1 = sdf.format(cal.getTime());
		coursesalestartdate1 = salestartdate1 + " 12:00 AM";
		course1_sale_start_date.sendKeys(coursesalestartdate1);
		log.info("set course sale start date is: " + coursesalestartdate1);
	}

	// method to set PRICE course SALE end date
	public void set1CourseSaleEndDate() {
		course1_sale_end_date.sendKeys(coursestartdate1);
		log.info("set course sale end date is: " + coursestartdate1);
	}

	@FindBy(xpath = "//input[@id='grid[maxqty_161_1]']")
	private WebElement course1_max_qty;

	public void set1CourseMaxQty() {
		course1_max_qty.sendKeys("9999");
	}

	@FindBy(xpath = "//input[@id='grid_course_date_69_2']")
	private WebElement course2_start_date;

	@FindBy(xpath = "//input[@id='grid_course_end_date_69_2']")
	private WebElement course2_end_date;

//method to set MARKET course start date
	public void set2CourseStartDateTime(int days) {
		Calendar cal = Calendar.getInstance();
		System.out.println("current date is: " + sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, days);
		String coursedateAfter = sdf.format(cal.getTime());
		coursestartdate2 = coursedateAfter + " 12:00 AM";
		log.info("calculated start date after adding required days (" + days + ")= " + coursestartdate2);
		course2_start_date.sendKeys(coursestartdate2);
		log.info("set course start date is: " + coursestartdate2);
	}

//method to set MARKET course end date
	public void set2CourseEndDateTime(int course_enddays) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(coursestartdate2));
		cal.add(Calendar.DAY_OF_MONTH, course_enddays);
		String edate = sdf.format(cal.getTime());
		courseenddate2 = edate + " 12:00 AM";
		course2_end_date.sendKeys(courseenddate2);
		log.info("set course end date is: " + courseenddate2);
	}

	@FindBy(xpath = "//input[@id='grid_sale_start_date_69_2']")
	private WebElement course2_sale_start_date;

	@FindBy(xpath = "//input[@id='grid_sale_end_date_69_2']")
	private WebElement course2_sale_end_date;

//method to set MARKET course SALE start date
	public void set2CourseSaleStartDate() {
		Calendar cal = Calendar.getInstance();
		String salestartdate2 = sdf.format(cal.getTime());
		coursesalestartdate2 = salestartdate2 + " 12:00 AM";
		course2_sale_start_date.sendKeys(coursesalestartdate2);
		log.info("set course sale start date is: " + coursesalestartdate2);
	}

//method to set MARKET course SALE end date
	public void set2CourseSaleEndDate() {
		course2_sale_end_date.sendKeys(coursestartdate2);
		log.info("set course sale end date is: " + coursestartdate2);
	}

	@FindBy(xpath = "//input[@id='grid[maxqty_69_2]']")
	private WebElement course2_max_qty;

	public void set2CourseMaxQty() {
		course2_max_qty.sendKeys("9999");
	}

	@FindBy(xpath = "//span[@id='span_templates']")
	private WebElement section_templates;

	@FindBy(xpath = "//select[@id='product[event_template]']")
	private WebElement dropdown_template;

	public void setPMTemplate() throws Exception {
		log.info("i am within the select template method");
		section_templates.click();
		log.info("TEMPLATE section opened");
		Thread.sleep(2000);

		Select template = new Select(dropdown_template);
		template.selectByVisibleText("Market Online");
		log.info("selected template is: " + template);

	}
	
	
	public void setPDTemplate() throws Exception {
		log.info("i am within the select template method");
		section_templates.click();
		log.info("TEMPLATE section opened");
		Thread.sleep(2000);

		Select template = new Select(dropdown_template);
		template.selectByVisibleText("Data Science for Business Leaders Online");
		log.info("selected template is: " + template);

	}

	@FindBy(xpath = "//button[@id='btnsave']")
	private WebElement btn_save_event;

	public void saveEvent() {
		btn_save_event.click();
		log.info("event saved");
	}

	@FindBy(xpath = "//button[@id='btnpublish']")
	private WebElement btn_publish_event;

	public void publishEvent() throws Exception {
		
		log.info("i am within the publish event method");
		//Thread.sleep(10000);
		//  wait.until(ExpectedConditions.visibilityOf(btn_publish_event)).click();
		btn_publish_event.click();
		log.info("EVENT PUBLISH BUTTON clicked");
		  Thread.sleep(20000);
		//btn_publish_event.click();
		
		//Thread.sleep(10000);

		js.executeScript("scroll(0, 300);");

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/eventcreated-" + ename + ".jpg"));
			log.info("screenshot captured");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}


	@FindBy(xpath = "//span[text()='Daily Schedule']")
	private WebElement section_dailyschedule;

	@FindBy(xpath = "//button[@id='btn_dailyschedule']")
	private WebElement btn_dailyschedule;

	@FindBy(xpath = "//span[@id='span_sodetails']")
	private WebElement section_so_details;

	public void clickSODetailsSection() {
		section_so_details.click();
		log.info("SO details section clicked");
	}

	@FindBy(id = "product[so_number]")
	private WebElement so_number;

	@FindBy(id = "product_so_signed_date")
	private WebElement signed_dates;

	public void setSODetails() {
		so_number.sendKeys("1234");
		signed_dates.sendKeys(eventenddate);
	}

	@FindBy(xpath = "//span[@id='span_ordersandattendees']")
	private WebElement section_orders_and_attendees;

	public void clickOrdersAndAttendees() {
		section_orders_and_attendees.click();
		log.info("section_orders_and_attendees section clicked");
	}

	@FindBy(xpath = "//button[@id='btn_attendees_model']")
	private WebElement btn_attendees;

	public void clickAttendeesbutton() {
		btn_attendees.click();
		log.info("attendees button clicked");
	}

	@FindBy(id = "attendeesemail")
	private WebElement attendee_email;

	@FindBy(id = "attendeesfname")
	private WebElement attendee_firstname;

	@FindBy(id = "attendeeslname")
	private WebElement attendee_lastname;

	@FindBy(id = "attendeesTitle")
	private WebElement attendee_title;

	@FindBy(id = "attendeesCompany")
	private WebElement attendee_company;

	@FindBy(id = "btn_add_attendees")
	private WebElement btn_add_attendee;

	@FindBy(xpath = "//aside[@class='modal-slide _inner-scroll _show']//child::button[@data-role='action']//span[text()='Close']")
	private WebElement btn_close;

	public void addattendee() throws Exception {
		log.info("new attendee info will get added now");
		attendee_email.sendKeys("test1@" + ename + ".com");
		Thread.sleep(1000);
		attendee_firstname.sendKeys("test1");
		Thread.sleep(1000);
		attendee_lastname.sendKeys(ename);
		Thread.sleep(1000);
		attendee_title.sendKeys("QC");
		Thread.sleep(1000);
		attendee_company.sendKeys("Flipick");
		Thread.sleep(1000);
		btn_add_attendee.click();
	
		log.info("new attendee info is added");
		Thread.sleep(7000);
		btn_close.click();
		log.info("Attendee list page-CLOSE button clicked");
	}

}
