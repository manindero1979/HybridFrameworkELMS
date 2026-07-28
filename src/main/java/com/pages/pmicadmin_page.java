package com.pages;

import java.awt.Toolkit;
import java.time.Duration;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.base.BaseClass;
import com.utility.PropertyUtils;

public class pmicadmin_page extends BaseClass {

	private WebDriverWait wait;

	public String pmictab;
	public String psptab;

	String oldpmictab;

	// public static ArrayList<String> tabs;

	// constructor
	public pmicadmin_page(WebDriver driver) {

		this.driver = driver;
		//wait = new WebDriverWait(driver, 50, 50);
		WebDriverWait wait = new WebDriverWait(
		        driver,
		        Duration.ofSeconds(50)
		);
		//wait = new WebDriverWait(driver, Duration.ofSeconds(50), Duration.ofMillis(50));
		PageFactory.initElements(driver, this);
	}

	// main links
	@FindBy(xpath = "//a[@id='InstructorManagement' and @title='Users']")
	private WebElement link_users;

	public void selectUsersLink() {
		link_users.click();
		log.info("Users link is launched");
	}

	@FindBy(xpath = "//input[@value='Add User']")
	private WebElement btn_adduser;

	public void clickAddUserbtn() {
		btn_adduser.click();
	}

	@FindBy(xpath = "//input[contains(@id,'FirstName')]")
	private WebElement add_newuser_firstname;

	@FindBy(xpath = "//input[contains(@id,'LastName')]")
	private WebElement add_newuser_lastname;

	@FindBy(xpath = "//span[text()='Email Address']//following-sibling::input")
	private WebElement add_newuser_emailid;

	@FindBy(xpath = "//input[@id='txtpwd']")
	private WebElement add_newuser_password;

	@FindBy(xpath = "//input[@id='txtcnmpwd']")
	private WebElement add_newuser_confirmpassword;

	@FindBy(xpath = "//select[@id='drpRole']")
	private WebElement drpdown_newuser_role;

	@FindBy(xpath = "//input[contains(@id,'btnSubmit')]")
	private WebElement btn_newuser_register;

	@FindBy(xpath = "//input[contains(@id,'btnCancel')]")
	private WebElement btn_newuser_cancel;

	public String newuseremail;

	public void generatenewadminuseremail() throws Exception {
		System.out.println("----------new user email id---------");
		String newuser_firstname_data = PropertyUtils.readProperty("newuser_firstname");

		String newuser_domain_data = PropertyUtils.readProperty("newuser_domain");
		newuseremail = newuser_firstname_data + "." + RandomStringUtils.randomNumeric(4) + newuser_domain_data;

	}

	public void addNewUser() throws Exception {
		String newuser_firstname_data = PropertyUtils.readProperty("newuser_firstname");
		String newuser_lastname_data = PropertyUtils.readProperty("newuser_lastname");
		String newuser_password_data = PropertyUtils.readProperty("newuser_password");
		String newuser_role_data = PropertyUtils.readProperty("newuser_role");

		add_newuser_firstname.sendKeys(newuser_firstname_data);
		add_newuser_lastname.sendKeys(newuser_lastname_data);
		add_newuser_emailid.sendKeys(newuseremail);
		add_newuser_password.sendKeys(newuser_password_data);
		add_newuser_confirmpassword.sendKeys(newuser_password_data);

		Select select_role = new Select(drpdown_newuser_role);
		select_role.selectByVisibleText(newuser_role_data);
		btn_newuser_register.click();

	}

	@FindBy(xpath = "//input[contains(@id,'btnFilterShow')]")
	private WebElement btn_filteruser;

	public void clickFilterBtn() {
		btn_filteruser.click();
	}

	@FindBy(xpath = "//input[@id='txtName']")
	private WebElement add_filterby_name;

	@FindBy(xpath = "//input[@id='txtLoginName']")
	private WebElement add_filterby_loginname;

