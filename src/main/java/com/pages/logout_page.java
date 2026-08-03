package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;

public class logout_page extends BaseClass {

	@FindBy(xpath = "//a[@title='My Account']")
	private WebElement linkmyaccount;

	@FindBy(xpath = "//a[@id='adminbtnLogout']")
	private WebElement linklogout;

	@FindBy(xpath = "//div[text()='You have logged out.']")
	private WebElement msglogoutsuccess;
	private WebDriverWait wait;
	// constructor
	public logout_page(WebDriver driver) {
		 this.driver = driver;

		    this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		    PageFactory.initElements(driver, this);
		//PageFactory.initElements(driver, this);// InitElement method will initialize all web element in one go
	}

	// simple method to logout app
	@Test
	public void elmsLogoutApplication() throws Exception {
		linkmyaccount.click();
		Thread.sleep(2000);
		linklogout.click();
		Thread.sleep(5000);
		Assert.assertEquals(msglogoutsuccess.getText(), "You have logged out.");
		log.info("User logout ELM successfuly.");
		//driver.close();
		driver.quit();

	}

	@FindBy(xpath = "//a[@title='Logout']")
	private WebElement link_logout_pmicadmin;

	public void pmicadminLogoutApplication() throws Exception {
		link_logout_pmicadmin.click();
		Thread.sleep(2000);
		Assert.assertEquals(driver.getTitle(), "Welcome to Learning Management System");
		log.info("user logout pmic admin portal successfully");
		

	}

	//@FindBy(xpath = "//*[@id='logoutItem']//span[contains(@class,'logOut')]//following::span[contains(@class,'title')]")
	@FindBy(xpath = "//span[@class='nav-img icon-logOut']")	
	private WebElement link_logout_odstudent;

	public void pmicstudentLogoutApplication() throws Exception {
		log.info("I am inside pmicstudent logout method");
		Thread.sleep(3000);
		
		link_logout_odstudent.click();
		log.info("Logout link clicked");

		Thread.sleep(4000);
		Assert.assertEquals(driver.getTitle(), "Welcome to Learning Management System");
		log.info("user logout pmic studnet portal successfully");

	}

	//@FindBy(xpath = "//a[@title='LogOut']")
	@FindBy(xpath = "//span[text()='LogOut']")
	private WebElement link_logout_pspstudent;
	
	@FindBy(xpath = "//a[@id='lnkPSPPortal']")
	private WebElement pragmatic_logo;

	public void pspstudentLogoutApplication() throws Exception {
		log.info("I am inside pspstudent logout method");
		waitForLoaderToDisappear();
		wait.until(ExpectedConditions.elementToBeClickable(link_logout_pspstudent)).click();
		//link_logout_pspstudent.click();
		log.info("student portal Logout link clicked");
		Thread.sleep(2000);

	}

	
	public void odadminLogoutApplication() throws Exception {
		log.info("I am inside odadmin logout method");
		link_logout_pmicadmin.click();
		Thread.sleep(2000);
		Assert.assertEquals(driver.getTitle(), "Welcome to Learning Management System");
		log.info("user logout on demand admin portal successfully");
		

	}
	
	@FindBy(xpath = "//span[@class='nav-img icon-logOut']")
	private WebElement psp_logout;
	
	
	public void odstudentLogoutApplication() throws Exception {
		log.info("I am inside odstudent logout method");
		Thread.sleep(2000);
		//WebElement frame1 = driver.findElement(By.id("IframeMCQContainer"));
		//driver.switchTo().frame(frame1);
		//log.info("I am inside IframeMCQContainer" );
		Thread.sleep(2000);
		pragmatic_logo.click();
		log.info("pragmatic logo link clicked");

		Thread.sleep(2000);
		//Assert.assertEquals(driver.getTitle(), "Welcome to Learning Management System");
		//log.info("user logout on demand student portal successfully");
		
		

	}
	
}
