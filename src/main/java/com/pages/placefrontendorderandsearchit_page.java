package com.pages;

import java.io.File;
import java.time.Duration;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.server.handler.GetTitle;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.base.BaseClass;
import com.utility.PropertyUtils;

public class placefrontendorderandsearchit_page extends BaseClass {

	public static String ename;

	public String eventstartdate;
	public String eventenddate;
	public String coursestartdate;
	public String courseenddate;
	public String coursesalestartdate;
	public String coursesaleenddate;
	//public String eventTemplate_data;
	
	//public String orderid;
	public String oldtab;
	
	

	
	public String existingeventnameforupdatingstatus_data;

	String itemqty_data;
	// front end order registrants below
	public String cartregistrant1_email;
	public String cartregistrant2_email;
	public String cartregistrant3_email;

	String cartregistrant_fnamel_data;
	String cartregistrant_lnamel_data;
	

	// Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

	
	private WebDriverWait wait;
	// constructor
	public placefrontendorderandsearchit_page(WebDriver driver) {
		
		this.driver = driver;
       // wait = new WebDriverWait(driver, 50, 50);
		WebDriverWait wait = new WebDriverWait(
		        driver,
		        Duration.ofSeconds(50)
		);
		//wait = new WebDriverWait(driver, Duration.ofSeconds(50), Duration.ofMillis(50));
        PageFactory.initElements(driver,this);
	}

	// javascript for scrolling
	JavascriptExecutor js = (JavascriptExecutor) driver;

	// #main links
	// Events menu link
	@FindBy(xpath = "//li[@id='menu-pragmatic-customadminnewpage-events' and @role='menu-item']//a")
	private WebElement link_events_menu;

	public void clickOnEventMenu() {
		link_events_menu.click();
		log.info("EVENT menu clicked");
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
	@FindBy(xpath = "//h1[@class='page-title']")
	private WebElement header_create_new_event;

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
		hidefromlisting.selectByVisibleText("No");
		log.info("Hide from listing=NO is set");
	}

	public void setYesHideFromListing() {
		Select hidefromlisting = new Select(dropdown_hide_from_listing_page);
		hidefromlisting.selectByVisibleText("Yes");
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
//------------------------------
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
		event_venue_name.sendKeys("Radisson Blu Hotel ");
	}

	public void setInpersonVenueAddress() {
		event_venue_address1.sendKeys("Nagar Bypass, Road, Kharadi");
	}

	public void setInpersonVenueCity() {
		event_venue_city.sendKeys("Pune");
	}

	public void setInpersonVenueZipcode() {
		event_venue_zipcode.sendKeys("411014");
	}

	public void setInpersonVenueCountry() {

		Select eventcountry = new Select(dropdown_event_venue_country);
		eventcountry.selectByVisibleText("India");
		log.info("Country set is: " + eventcountry);

	}

	public void setInpersonVenueState() {

		Select eventstate = new Select(dropdown_event_venue_state);
		eventstate.selectByVisibleText("Maharashtra");// maharashtra
		log.info("state set is: " + eventstate);

	}

	public void setInpersonVenuePhone() {

		event_venue_phone.sendKeys("020 2706 0606");

	}

//Account for Onsite event

	@FindBy(xpath = "//input[@id='accountSearch']")
	private WebElement event_account;

	public void setOnsiteAccount() {
		event_account.sendKeys("Mediawide ");
	}

	@FindBy(xpath = "//span[text()='Products & Pricing']")
	private WebElement section_products_and_pricing;

	@FindBy(xpath = "//select[@id='ddmastercourses']")
	private WebElement add_pm_product;

	@FindBy(xpath = "//button[@id='btnaddmastercourse']")
	private WebElement btn_add_product;

	public void clickProductAndPricingSection() {
		section_products_and_pricing.click();
	}

	public void addPMProducts() throws Exception {
		String setPMcourse_data = PropertyUtils.readProperty("setPMcourse");
		coursename=setPMcourse_data;
		Select product = new Select(add_pm_product);
		product.selectByVisibleText(setPMcourse_data);
		log.info("selected course: "+setPMcourse_data);
		btn_add_product.click();
	}

	@FindBy(xpath = "//input[@id='grid_course_date_68_1']")
	private WebElement course_start_date;
	
	@FindBy(xpath = "//input[@id='grid_course_end_date_68_1']")
	private WebElement course_end_date;

	

	public void setCourseStartDateTime() throws Exception {// int days) {
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
		course_start_date.sendKeys(coursestartdate);
		log.info("set course start date is: " + coursestartdate);
	}