	@FindBy(xpath = "//select[@id='drpRole']")
	private WebElement add_filterby_role;

	@FindBy(xpath = "//input[contains(@id,'ApplyFilter')]")
	private WebElement btn_applyfilters;

	@FindBy(xpath = "//input[contains(@id,'btnClear')]")
	private WebElement btn_clearfilters;

	public void searchUserByName() throws Exception {
		String search_adminuser_name_data = PropertyUtils.readProperty("search_adminuser_name");
		add_filterby_name.sendKeys(search_adminuser_name_data);
		btn_applyfilters.click();
	}

	public void searchUserByLoginname() throws Exception {
		String search_adminuser_loginname_data = PropertyUtils.readProperty("search_adminuser_loginname");
		add_filterby_loginname.sendKeys(search_adminuser_loginname_data);
		btn_applyfilters.click();
	}

	public void searchUserByRole() throws Exception {
		String search_adminuser_role_data = PropertyUtils.readProperty("search_adminuser_role");

		Select select_role = new Select(add_filterby_role);
		select_role.selectByVisibleText(search_adminuser_role_data);
		btn_applyfilters.click();
	}

	@FindBy(xpath = "//a[@title='Edit']")
	private WebElement icon_edituser;

	@FindBy(xpath = "//a[@title='Delete Selected Institute User']")
	private WebElement icon_deleteuser;

	public void editUserModifyFirstname() throws Exception {
		icon_edituser.click();
		String edit_user_firstname_data = PropertyUtils.readProperty("edit_user_firstname");
		add_newuser_firstname.sendKeys(edit_user_firstname_data);
		btn_newuser_register.click();

	}

	public void editUserModifyLastname() throws Exception {
		icon_edituser.click();
		String edit_user_lastname_data = PropertyUtils.readProperty("edit_user_lastname");
		add_newuser_lastname.sendKeys(edit_user_lastname_data);
		btn_newuser_register.click();

	}

	public void editUserModifyPassword() throws Exception {
		icon_edituser.click();
		String edit_user_password_data = PropertyUtils.readProperty("edit_user_password");
		add_newuser_password.sendKeys(edit_user_password_data);
		add_newuser_confirmpassword.sendKeys(edit_user_password_data);
		btn_newuser_register.click();

	}

	public void editUserModifyRole() throws Exception {
		icon_edituser.click();
		String edit_user_role_data = PropertyUtils.readProperty("edit_user_role");

		Select select_role = new Select(drpdown_newuser_role);
		select_role.selectByVisibleText(edit_user_role_data);
		btn_newuser_register.click();
	}

	@FindBy(xpath = "//a[contains(@id,'CP_users')]")
	private WebElement tab_users;

	public void clickUsersTab() {
		tab_users.click();
	}

	@FindBy(xpath = "//a[contains(@id,'CP_roles')]")
	private WebElement tab_roles;

	public void clickRolesTab() {
		tab_roles.click();
		log.info("Role tab is selected");
	}

	@FindBy(xpath = "//input[contains(@id,'btnAddRole')]")
	private WebElement btn_addnewrole;

	public void clickAddNewRoleBtn() {
		btn_addnewrole.click();
	}

	@FindBy(xpath = "//input[@id='txtRoleName']")
	private WebElement add_rolename;

	@FindBy(xpath = "//td[@class='tvTilePermissions_2']//descendant::a[text()='Users']//preceding-sibling::input[@type='checkbox']")
	private WebElement chkbox_users_permission;

	@FindBy(xpath = "//td[@class='tvTilePermissions_2']//descendant::a[text()='Courses']//preceding-sibling::input[@type='checkbox']")
	private WebElement chkbox_courses_permission;

	@FindBy(xpath = "//td[@class='tvTilePermissions_2']//descendant::a[text()='Students']//preceding-sibling::input[@type='checkbox']")
	private WebElement chkbox_students_permission;

