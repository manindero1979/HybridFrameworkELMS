package com.pages;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
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

public class odadmin_page extends BaseClass {

	private WebDriverWait wait;
	
	public String pmictab;
	public String psptab;

	
	String oldpmictab;
	
	//public static ArrayList<String> tabs;
	
	
	// constructor
	public odadmin_page(WebDriver driver) {

		this.driver = driver;
		//wait = new WebDriverWait(driver, 50, 50);
		WebDriverWait wait = new WebDriverWait(
		        driver,
		        Duration.ofSeconds(50)
		);
		//wait = new WebDriverWait(driver, Duration.ofSeconds(50)); 
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h4[text()='Students']//ancestor::a")
	private WebElement link_students;

	@FindBy(xpath = "//input[@value='Filters']")
	private WebElement btn_filters;

	@FindBy(xpath = "//input[@id='txtEmail']")
	private WebElement search_emailid;

	@FindBy(xpath = "//input[contains(@name,'btnApplyFilter')]")
	private WebElement btn_applyfilters;

	@FindBy(xpath = "//a[@title='Copy Activation Link']")
	private WebElement link_copyactivation;

	public void searchStudent() throws Exception {
		log.info("we are inside searchStudent method");
		Thread.sleep(2000);
		link_students.click();
		Thread.sleep(2000);
		btn_filters.click();
		search_emailid.sendKeys(registrant);
		btn_applyfilters.click();
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
	
	

	public void ActivateRecentlyCreatedUser() throws Exception {
		
		Thread.sleep(2000);
		link_copyactivation.click();
		log.info("registrants activation link is copied");
		String my_clipboard = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null)
				.getTransferData(DataFlavor.stringFlavor);

		log.info("activation url is :" + my_clipboard);
		 oldTab = driver.getWindowHandle();// considering that there is only one tab opened in that point.--added on 10th jan 2023

		// code below to open new browser tab

		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		tabs.remove(oldTab);//--added on 10th jan 2023
		driver.switchTo().window(tabs.get(0));//changed from (1) to (0)--added on 10th jan 2023

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
		log.info("msg is: " + msg_alert.getText());
		btn_loginnow.click();
		Thread.sleep(2000);
	}
}