	public void setCourseEndDateTime() throws Exception {// int course_enddays) throws Exception {
		String course_end_days_string = PropertyUtils.readProperty("course_days_enddate_fromcourse_startdate");
		int course_end_days_int = Integer.valueOf(course_end_days_string);
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(coursestartdate));
		cal.add(Calendar.DAY_OF_MONTH, course_end_days_int);// course_enddays);
		String edate = sdf.format(cal.getTime());
		courseenddate = edate + " 12:00 AM";
		course_end_date.sendKeys(courseenddate);
		log.info("set course end date is: " + courseenddate);
	}

	@FindBy(xpath = "//input[@id='grid_sale_start_date_68_1']")
	private WebElement course_sale_start_date;

	@FindBy(xpath = "//input[@id='grid_sale_end_date_68_1']")
	private WebElement course_sale_end_date;

	public void setCourseSaleStartDate() {
		Calendar cal = Calendar.getInstance();
		String salestartdate = sdf.format(cal.getTime());
		coursesalestartdate = salestartdate + " 12:00 AM";
		course_sale_start_date.sendKeys(coursesalestartdate);
		log.info("set course sale start date is: " + coursesalestartdate);
	}

	public void setCourseSaleEndDate() {
		course_sale_end_date.sendKeys(coursestartdate);
		log.info("set course sale end date is: " + coursestartdate);
	}

	@FindBy(xpath = "//input[@id='grid[maxqty_68_1]']")
	private WebElement course_max_qty;

	public void setCourseMaxQty() {
		course_max_qty.sendKeys("9999");
	}

	@FindBy(xpath = "//span[@id='span_templates']")
	private WebElement section_templates;

	@FindBy(xpath = "//select[@id='product[event_template]']")
	private WebElement dropdown_template;

	public void setTemplate() throws Exception {
		log.info("i am within the select template method");
		section_templates.click();
		log.info("TEMPLATE section opened");
		Thread.sleep(2000);
		
		Select selecttemplate = new Select(dropdown_template);
		String eventTemplate_data = PropertyUtils.readProperty("eventTemplate");
		selecttemplate.selectByVisibleText(eventTemplate_data);
		log.info("selected template is: ");//+eventTemplate_data);
		// Thread.sleep(3000);

	}

	@FindBy(xpath = "//button[@id='btnsave']")
	private WebElement btn_save_event;

	public void saveEvent() {
		log.info("I am inside save event method");
		btn_save_event.click();
		log.info("EVENT SAVE button clicked");
	}

	@FindBy(xpath = "//button[@id='btnpublish']")
	public WebElement btn_publish_event;

	@FindBy(xpath = "//label[@class='admin__field-label']//span[contains(text(),'(Internal)')]")
	private WebElement event_name_internal;

	
	public void publishEvent() throws Exception {
	
		log.info("i am within the publish event method");
		Thread.sleep(10000);
		//  wait.until(ExpectedConditions.visibilityOf(btn_publish_event)).click();
		btn_publish_event.click();
		log.info("EVENT PUBLISH BUTTON clicked");
		  Thread.sleep(25000);
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

	
	@FindBy(xpath = "//input[contains(@name,'grid_sale_start_date') and contains(@value,'12:00 AM')]")
	private WebElement edit_course_sale_date;

	
	public void setSalestartDateAsYesterdaysDate() {
		String editedcoursesalestartdate;
		Calendar cal = Calendar.getInstance();
		System.out.println("Today's date is "+sdf.format(cal.getTime()));
		cal.add(Calendar.DATE,-1);
		String editsalestartdate = sdf.format(cal.getTime());
		editedcoursesalestartdate = editsalestartdate + " 12:00 AM";
		edit_course_sale_date.sendKeys(editedcoursesalestartdate);
		log.info("set course sale start date is: " + editedcoursesalestartdate);
		
	}
	
	
	@FindBy(xpath = "//span[@id='span_admin' and text()='Admin']")
	private WebElement section_admin;
	
	@FindBy(xpath="//select[@name='product[portal_beta]']")
	private WebElement drpdown_portalbeta;
	
	public void setPortalBetaYesFlag() throws InterruptedException {
		
		wait.until(ExpectedConditions.elementToBeClickable(section_admin)).click();
		log.info("ADMIN section opened");
		wait.until(ExpectedConditions.elementToBeClickable(drpdown_portalbeta));
		//Thread.sleep(3000);
		Select portalbeta = new Select(drpdown_portalbeta);
		portalbeta.selectByVisibleText("Yes");
		log.info("Portal Beta=YES is set");
		
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/portalbetaYESisset-" + ename + ".jpg"));
			log.info("screenshot captured");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		
	}
	
	// public event
	// call here again ---clickOnEventMenu

	public void clickOnAllEventsLink() {
		link_all_events.click();
		log.info("ALL EVENTS link selected");
	}

	@FindBy(xpath = "//button[@class='action-tertiary action-clear']")
	private WebElement link_clearallevent;

	public void clearAllEventFilterLink() {
		log.info("We are inside CLEAR ALL EVENT FILTER LINK method");
		
		/*
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
		 * btn_filters);
		 */
		 
		//wait.until(ExpectedConditions.visibilityOf(link_clearall));
		  try   //add this try-catch so tht if elemtn not displayed then ELSE condition will work
		  {

		if (link_clearallevent.isDisplayed()){
			link_clearallevent.click();
			log.info("CLEARALL link visible and clikced");
		}
		  }      
		  	catch(Exception e)     
		  	{

			log.info("clearall link not visible");}

	}

	@FindBy(xpath = "//button[text()='Filters']//parent::div")
	private WebElement btn_filters;

	public void clickOnEventFiltersBtn() {
		btn_filters.click();
		log.info("EVENTS FILTER button clicked");
	}

	@FindBy(xpath = "//span[text()='Event Name']//parent::label//following-sibling::div//input")
	private WebElement filter_eventname;

	@FindBy(xpath = "//span[text()='Apply Filters']//parent::button")
	private WebElement btn_apply_filters;

	public void setConfiguredEventNameFilter() throws Exception {
		String testeventselectedforordercases_data = PropertyUtils.readProperty("testeventselectedforordercases");

		filter_eventname.sendKeys(testeventselectedforordercases_data);
		log.info("event name: " + testeventselectedforordercases_data + ", added in filter");

		btn_apply_filters.click();
		log.info("APPLY FILTER button clicked");
	}

	public void setNewlyCreatedEventNameFilter() throws Exception {
		/*
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
		 * filter_eventname); new WebDriverWait(driver,
		 * 20).until(ExpectedConditions.visibilityOf(filter_eventname)).sendKeys(ename);
		 */
		wait.until(ExpectedConditions.elementToBeClickable(filter_eventname)).sendKeys(ename);
		btn_apply_filters.click();
		log.info("APPLY FILTER button clicked");
	}
	//below 2 method for search specific event and updating its status
	public void setExistingEventNameFilter() throws Exception {
		existingeventnameforupdatingstatus_data = PropertyUtils.readProperty("existingeventname");
		
		/*
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
		 * filter_eventname); new WebDriverWait(driver,
		 * 20).until(ExpectedConditions.visibilityOf(filter_eventname)).sendKeys(
		 * existingeventnameforupdatingstatus_data);
		 */
		wait.until(ExpectedConditions.elementToBeClickable(filter_eventname)).sendKeys(
				 existingeventnameforupdatingstatus_data);
		btn_apply_filters.click();
		log.info("APPLY FILTER button clicked");
	}
	
	public void searchNewlyCreatedEvent() throws Exception {//this used for end to end workflow
		existingeventnameforupdatingstatus_data = PropertyUtils.readProperty("existingeventname");
		
		/*
		 * ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
		 * filter_eventname); new WebDriverWait(driver,
		 * 20).until(ExpectedConditions.visibilityOf(filter_eventname)).sendKeys(
		 * existingeventnameforupdatingstatus_data);
		 */
		wait.until(ExpectedConditions.elementToBeClickable(filter_eventname)).sendKeys(
				 eventname);
		btn_apply_filters.click();
		log.info("APPLY FILTER button clicked");
	}
	
	@FindBy(xpath = "//button[@class='action-select']")
	private WebElement drpdown_select_event;
	
	@FindBy(xpath = "//a[@data-action='item-edit']")
	private WebElement drpdown_view_event;
	
	
	public void viewEvent() throws Exception {
		/*
		 * JavascriptExecutor jse = (JavascriptExecutor)driver;
		 * jse.executeScript("arguments[0].click()", drpdown_select_event);
		 */
		wait.until(ExpectedConditions.elementToBeClickable(drpdown_select_event)).click();
		
		log.info("clicked SELECT");
		//drpdown_select_event.click();
		Thread.sleep(2000);
		drpdown_view_event.click();
		log.info("clicked VIEW");
	}
	
	
	/*already defined above
	 * @FindBy(xpath = "//select[@id='product[event_status]']") private WebElement
	 * dropdown_event_status;
	 */
	
	public void updateEventStatusCancelled() throws Exception {
		
		Select select_status = new Select(dropdown_event_status);
		select_status.selectByVisibleText("Cancelled");
		log.info("Set Status=Cancelled");
		wait.until(ExpectedConditions.visibilityOf(btn_save_event)).click();
		//Thread.sleep(5000);
		//btn_save_event.click();
		log.info("SAVE button clciked");
		Thread.sleep(5000);
		js.executeScript("scroll(0, 300);");
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("target/screenshots/eventcancelled" + existingeventnameforupdatingstatus_data + ".jpg"));
		log.info("screenshot captured");

	}
	

	@FindBy(xpath = "//div[@class='data-grid-cell-content']//a")
	private WebElement link_launch_event_purchase;

	@FindBy(xpath = "//span[@class='data-grid-cell-content' and text()='Event Name']")
	private By column_header_eventname;

	public void launchEventPurchaseLink() throws Exception {
		log.info("I am in Launch event purchase link - method");

		oldtab = driver.getWindowHandle();
	
		wait.until(ExpectedConditions.elementToBeClickable(link_launch_event_purchase)).click();
		
		ArrayList<?> newtab = new ArrayList<Object>(driver.getWindowHandles());
		newtab.remove(oldtab);
		// change focus to new tab
		driver.switchTo().window((String) newtab.get(0));

		/// to switch back to main window follow below steps
		// driver.switchTo().window(oldtab);
	}

	@FindBy(xpath = "//h1[@class='page-title']//span")
	private WebElement cart_page_header;

	@FindBy(xpath = "//select[@id='qty_Build']")
	private WebElement select_built_qty;
	
	@FindBy(xpath = "//select[@id='qty_Launch']")
	private WebElement select_launch_qty;
	
	@FindBy(xpath = "//select[@id='qty_Foundations']")
	private WebElement select_foundations_qty;

	@FindBy(xpath = "//button[@title='Register']")
	private WebElement btn_register;

	public void setRegistrantItemQty(String qty) throws Exception {
		// String itemqty_data = PropertyUtils.readProperty("itemqty");
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/defaultqtyforpurchase.jpg"));
			log.info("screenshot captured");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		Select select_quantity = new Select(select_built_qty);
		select_quantity.selectByVisibleText(qty);
		log.info("Product item qty is selected");
		Thread.sleep(6000);
		//btn_register.click();
		//log.info("REGISTER button clicked");
	}
	
	public void setLaunchRegistrantItemQty(String qty) throws Exception {
		log.info("I am inside setLaunchRegistrantItemQty method");
		// String itemqty_data = PropertyUtils.readProperty("itemqty");
		/*
		 * File screenshot = ((TakesScreenshot)
		 * driver).getScreenshotAs(OutputType.FILE); try {
		 * FileUtils.copyFile(screenshot, new
		 * File("target/screenshots/defaultqtyforpurchase.jpg"));
		 * log.info("screenshot captured"); } catch (IOException e) {
		 * System.out.println(e.getMessage()); }
		 */
		Select select_quantity = new Select(select_foundations_qty);
		select_quantity.selectByVisibleText(qty);
		log.info("Product item qty is selected");
		Thread.sleep(6000);
		btn_register.click();
		log.info("REGISTER button clicked");
	}
	@FindBy(xpath = "//*[@class='course-info-details-wrap']//*[@class='course-info-cta']//child::*[@class='btn-add-to-cart addtocartsimple']")
	//private WebElement btn_register_fod;
	private WebElement btn_addtocart_fod;
	
	//@FindBy(xpath = "//*[@class='course-info-details-wrap']//*[@class='course-info-cta']//child::*[@class='btn-add-to-cart addtocart']")
	@FindBy(xpath = "//h2[text()='Build']//parent::div[@class='course-name-wrap']//following::div[@class='course-info-details-wrap']//child::a[@class='btn-add-to-cart addtocart']")
	//private WebElement btn_register_fod;
	private WebElement btn_new_event_course_addtocart;
	
	@FindBy(xpath = "//a[text()='Proceed to Register']")
	private WebElement btn_proceedtoregister;
	
	public void selectAddtocartAndProceedToRegistration() throws Exception {
		
		log.info("i am inside selectAddtocartAndPoceedToRegistration method");
		Thread.sleep(4000);
		btn_new_event_course_addtocart.click();
		log.info("in course details page add to cart button clicked");
		Thread.sleep(3000);
		btn_proceedtoregister.click();
		log.info("in course details page proceed to register button clicked");
		Thread.sleep(3000);
		//btn_register.click();
	}

	
	@FindBy(xpath = "//label[contains(@id,'1_US_Canada_message')]")
	private WebElement msg1_us_canada;
	
	
	
	public void generateReg1Email() throws Exception {
		System.out.println("----------generate regustrant 1 email---------");
		String reg1fname = PropertyUtils.readProperty("cartregistrant_fnamel");
		cartregistrant1_email = reg1fname + "-" + RandomStringUtils.randomNumeric(4) + "@flipick.com";
		;
		log.info("Random registrant-1 email generated is: " + cartregistrant1_email);
		registrant=cartregistrant1_email;//this is for end to end flow
	}

	public void generatePSPReg1Email() throws Exception {
		System.out.println("----------generate regustrant 1 email---------");
		String reg1fname = PropertyUtils.readProperty("cartPSPregistrant_fnamel");
		cartregistrant1_email = reg1fname + "-" + RandomStringUtils.randomNumeric(4) + "@flipick.com";
		;
		log.info("Random registrant-1 email generated is: " + cartregistrant1_email);
		registrant=cartregistrant1_email;//this is for end to end flow
	}
	public void generateODReg1Email() throws Exception {
		System.out.println("----------generate regustrant 1 email---------");
		String reg1fname = PropertyUtils.readProperty("cartODregistrant_fnamel");
		cartregistrant1_email = reg1fname + "-" + RandomStringUtils.randomNumeric(4) + "@flipick.com";
		;
		log.info("Random registrant-1 email generated is: " + cartregistrant1_email);
		registrant=cartregistrant1_email;//this is for end to end flow
	}
	public void generateReg2Email() throws Exception {
		System.out.println("----------generate regustrant 2 email---------");
		String reg2fname = PropertyUtils.readProperty("cartregistrant_fname2");
		cartregistrant2_email = reg2fname + "-" + RandomStringUtils.randomNumeric(4) + "@flipick.com";
		;
		log.info("Random registrant-2 email generated is: " + cartregistrant2_email);
	}

	public void generateReg3Email() throws Exception {
		System.out.println("----------generate regustrant 3 email---------");
		String reg3fname = PropertyUtils.readProperty("cartregistrant_fname3");
		cartregistrant3_email = reg3fname + "-" + RandomStringUtils.randomNumeric(4) + "@flipick.com";
		;
		log.info("Random registrant-3 email generated is: " + cartregistrant3_email);
	}

	@FindBy(xpath = "//*[@placeholder='Email*'][contains(@id,'1_email_address')]")
	private WebElement reg1_email;

	@FindBy(xpath = "//*[@placeholder='First Name*'][contains(@id,'1_first_name')]")
	private WebElement reg1_firstname;

	@FindBy(xpath = "//*[@placeholder='Last Name*'][contains(@id,'1_last_name')]")
	private WebElement reg1_lastname;

	@FindBy(xpath = "//*[@placeholder='Job Title*'][contains(@id,'1_job_title')]")
	private WebElement reg1_jobtitle;

	@FindBy(xpath = "//*[@placeholder='Company Name*'][contains(@id,'1_company')]")
	private WebElement reg1_companyname;

	@FindBy(xpath = "//td[@class='padding-right sb_display-flex']//select[contains(@id,'1_ismobile')]")
	private WebElement reg1_drpdwn_phoneoption;

	@FindBy(xpath = "//*[@placeholder='Phone/Mobile*'][contains(@id,'1_home_phone')]")
	private WebElement reg1_phone;

	@FindBy(xpath = "//*[@placeholder='Address Line 1'][contains(@id,'1_home_address_1')]")
	private WebElement reg1_address1;

	@FindBy(xpath = "//*[@placeholder='Address Line 2'][contains(@id,'1_home_address_2')]")
	private WebElement reg1_address2;

	@FindBy(xpath = "//*[@placeholder='City'][contains(@id,'1_home_city')]")
	private WebElement reg1_city;

	@FindBy(xpath = "//*[@placeholder='Zip Code'][contains(@id,'1_home_zip')]")
	private WebElement reg1_zipcode;

	@FindBy(xpath = "//*[@class='form-control input-sm  homecountry'][contains(@id,'1_home_country')]")
	private WebElement reg1_country;

	@FindBy(xpath = "//*[@class='form-control input-sm  ddhome_state'][contains(@id,'1_ddhome_state')]")
	private WebElement reg1_state;

	public void setRegistrantONEInfo() throws Exception {
		reg1_email.clear();
		reg1_email.sendKeys(cartregistrant1_email);
		log.info("registrant1 email id added");

		reg1_firstname.clear();
		cartregistrant_fnamel_data = PropertyUtils.readProperty("cartregistrant_fnamel");
		reg1_firstname.sendKeys(cartregistrant_fnamel_data);
		log.info("registrant1 first name added");
		Thread.sleep(1000);

		reg1_lastname.clear();
		cartregistrant_lnamel_data = PropertyUtils.readProperty("cartregistrant_lname1");
		reg1_lastname.sendKeys(cartregistrant_lnamel_data);
		log.info("registrant1 lastname added");
		Thread.sleep(1000);

		reg1_jobtitle.clear();
		String cartregistrant_jtitle1_data = PropertyUtils.readProperty("cartregistrant_jtitle1");
		reg1_jobtitle.sendKeys(cartregistrant_jtitle1_data);
		log.info("registrant1 job title added");
		Thread.sleep(1000);

		reg1_companyname.clear();
		String cartregistrant_company1_data = PropertyUtils.readProperty("cartregistrant_company1");
		reg1_companyname.sendKeys(cartregistrant_company1_data);
		log.info("registrant1 company name added");
		Thread.sleep(1000);

		
		  String cartregistrant_phoneoption1_data =
		  PropertyUtils.readProperty("cartregistrant_phoneoption1"); 
		  Select mobileoption = new Select(reg1_drpdwn_phoneoption);
		  mobileoption.selectByVisibleText(cartregistrant_phoneoption1_data);
		  log.info("registrant1 phoneoption value selected as: "+cartregistrant_phoneoption1_data); Thread.sleep(3000);
		 

		reg1_phone.clear();
		String cartregistrant_mobile1_data = PropertyUtils.readProperty("cartregistrant_mobile1");
		reg1_phone.sendKeys(cartregistrant_mobile1_data);
		log.info("registrant1 phone data added");
		Thread.sleep(1000);

		reg1_address1.clear();
		String cartregistrant_address1_1_data = PropertyUtils.readProperty("cartregistrant_address1_1");
		reg1_address1.sendKeys(cartregistrant_address1_1_data);
		log.info("registrant1 address1 added");
		Thread.sleep(1000);

		reg1_address2.clear();
		String cartregistrant_address1_2_data = PropertyUtils.readProperty("cartregistrant_address1_2");
		reg1_address2.sendKeys(cartregistrant_address1_2_data);
		log.info("registrant1 address2 added");
		Thread.sleep(1000);

		reg1_city.clear();
		String cartregistrant_city1_data = PropertyUtils.readProperty("cartregistrant_city1");
		reg1_city.sendKeys(cartregistrant_city1_data);
		log.info("registrant1 city added");
		Thread.sleep(1000);

		reg1_zipcode.clear();
		String cartregistrant_zipcode1_data = PropertyUtils.readProperty("cartregistrant_zipcode1");
		reg1_zipcode.sendKeys(cartregistrant_zipcode1_data);
		log.info("registrant1 zipcode added");
		Thread.sleep(1000);

		String cartregistrant_country1_data = PropertyUtils.readProperty("cartregistrant_country1");
		Select reg1country = new Select(reg1_country);
		reg1country.selectByVisibleText(cartregistrant_country1_data);
		log.info("registrant1 country selected");
		Thread.sleep(1000);

		String cartregistrant_state1_data = PropertyUtils.readProperty("cartregistrant_state1");
		Select reg1state = new Select(reg1_state);
		reg1state.selectByVisibleText(cartregistrant_state1_data);
		log.info("registrant1 state selected");
		Thread.sleep(1000);
		try   //add this try-catch so tht if elemtn not displayed then ELSE condition will work
		{
		if (msg1_us_canada.isDisplayed()){
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",msg1_us_canada);

		
		
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {FileUtils.copyFile(screenshot, new File("target/screenshots/FirstRegistrant-"+cartregistrant_country1_data+"-"+cartregistrant_phoneoption1_data+"-DisclaimerMsg.jpg"));
			log.info("screenshot captured");} 
		catch (IOException e) 
		{System.out.println(e.getMessage());}
		
		
		
		
		}
		
		}
		
		catch(Exception e)     
		
{log.info("Disclaimer msg is not available for this product and registrant");}
		
		}



	@FindBy(xpath = "//*[@placeholder='Email*'][contains(@id,'2_email_address')]")
	private WebElement reg2_email;

	@FindBy(xpath = "//*[@placeholder='First Name*'][contains(@id,'2_first_name')]")
	private WebElement reg2_firstname;

	@FindBy(xpath = "//*[@placeholder='Last Name*'][contains(@id,'2_last_name')]")
	private WebElement reg2_lastname;

	@FindBy(xpath = "//*[@placeholder='Job Title*'][contains(@id,'2_job_title')]")
	private WebElement reg2_jobtitle;

	@FindBy(xpath = "//*[@placeholder='Company Name*'][contains(@id,'2_company')]")
	private WebElement reg2_companyname;

	@FindBy(xpath = "//*[contains(@id,'2_ismobile')]")
	private WebElement reg2_drpdwn_phoneoption;

	@FindBy(xpath = "//*[@placeholder='Phone/Mobile*'][contains(@id,'2_home_phone')]")
	private WebElement reg2_phone;

	@FindBy(xpath = "//*[@placeholder='Address Line 1'][contains(@id,'2_home_address_1')]")
	private WebElement reg2_address1;

	@FindBy(xpath = "//*[@placeholder='Address Line 2'][contains(@id,'2_home_address_2')]")
	private WebElement reg2_address2;

	@FindBy(xpath = "//*[@placeholder='City'][contains(@id,'2_home_city')]")
	private WebElement reg2_city;

	@FindBy(xpath = "//*[@placeholder='Zip Code'][contains(@id,'2_home_zip')]")
	private WebElement reg2_zipcode;

	@FindBy(xpath = "//*[@class='form-control input-sm  homecountry'][contains(@id,'2_home_country')]")
	private WebElement reg2_country;

	@FindBy(xpath = "//*[@class='form-control input-sm  ddhome_state'][contains(@id,'2_ddhome_state')]")
	private WebElement reg2_state;

	@FindBy(xpath = "//label[contains(@id,'2_US_Canada_message')]")
	private WebElement msg2_us_canada;
	
	public void setRegistrantTWOInfo() throws Exception {
		reg2_email.clear();
		reg2_email.sendKeys(cartregistrant2_email);
		log.info("registrant2 email id added");
		Thread.sleep(1000);

		reg2_firstname.clear();
		String cartregistrant_fname2_data = PropertyUtils.readProperty("cartregistrant_fname2");
		reg2_firstname.sendKeys(cartregistrant_fname2_data);
		log.info("registrant2 first name added");
		Thread.sleep(1000);

		reg2_lastname.clear();
		String cartregistrant_lname2_data = PropertyUtils.readProperty("cartregistrant_lname2");
		reg2_lastname.sendKeys(cartregistrant_lname2_data);
		log.info("registrant2 last name added");
		Thread.sleep(1000);

		reg2_jobtitle.clear();
		String cartregistrant_jtitle2_data = PropertyUtils.readProperty("cartregistrant_jtitle2");
		reg2_jobtitle.sendKeys(cartregistrant_jtitle2_data);
		log.info("registrant2 jobtitle added");
		Thread.sleep(1000);

		reg2_companyname.clear();
		String cartregistrant_company2_data = PropertyUtils.readProperty("cartregistrant_company2");
		reg2_companyname.sendKeys(cartregistrant_company2_data);
		log.info("registrant2 company added");
		Thread.sleep(1000);

		String cartregistrant_phoneoption2_data = PropertyUtils.readProperty("cartregistrant_phoneoption2");
		Select mobileoption2 = new Select(reg2_drpdwn_phoneoption);
		mobileoption2.selectByVisibleText(cartregistrant_phoneoption2_data);
		log.info("registrant2 phone option selected as: "+cartregistrant_phoneoption2_data);
		Thread.sleep(3000);

		reg2_phone.clear();
		String cartregistrant_mobile2_data = PropertyUtils.readProperty("cartregistrant_mobile2");
		reg2_phone.sendKeys(cartregistrant_mobile2_data);
		log.info("registrant2 phone data added");
		Thread.sleep(1000);

		reg2_address1.clear();
		String cartregistrant_address2_1_data = PropertyUtils.readProperty("cartregistrant_address2_1");
		reg2_address1.sendKeys(cartregistrant_address2_1_data);
		log.info("registrant2 address1 added");
		Thread.sleep(1000);

		reg2_address2.clear();
		String cartregistrant_address2_2_data = PropertyUtils.readProperty("cartregistrant_address2_2");
		reg2_address2.sendKeys(cartregistrant_address2_2_data);
		log.info("registrant2 address2 added");
		Thread.sleep(1000);

		reg2_city.clear();
		String cartregistrant_city2_data = PropertyUtils.readProperty("cartregistrant_city2");
		reg2_city.sendKeys(cartregistrant_city2_data);
		log.info("registrant2 city added");
		Thread.sleep(1000);

		reg2_zipcode.clear();
		String cartregistrant_zipcode2_data = PropertyUtils.readProperty("cartregistrant_zipcode2");
		reg2_zipcode.sendKeys(cartregistrant_zipcode2_data);
		log.info("registrant2 zipcode added");
		Thread.sleep(1000);

		String cartregistrant_country2_data = PropertyUtils.readProperty("cartregistrant_country2");
		Select reg2country = new Select(reg2_country);
		reg2country.selectByVisibleText(cartregistrant_country2_data);
		log.info("registrant2 country selected");
		Thread.sleep(1000);

		String cartregistrant_state2_data = PropertyUtils.readProperty("cartregistrant_state2");
		Select reg2state = new Select(reg2_state);
		reg2state.selectByVisibleText(cartregistrant_state2_data);
		log.info("registrant2 state selected");
		Thread.sleep(1000);
		
		try   //add this try-catch so tht if elemtn not displayed then ELSE condition will work
		{
		if (msg2_us_canada.isDisplayed()){
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",msg2_us_canada);

		
		
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {FileUtils.copyFile(screenshot, new File("target/screenshots/SecondRegistrant-"+cartregistrant_country2_data+"-"+cartregistrant_phoneoption2_data+"-DisclaimerMsg.jpg"));
			log.info("screenshot captured");} 
		catch (IOException e) 
		{System.out.println(e.getMessage());}
		
		
		
		
		}
		
		}
		
		catch(Exception e)     
		
{log.info("Disclaimer msg is not available for this product and registrant");}
		
		}

	@FindBy(xpath = "//*[@placeholder='Email*'][contains(@id,'3_email_address')]")
	private WebElement reg3_email;

	@FindBy(xpath = "//*[@placeholder='First Name*'][contains(@id,'3_first_name')]")
	private WebElement reg3_firstname;

	@FindBy(xpath = "//*[@placeholder='Last Name*'][contains(@id,'3_last_name')]")
	private WebElement reg3_lastname;

	@FindBy(xpath = "//*[@placeholder='Job Title*'][contains(@id,'3_job_title')]")
	private WebElement reg3_jobtitle;

	@FindBy(xpath = "//*[@placeholder='Company Name*'][contains(@id,'3_company')]")
	private WebElement reg3_companyname;

	@FindBy(xpath = "//*[contains(@id,'3_ismobile')]")
	private WebElement reg3_drpdwn_phoneoption;

	@FindBy(xpath = "//*[@placeholder='Phone/Mobile*'][contains(@id,'3_home_phone')]")
	private WebElement reg3_phone;

	@FindBy(xpath = "//*[@placeholder='Address Line 1'][contains(@id,'3_home_address_1')]")
	private WebElement reg3_address1;

	@FindBy(xpath = "//*[@placeholder='Address Line 2'][contains(@id,'3_home_address_2')]")
	private WebElement reg3_address2;

	@FindBy(xpath = "//*[@placeholder='City'][contains(@id,'3_home_city')]")
	private WebElement reg3_city;

	@FindBy(xpath = "//*[@placeholder='Zip Code'][contains(@id,'3_home_zip')]")
	private WebElement reg3_zipcode;

	@FindBy(xpath = "//*[@class='form-control input-sm  homecountry'][contains(@id,'3_home_country')]")
	private WebElement reg3_country;

	@FindBy(xpath = "//*[@class='form-control input-sm  ddhome_state'][contains(@id,'3_ddhome_state')]")
	private WebElement reg3_state;
	
	@FindBy(xpath = "//label[contains(@id,'3_US_Canada_message')]")
	private WebElement msg3_us_canada;

	public void setRegistrantTHREEInfo() throws Exception {
		reg3_email.clear();
		reg3_email.sendKeys(cartregistrant3_email);
		log.info("registrant3 email id added");
		Thread.sleep(1000);

		reg3_firstname.clear();
		String cartregistrant_fname3_data = PropertyUtils.readProperty("cartregistrant_fname3");
		reg3_firstname.sendKeys(cartregistrant_fname3_data);
		log.info("registrant3 first name added");
		Thread.sleep(1000);

		reg3_lastname.clear();
		String cartregistrant_lname3_data = PropertyUtils.readProperty("cartregistrant_lname3");
		reg3_lastname.sendKeys(cartregistrant_lname3_data);
		log.info("registrant3 last name added");
		Thread.sleep(1000);

		reg3_jobtitle.clear();
		String cartregistrant_jtitle3_data = PropertyUtils.readProperty("cartregistrant_jtitle3");
		reg3_jobtitle.sendKeys(cartregistrant_jtitle3_data);
		log.info("registrant3 job title added");
		Thread.sleep(1000);

		reg3_companyname.clear();
		String cartregistrant_company3_data = PropertyUtils.readProperty("cartregistrant_company3");
		reg3_companyname.sendKeys(cartregistrant_company3_data);
		log.info("registrant3 company added");
		Thread.sleep(1000);

		String cartregistrant_phoneoption3_data = PropertyUtils.readProperty("cartregistrant_phoneoption3");
		Select mobileoption3 = new Select(reg3_drpdwn_phoneoption);
		mobileoption3.selectByVisibleText(cartregistrant_phoneoption3_data);
		log.info("registrant3 phone option selected as: "+cartregistrant_phoneoption3_data);
		Thread.sleep(3000);

		reg3_phone.clear();
		String cartregistrant_mobile3_data = PropertyUtils.readProperty("cartregistrant_mobile3");
		reg3_phone.sendKeys(cartregistrant_mobile3_data);
		log.info("registrant3 phone data added");
		Thread.sleep(1000);

		reg3_address1.clear();
		String cartregistrant_address3_1_data = PropertyUtils.readProperty("cartregistrant_address3_1");
		reg3_address1.sendKeys(cartregistrant_address3_1_data);
		log.info("registrant3 address1 added");
		Thread.sleep(1000);

		reg3_address2.clear();
		String cartregistrant_address3_2_data = PropertyUtils.readProperty("cartregistrant_address3_2");
		reg3_address2.sendKeys(cartregistrant_address3_2_data);
		log.info("registrant3 address2 added");
		Thread.sleep(1000);

		reg3_city.clear();
		String cartregistrant_city3_data = PropertyUtils.readProperty("cartregistrant_city3");
		reg3_city.sendKeys(cartregistrant_city3_data);
		log.info("registrant3 city added");
		Thread.sleep(1000);

		reg3_zipcode.clear();
		String cartregistrant_zipcode3_data = PropertyUtils.readProperty("cartregistrant_zipcode3");
		reg3_zipcode.sendKeys(cartregistrant_zipcode3_data);
		log.info("registrant3 zipcode added");
		Thread.sleep(1000);

		String cartregistrant_country3_data = PropertyUtils.readProperty("cartregistrant_country3");
		Select reg3country = new Select(reg3_country);
		reg3country.selectByVisibleText(cartregistrant_country3_data);
		log.info("registrant3 country selected");
		Thread.sleep(1000);

		String cartregistrant_state3_data = PropertyUtils.readProperty("cartregistrant_state3");
		Select reg3state = new Select(reg3_state);
		reg3state.selectByVisibleText(cartregistrant_state3_data);
		log.info("registrant3 state added");
		Thread.sleep(1000);
		
		try   //add this try-catch so tht if elemtn not displayed then ELSE condition will work
		{
		if (msg3_us_canada.isDisplayed()){
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",msg3_us_canada);

		
		
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {FileUtils.copyFile(screenshot, new File("target/screenshots/ThirdRegistrant-"+cartregistrant_country3_data+"-"+cartregistrant_phoneoption3_data+"-DisclaimerMsg.jpg"));
			log.info("screenshot captured");} 
		catch (IOException e) 
		{System.out.println(e.getMessage());}
		
		
		
		
		}
		
		}
		
		catch(Exception e)     
		
{log.info("Disclaimer msg is not available for this product and registrant");}
		
		}


	@FindBy(xpath = "//input[@name='coupon_code']")
	private WebElement coupon_code;

	@FindBy(xpath = "//button[@id='btnCoupon' and @value='Apply']")
	private WebElement btn_apply_coupon_code;

	public void applyCouponCode() throws Exception {
		log.info("I am in applycoupon code method");
		String discountcouponcode_data = PropertyUtils.readProperty("discountcouponcode");
		coupon_code.sendKeys(discountcouponcode_data);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
				btn_apply_coupon_code);

		btn_apply_coupon_code.click();
		log.info("APPLY COUPON CODE button clicked");
	}

	@FindBy(xpath = "//div[@class='proceed-btn-wrap']//button[@id='btnCheckout' and @title='Proceed to Payment']")
	private WebElement btn_proceed_to_checkout;

	public void clickProceedToCheckoutBtn() {
		log.info("I am in clickProceedToCheckoutBtn method");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
				btn_proceed_to_checkout);

		
		wait.until(ExpectedConditions.elementToBeClickable(btn_proceed_to_checkout)).click();

		
		/*
		 * JavascriptExecutor jse = (JavascriptExecutor) driver;
		 * jse.executeScript("arguments[0].click()", btn_proceed_to_checkout);
		 * btn_proceed_to_checkout.click();
		 */
		log.info("PROCEED TO CHECKOUT button clicked");
	}

	@FindBy(xpath = "//div[@class='control input-field _with-tooltip']//input[@type='email' and @name='username']")
	private WebElement billing_email;

	@FindBy(xpath = "//select[@id='dd_registrant']")
	private WebElement drpdwn_copy_info;

	@FindBy(xpath = "//div[@name='shippingAddress.firstname']//input[@placeholder='First Name*']")
	private WebElement biller_firstname;

	@FindBy(xpath = "//div[@name='shippingAddress.lastname']//input[@placeholder='Last Name*']")
	private WebElement biller_lastname;

	@FindBy(xpath = "//div[@name='shippingAddress.telephone']//input[@placeholder='Phone/Mobile*']")
	private WebElement biller_phone;

	@FindBy(xpath = "//div[@name='shippingAddress.custom_attributes.job_title']//input[@placeholder='Job Title*']")
	private WebElement biller_jobtitle;

	@FindBy(xpath = "//div[@name='shippingAddress.company']//input[@placeholder='Company Name*']")
	private WebElement biller_company;

	@FindBy(xpath = "//div[@name='shippingAddress.street.0']//input[@placeholder='Address Line 1*']")
	private WebElement biller_address1;

	@FindBy(xpath = "//div[@name='shippingAddress.street.1']//input[@placeholder='Address Line 2']")
	private WebElement biller_address2;

	@FindBy(xpath = "//div[@name='shippingAddress.city']//input[@placeholder='City*']")
	private WebElement biller_city;

	@FindBy(xpath = "//div[@name='shippingAddress.postcode']//input[@placeholder='Zip Code*']")
	private WebElement biller_zipcode;

	@FindBy(xpath = "//div[@name='shippingAddress.country_id']//select[@name='country_id']")
	private WebElement biller_country;

	@FindBy(xpath = "//div[@name='shippingAddress.region_id']//select[@name='region_id']")
	private WebElement biller_state;

	@FindBy(xpath = "//input[@title='Credit Card Number']")
	private WebElement card_number;

	@FindBy(xpath = "//select[@name='payment[cc_exp_month]']")
	private WebElement drpdwn_exp_month;

	@FindBy(xpath = "//select[@name='payment[cc_exp_year]']")
	private WebElement drpdwn_exp_year;

	@FindBy(xpath = "//input[@name='payment[cc_cid]']")
	private WebElement card_cvv;

	@FindBy(xpath = "//input[@id='_chkwaiver']")
	private WebElement chkbox_agree;

	@FindBy(xpath = "//div[@class='place-order-primary']//button[@title='Place Order']")
	private WebElement btn_placeorder;

	public void setBillingInfo() {
		log.info("I am in setBillingInfo method");
		billing_email.sendKeys(cartregistrant1_email);
		Select copyinfo = new Select(drpdwn_copy_info);

		String drpdownvaluetobeselected = cartregistrant_fnamel_data + " " + cartregistrant_lnamel_data + "("
				+ cartregistrant1_email + ")";
		copyinfo.selectByVisibleText(drpdownvaluetobeselected);
		log.info("biller info added");
	}

	public void generateNewBillerEmail() throws Exception {
		System.out.println("----------generate new Biller email---------");
		String newbillerfirstname_data = PropertyUtils.readProperty("newbillerfirstname");
		newBiller_email = newbillerfirstname_data + "-" + RandomStringUtils.randomNumeric(4) + "@flipick.com";

		log.info("Random Biller email generated is: " + newBiller_email);
	}

	@FindBy(xpath = "//div[@name='shippingAddress.firstname']//descendant::input[@placeholder='First Name*' and @name='firstname']")
	private WebElement newbiller_fname;

	@FindBy(xpath = "//div[@name='shippingAddress.lastname']//descendant::input[@placeholder='Last Name*' and @name='lastname']")
	private WebElement newbiller_lname;

	@FindBy(xpath = "//div[@name='shippingAddress.telephone']//descendant::input[@placeholder='Phone/Mobile*' and @name='telephone']")
	private WebElement newbiller_phone;

	@FindBy(xpath = "//div[@name='shippingAddress.custom_attributes.job_title']//descendant::input[@placeholder='Job Title*' and @name='custom_attributes[job_title]']")
	private WebElement newbiller_title;

	@FindBy(xpath = "//div[@name='shippingAddress.company']//descendant::input[@placeholder='Company Name*' and @name='company']")
	private WebElement newbiller_company;

	@FindBy(xpath = "//div[@name='shippingAddress.street.0']//descendant::input[@placeholder='Address Line 1*' and @name='street[0]']")
	private WebElement newbiller_add1;

	@FindBy(xpath = "//div[@name='shippingAddress.street.1']//descendant::input[@placeholder='Address Line 2' and @name='street[1]']")
	private WebElement newbiller_add2;

	@FindBy(xpath = "//div[@name='shippingAddress.city']//descendant::input[@placeholder='City*' and @name='city']")
	private WebElement newbiller_city;

	@FindBy(xpath = "//div[@name='shippingAddress.postcode']//descendant::input[@placeholder='Zip Code*' and @name='postcode']")
	private WebElement newbiller_zipcode;

	@FindBy(xpath = "//div[@name='shippingAddress.country_id']//select[@name='country_id']")
	private WebElement newbiller_country;

	@FindBy(xpath = "//div[@name='shippingAddress.region_id']//select[@name='region_id']")
	private WebElement newbiller_state;

	public void setNewBillingInfo() throws Exception {
		billing_email.sendKeys(newBiller_email);
		Thread.sleep(2000);

		String newbillerfirstname_data = PropertyUtils.readProperty("newbillerfirstname");
		newbiller_fname.sendKeys(newbillerfirstname_data);
		Thread.sleep(2000);

		String newbillerlastname_data = PropertyUtils.readProperty("newbillerlastname");
		newbiller_lname.sendKeys(newbillerlastname_data);
		Thread.sleep(2000);

		String newbillerphone_data = PropertyUtils.readProperty("newbillerphone");
		newbiller_phone.sendKeys(newbillerphone_data);
		Thread.sleep(2000);

		String newbillertitle_data = PropertyUtils.readProperty("newbillertitle");
		newbiller_title.sendKeys(newbillertitle_data);
		Thread.sleep(2000);

		String newbillercompany_data = PropertyUtils.readProperty("newbillercompany");
		newbiller_company.sendKeys(newbillercompany_data);
		Thread.sleep(5000);

		String newbilleraddress1_data = PropertyUtils.readProperty("newbilleraddress1");
		newbiller_add1.sendKeys(newbilleraddress1_data);
		Thread.sleep(2000);

		String newbilleraddress2_data = PropertyUtils.readProperty("newbilleraddress2");
		newbiller_add2.sendKeys(newbilleraddress2_data);
		Thread.sleep(2000);

		String newbillercity_data = PropertyUtils.readProperty("newbillercity");
		newbiller_city.sendKeys(newbillercity_data);
		Thread.sleep(2000);

		String newbillerzipcode_data = PropertyUtils.readProperty("newbillerzipcode");
		newbiller_zipcode.sendKeys(newbillerzipcode_data);
		Thread.sleep(2000);

		String newbillercountry_data = PropertyUtils.readProperty("newbillercountry");
		Select newbillercountry = new Select(newbiller_country);
		newbillercountry.selectByVisibleText(newbillercountry_data);
		Thread.sleep(2000);

		String newbillerstate_data = PropertyUtils.readProperty("newbillerstate");
		Select newbillerstates = new Select(newbiller_state);
		newbillerstates.selectByVisibleText(newbillerstate_data);
		Thread.sleep(2000);
		log.info("New biller details added");
	}

	

	@FindBy(xpath = "//div[@class='payment-method-title field choice']//label[@for='authnetcim']")
	private WebElement radio_selectbillingoption;
	
	
	
	public void setCardDetails() throws Exception {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
				radio_selectbillingoption);
		
		
		radio_selectbillingoption.click();
		log.info("Credit card radio button selected");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(card_number));
		String cardnumber_data = PropertyUtils.readProperty("cardnumber");
		card_number.sendKeys(cardnumber_data);
		log.info("Credit card number added");
		Thread.sleep(1000);
		String exp_month_data = PropertyUtils.readProperty("exp_month");
		Select emonth = new Select(drpdwn_exp_month);
		emonth.selectByVisibleText(exp_month_data);
		Thread.sleep(1000);

		String exp_year_data = PropertyUtils.readProperty("exp_year");
		Select eyear = new Select(drpdwn_exp_year);
		eyear.selectByVisibleText(exp_year_data);
		Thread.sleep(1000);

		String cvv_data = PropertyUtils.readProperty("cvv");
		card_cvv.sendKeys(cvv_data);
		Thread.sleep(1000);
	}

	@FindBy(xpath = "//h3[contains(text(),'Order Confirmation')]")
	private WebElement msg_order_confirmation;

	@FindBy(xpath = "//*[@id='top-content']/div[3]/div[2]/div/span[1]/span[2]")
	private WebElement order_id;

	public void placeOrder() throws Exception {
		
		  JavascriptExecutor jse = (JavascriptExecutor) driver;
		  jse.executeScript("arguments[0].click()", chkbox_agree);
		 
		//wait.until(ExpectedConditions.elementToBeClickable(chkbox_agree)).click();
		// chkbox_agree.click();
		log.info("I AGREE checkbox selected");
		Thread.sleep(2000);
		
		//wait.until(ExpectedConditions.elementToBeClickable(btn_placeorder)).click();

		
		  wait.until(ExpectedConditions.elementToBeClickable(btn_placeorder));
		  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
		  btn_placeorder);
		  
		  
		  btn_placeorder.click();
		 
		log.info("PLACE ORDER button clicked");
		Thread.sleep(20000);

		if (msg_order_confirmation.isDisplayed()) {
			log.info("order confirmation msg is: " + msg_order_confirmation.getText());
			orderid = order_id.getText();
			log.info("order confirmed and order id is: " + orderid);
			// *********************String newbillercountry_data = PropertyUtils.
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File("target/screenshots/frontendordercreated-" + orderid + ".jpg"));
			log.info("screenshot captured");
		} else {
			log.info("order failed");
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshot, new File("target/screenshots/frontendorder_failed.jpg"));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		Thread.sleep(2000);
		driver.close();//added on 18th jan 23
		driver.switchTo().window(oldtab);

	}
}