	@FindBy(xpath = "//td[@class='tvTilePermissions_2']//descendant::a[text()=''Manage Batches']//preceding-sibling::input[@type='checkbox']")
	private WebElement chkbox_managebatches_permission;

	@FindBy(xpath = "//td[@class='tvTilePermissions_2']//descendant::a[text()='Reports']//preceding-sibling::input[@type='checkbox']")
	private WebElement chkbox_reports_permission;

	@FindBy(xpath = "//td[@class='tvTilePermissions_2']//descendant::a[text()='Settings']//preceding-sibling::input[@type='checkbox']")
	private WebElement chkbox_settings_permission;

	@FindBy(xpath = "//td[@class='tvTilePermissions_2']//descendant::a[text()='Students Registered']//preceding-sibling::input[@type='checkbox']")
	private WebElement chkbox_studentsregistered_permission;

	@FindBy(xpath = "//td[@class='tvTilePermissions_2']//descendant::a[text()='Students Activated']//preceding-sibling::input[@type='checkbox']")
	private WebElement chkbox_studentsactivated_permission;

	@FindBy(xpath = "//td[@class='tvTilePermissions_2']//descendant::a[text()='Profile']//preceding-sibling::input[@type='checkbox']")
	private WebElement chkbox_profile_permission;

	@FindBy(xpath = "//td[@class='tvTilePermissions_2']//descendant::a[text()='Exam Dashboard']//preceding-sibling::input[@type='checkbox']")
	private WebElement chkbox_examdashboard_permission;

	@FindBy(xpath = "//td[@class='tvTilePermissions_2']//descendant::a[text()='Question Bank']//preceding-sibling::input[@type='checkbox']")
	private WebElement chkbox_questionbank_permission;

	@FindBy(xpath = "//td[@class='tvTilePermissions_2']//descendant::a[text()='View Archived Data']//preceding-sibling::input[@type='checkbox']")
	private WebElement chkbox_viewarchiveddata_permission;

	@FindBy(xpath = "//input[contains(@id,'btnSubmit')]")
	private WebElement btn_createrole;

	@FindBy(xpath = "//input[contains(@id,'btnCancel')]")
	private WebElement btn_cancelrole;

	public void createnewrole() throws Exception {
		String new_rolename_data = PropertyUtils.readProperty("new_rolename");

		add_rolename.sendKeys(new_rolename_data);
		chkbox_users_permission.click();
		chkbox_courses_permission.click();
		chkbox_examdashboard_permission.click();
		chkbox_managebatches_permission.click();
		chkbox_profile_permission.click();
		chkbox_questionbank_permission.click();
		chkbox_reports_permission.click();
		chkbox_settings_permission.click();
		chkbox_students_permission.click();
		chkbox_studentsactivated_permission.click();
		chkbox_studentsregistered_permission.click();
		chkbox_viewarchiveddata_permission.click();

		Thread.sleep(1000);
		btn_createrole.click();
	}

	@FindBy(xpath = "//a[@id='lnkPageNoVideo' and text()='Next']")
	private WebElement icon_nextpage;

	@FindBy(xpath = "//input[@value='Update']")
	private WebElement btn_updaterole;

	public void searchRoleAndEdit() throws Exception {
		String search_role_data = PropertyUtils.readProperty("search_role_foredition");

		By searchEditRoleLocator = By
				.xpath("//td[text()='" + search_role_data + "']//following-sibling::td//a[@title='Edit']");

		// td[text()='IT User']//following-sibling::td//a[@title='Edit']
		List<WebElement> links = driver.findElements(searchEditRoleLocator);
		while (links.size() == 0) {
			// click Next
			icon_nextpage.click();
			// look for matches
			links = driver.findElements(searchEditRoleLocator);
		}

		// match found, click it
		log.info("Role found");
		links.get(0).click();

		Thread.sleep(2000);
		chkbox_reports_permission.click();// unselected "reports" persmission for this role
		log.info("unselected REPORTS permissions");
		btn_updaterole.click();
		log.info("UPDATE button selected");

	}

	@FindBy(xpath = "//div[text()='Delete Role']//following::div[contains(text(),'delete this Role?')]")
	private WebElement msg_confirm_deleterole;

	@FindBy(xpath = "//div[text()='Delete Role']//following::input[contains(@id,'Button3')]")
	private WebElement btn_yes_confirm_deleterole;

	@FindBy(xpath = "//span[contains(@id,'lblError')]")
	private WebElement msg_role_deletion;

	public void searchRoleAndDelete() throws Exception {
		String search_role_data = PropertyUtils.readProperty("search_role_fordeletion");

		By searchDeleteRoleLocator = By.xpath(
				"//td[text()='" + search_role_data + "']//following-sibling::td//a[@title='Delete Selected Role']");
		// td[text()='IT User']//following-sibling::td//a[@title='Delete Selected Role']

		List<WebElement> links = driver.findElements(searchDeleteRoleLocator);
		while (links.size() == 0) {
			// click Next
			icon_nextpage.click();
			// look for matches
			links = driver.findElements(searchDeleteRoleLocator);
		}

		// match found, click it
		log.info("Role found");
		links.get(0).click();
		log.info("Delete icon for selected role is clicked");

		log.info("Role deletion confirmation msg is: " + msg_confirm_deleterole.getText());
		Thread.sleep(1000);

		btn_yes_confirm_deleterole.click();

		Thread.sleep(1000);

		if (msg_role_deletion.getText().equals("Role deleted successfully"))
			log.info("Role deleted successfully");
		if (msg_role_deletion.getText().equals("Role already assigned to user(s).Role cannot be deleted"))
			log.info("Role cannot be deleted as it is already assigned to some user(s)");

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot,
					new File("target/screenshots/" + search_role_data + "-RoleDeletionSuccessMsg.jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	@FindBy(xpath = "//a[@title='Edit']")
	private WebElement edit_role;

	public void editRoleModifyPermission() {

	}

	@FindBy(xpath = "//a[@id='CourseManagement' and @title='Courses']")
	private WebElement link_courses;

	@FindBy(xpath = "//a[@id='StudentManagement' and @title='Students']")
	private WebElement link_students;

	@FindBy(xpath = "//a[@id='collegebatches' and @title='Manage Batches']")
	private WebElement link_managebatches;

	@FindBy(xpath = "//a[@id='Reports' and @title='Reports']")
	private WebElement link_reports;

	@FindBy(xpath = "//a[@id='Settings' and @title='Settings']")
	private WebElement link_settings;

	@FindBy(xpath = "//a[@id='Profile' and @title='Profile']")
	private WebElement link_profile;

	// Exam dashbaord
	@FindBy(xpath = "//a[@id='AssessmentDashboard' and @title='Exam Dashboard']")
	private WebElement link_examdashboard;

	public void clickExamDashboardLink() {
		link_examdashboard.click();
	}

	// Event dashboard->Filter options
	@FindBy(xpath = "//input[@title='Filters']")
	private WebElement btn_filter_events;

	@FindBy(xpath = "//input[@id='txtStartDate']")
	private WebElement filterby_event_startdate;

	@FindBy(xpath = "//input[@id='txtEndDate']")
	private WebElement filterby_event_enddate;

	@FindBy(xpath = "//input[contains(@id,'txtEvent')]")
	private WebElement filterby_event_name;

	@FindBy(xpath = "//input[contains(@id,'txtCourse')]")
	private WebElement filterby_event_course;

	@FindBy(xpath = "//input[contains(@id,'txtTitle')]")
	private WebElement filterby_event_title;

	@FindBy(xpath = "//input[contains(@id,'txtInstructor')]")
	private WebElement filterby_event_instructor;

	@FindBy(xpath = "//select[@id='drpVertical']")
	private WebElement filterby_event_piaccount;

	@FindBy(xpath = "//input[@value='Apply Filters']")
	private WebElement btn_event_apply_filter;

	@FindBy(xpath = "//input[@value='Clear']")
	private WebElement btn_event_filter_clear;

	@FindBy(xpath = "//input[@value='Setup']")
	private WebElement btn_setup;

	@FindBy(xpath = "//a[text()='»']")
	private WebElement icon_setup_nextpage;

	public void examdashClickFilterBtn() {
		btn_filter_events.click();
		log.info("In Exam dashboard page, Filter button is clicked");
	}

	// I am using examdash_ in methods for exam dashboard related methods
	public void examdashFilterbyEventAndCourse() throws Exception {

		filterby_event_startdate.clear();
		log.info("Event start date made blank");
		filterby_event_enddate.clear();
		log.info("Event End date made blank");

		String search_eventname_forsetup_data = PropertyUtils.readProperty("search_eventname_forsetup");
		filterby_event_name.sendKeys(search_eventname_forsetup_data);

		String search_eventcourse_forsetup_data = PropertyUtils.readProperty("search_eventcourse_forsetup");
		filterby_event_course.sendKeys(search_eventcourse_forsetup_data);
		log.info("Added events name for filteration");

		btn_event_apply_filter.click();
		log.info("APPLY FILTER button clicked");
		Thread.sleep(4000);
		btn_setup.click();
		log.info("we are now in event setup page");

	}

	public void examdashFilterbyNewlyCreatedEventAndCourse() throws Exception {

		filterby_event_startdate.clear();
		log.info("Event start date made blank");
		filterby_event_enddate.clear();
		log.info("Event End date made blank");

		log.info("event name to be searched is: " + eventname);
		filterby_event_name.sendKeys(eventname);
		log.info("event name added for filteration");

		filterby_event_course.sendKeys(coursename);
		log.info("course name added for filteration");
		btn_event_apply_filter.click();

		log.info("APPLY FILTER button clicked");
		Thread.sleep(2000);
		btn_setup.click();
		log.info("Events Setup button clicked");

	}

	public void examdashFilterbyEventAndCourse(String ename, String cname) throws Exception {

		filterby_event_startdate.clear();
		log.info("Event start date made blank");
		filterby_event_enddate.clear();
		log.info("Event End date made blank");

		filterby_event_name.sendKeys(ename);
		filterby_event_course.sendKeys(cname);

		btn_event_apply_filter.click();
		Thread.sleep(2000);
		btn_setup.click();
		log.info("we are now in event setup page");

	}

	@FindBy(xpath = "//label[@id='lblMessageSuccess']")
	private WebElement msg_examstarted;

	@FindBy(xpath = "//input[@id='btnCloseMessagePopup']")
	private WebElement btn_close_examstarted_msg;

	public void setupSearchUserAndStartExam() throws Exception {
		String search_eventuser_insetup_startexam_data = PropertyUtils
				.readProperty("search_eventuser_insetup_startexam");

		By searchregistrantLocator = By.xpath("//td[text()='" + search_eventuser_insetup_startexam_data
				+ "']//following-sibling::td//input[@value='Start']");
		// td[text()='betayesuser6@mw.com']//following-sibling::td//input[@value='Start']

		List<WebElement> links = driver.findElements(searchregistrantLocator);
		while (links.size() == 0) {
			// click Next
			icon_setup_nextpage.click();
			// look for matches
			links = driver.findElements(searchregistrantLocator);
		}
	}

	public void setupRecentlyCreatedUserAndStartExam() throws Exception {// this method is for end to end flow
		By searchregistrantLocator = By
				.xpath("//td[@title='" + registrant + "']//following-sibling::td//input[@value='Start']");
		//td[@title='betayesuser6@mw.com']//following-sibling::td//input[@value='Start']

		List<WebElement> links = driver.findElements(searchregistrantLocator);
		while (links.size() == 0) {
			// click Next
			icon_setup_nextpage.click();
			// look for matches
			links = driver.findElements(searchregistrantLocator);
		}

		// match found, click it
		log.info("Registrants found");
		links.get(0).click();
		log.info("setup button of required registrant is clicked");
		Thread.sleep(1000);
		log.info("Exam started msg appeared as : " + msg_examstarted.getText());

		btn_close_examstarted_msg.click();

		// td[4]//*[text()='Build']//parent::td//following-sibling::td//input[@value='Setup'
		// and @class='btn btn-primary setupNo']

	}

	public void setupSearchUserAndStartExam(String eventusersetup) throws Exception {

		By searchregistrantLocator = By
				.xpath("//td[text()='" + eventusersetup + "']//following-sibling::td//input[@value='Start']");
		// td[text()='betayesuser6@mw.com']//following-sibling::td//input[@value='Start']

		List<WebElement> links = driver.findElements(searchregistrantLocator);
		while (links.size() == 0) {
			// click Next
			icon_setup_nextpage.click();
			// look for matches
			links = driver.findElements(searchregistrantLocator);
		}

	}

	@FindBy(xpath = "//input[@name='password1']")
	private WebElement set_password;

	@FindBy(xpath = "//input[@name='password2']")
	private WebElement set_confirmpassword;

	@FindBy(xpath = "//*[@id='chkTermsConditions']")
	private WebElement chkbox_TandC;

	@FindBy(xpath = "//*[@id='btnSubmit']")
	private WebElement btn_setpassword;

	@FindBy(xpath = "//*[@id='alertDecription']")
	private WebElement msg_alert;

	@FindBy(xpath = "//input[@id='btn_Ok']")
	private WebElement btn_loginnow;

	public void copyactivationSearchUser() throws Exception {
		String search_eventuser_insetup_activateuser_data = PropertyUtils
				.readProperty("search_eventuser_insetup_activateuser");

		By searchuserLocator = By.xpath("//td[@title='" + search_eventuser_insetup_activateuser_data
				+ "']//following-sibling::td//a[@title='Copy Activation Link']");

		// td[@title='allcourseuser6@9aug.com']//following-sibling::td//a[@title='Copy
		// Activation Link']

		List<WebElement> links = driver.findElements(searchuserLocator);
		while (links.size() == 0) {
			// click Next
			icon_setup_nextpage.click();
			// look for matches
			links = driver.findElements(searchuserLocator);
		}

		// match found, click it
		log.info("Registrants found");
		links.get(0).click();
		String my_clipboard = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null)
				.getTransferData(DataFlavor.stringFlavor);

		log.info("activation url is :" + my_clipboard);
		log.info("registrants activation link is copied");

		oldTab = driver.getWindowHandle();// considering that there is only one tab opened in that point.--added on 10th
											// jan 2023

		// code below to open new browser tab
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		tabs.remove(oldTab);// --added on 10th jan 2023
		driver.switchTo().window(tabs.get(0));// changed from (1) to (0)--added on 10th jan 2023
		// code below to paste copied url from clipboard to new browser tab
		driver.get(my_clipboard);
		Thread.sleep(2000);

		String setPassword_data = PropertyUtils.readProperty("setPassword");
		set_password.sendKeys(setPassword_data);
		set_confirmpassword.sendKeys(setPassword_data);
		log.info("Both the password is added");
		chkbox_TandC.click();
		log.info("TERMS & CONDITION checkbox checked");
		Thread.sleep(1000);
		btn_setpassword.click();
		log.info("SET PASSWORD button clciked");
		Thread.sleep(2000);
		log.info("msg is: " + msg_alert.getText());
		btn_loginnow.click();
		Thread.sleep(2000);

	}

	public void ActivateRecentlyCreatedUser() throws Exception {

		By searchuserLocator = By
				.xpath("//td[@title='" + registrant + "']//following-sibling::td//a[@title='Copy Activation Link']");

		// td[@title='allcourseuser6@9aug.com']//following-sibling::td//a[@title='Copy
		// Activation Link']

		driver.findElement(searchuserLocator).click();

		String my_clipboard = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null)
				.getTransferData(DataFlavor.stringFlavor);

		log.info("activation url is :" + my_clipboard);
		log.info("registrants activation link is copied");

		oldTab = driver.getWindowHandle();// considering that there is only one tab opened in that point.--added on 10th
											// jan 2023

		// code below to open new browser tab

		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		tabs.remove(oldTab);// --added on 10th jan 2023
		driver.switchTo().window(tabs.get(0));// changed from (1) to (0)--added on 10th jan 2023

		// code below to paste copied url from clipboard to new browser tab
		driver.get(my_clipboard);
		Thread.sleep(2000);

		String setPassword_data = PropertyUtils.readProperty("setPassword");
		set_password.sendKeys(setPassword_data);
		set_confirmpassword.sendKeys(setPassword_data);
		log.info("Both the password is added");
		Thread.sleep(1000);
		chkbox_TandC.click();
		log.info("TERMS & CONDITION checkbox checked");
		Thread.sleep(1000);
		btn_setpassword.click();
		log.info("SET PASSWORD button clciked");
		Thread.sleep(4000);
		log.info("msg is: " + msg_alert.getText());
		btn_loginnow.click();
		Thread.sleep(2000);

	}

	@FindBy(xpath = "//div[@class='modal-body']")
	private WebElement pspmsg_alert;

	
	  @FindBy(xpath = "//button[text()=' Login Now ']") 
	  private WebElement btn_psploginnow;
	 

	public void ActivateRecentlyCreatedPSPUser() throws Exception {

		By searchuserLocator = By
				.xpath("//td[@title='" + registrant + "']//following-sibling::td//a[@title='Copy Activation Link']");

		// td[@title='allcourseuser6@9aug.com']//following-sibling::td//a[@title='Copy
		// Activation Link']

		driver.findElement(searchuserLocator).click();

		String my_clipboard = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null)
				.getTransferData(DataFlavor.stringFlavor);

		log.info("activation url is :" + my_clipboard);
		log.info("registrants activation link is copied");

		oldTab = driver.getWindowHandle();// considering that there is only one tab opened in that point.--added on 10th
											// jan 2023

		// code below to open new browser tab

		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		tabs.remove(oldTab);// --added on 10th jan 2023
		driver.switchTo().window(tabs.get(0));// changed from (1) to (0)--added on 10th jan 2023

		// code below to paste copied url from clipboard to new browser tab
		driver.get(my_clipboard);
		Thread.sleep(2000);

		String setPassword_data = PropertyUtils.readProperty("setPassword");
		set_password.sendKeys(setPassword_data);
		set_confirmpassword.sendKeys(setPassword_data);
		log.info("Both the password is added");
		Thread.sleep(1000);
		chkbox_TandC.click();
		log.info("TERMS & CONDITION checkbox checked");
		Thread.sleep(1000);
		btn_setpassword.click();
		log.info("SET PASSWORD button clciked");
		Thread.sleep(2000);
		log.info("msg is: " + pspmsg_alert.getText());
		btn_psploginnow.click();
		Thread.sleep(2000);

	}

	@FindBy(xpath = "//a[@id='QBank' and @title='Question bank']")
	private WebElement link_questionbank;

	@FindBy(xpath = "//a[@title='Logout']")
	private WebElement link_logout;

	public void pmicadminLogout() throws Exception {
		link_logout.click();
		Thread.sleep(2000);
		Assert.assertEquals(driver.getTitle(), "Welcome to Learning Management System");
		log.info("user logout pmic successfully");

	}

}
