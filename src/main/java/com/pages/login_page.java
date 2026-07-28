package com.pages;
import com.models.Credentials;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.base.BaseClass;



public class login_page extends BaseClass{
	//constructor
		public login_page(WebDriver driver) {
			PageFactory.initElements(driver, this);//InitElement method will initialize all web element in one go
		}
	
		
	@FindBy(id="username")
	private WebElement uname;
	
	@FindBy(xpath="//input[@id='login']")
	private WebElement pass;
	
	@FindBy(xpath="//button[@class='action-login action-primary']")
	private WebElement btn_login;
	
	
	@FindBy(xpath="//a[@title='My Account']")
	private WebElement link_myaccount;
	
	@FindBy(xpath="//a[@id='adminbtnLogout']")
	private WebElement link_signout;
	
	@FindBy(xpath="//div[text()='You have logged out.']")
	private WebElement msg_logout;
	
	public void loginToelms(String eusername, String epassword) throws Exception {
		uname.clear();
		pass.clear();
		
		uname.sendKeys(eusername);
		pass.sendKeys(epassword);
		btn_login.click();
		Thread.sleep(3000);
	}
	
	
	@FindBy(xpath="//label[text()='Admin']")
	private WebElement admin_tab;
	
	@FindBy(xpath="//input[@id='emailInput']")
	private WebElement add_pmicemail;
	
	@FindBy(xpath="//input[@id='passwordInput']")
	private WebElement add_pmicpassword;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement btn_pmiclogin;
	
	@FindBy(xpath="//div[@class='box-header with-border']//following::span[contains(@id,'Heading')]")
	private WebElement pageheader_examdashbaord;
	
	
	public void loginToPmicAdmin(String pmicusername, String pmicpassword) throws Exception {
		//pmictab = driver.getWindowHandle();
		Thread.sleep(2000);
		//admin_tab.click();
		//Thread.sleep(1000);
		add_pmicemail.clear();
		add_pmicpassword.clear();
		
		add_pmicemail.sendKeys(pmicusername);
		add_pmicpassword.sendKeys(pmicpassword);
		btn_pmiclogin.click();
		Thread.sleep(4000);
		Assert.assertEquals(pageheader_examdashbaord.getText(), "Exam Dashboard");
		log.info("After login admin user landed in "+pageheader_examdashbaord.getText()+" page");
	}
	
	
	public void loginToPmicStudent(String regusername, String regpassword) throws Exception {
		
		log.info("I am inside 'loginToPmicStudent' method");
		Thread.sleep(5000);
		add_pmicemail.clear();
		add_pmicpassword.clear();
		
		add_pmicemail.sendKeys(regusername);
		log.info("student username added");
		add_pmicpassword.sendKeys(regpassword);
		log.info("student Password added");
		Thread.sleep(2000);
		btn_pmiclogin.click();
		log.info("Login button clicked");
		Thread.sleep(4000);
		Assert.assertEquals(driver.getTitle(),"Student Portal");
		log.info("After login student  landed in "+driver.getTitle()+" page");
	}
	
	@FindBy(xpath="//input[@name='Email']")
	private WebElement add_pspemail;
	
	@FindBy(xpath="//input[@name='Password']")
	private WebElement add_psppassword;
	
	@FindBy(xpath="//button[@value='Login']")
	private WebElement btn_psplogin;
	
	@FindBy(xpath="//p[text()='Welcome']")
	private WebElement header_welcome;
	
public void loginToPspStudent(String regusername, String regpassword) throws Exception {
		SoftAssert softAssert = new SoftAssert();
		Thread.sleep(3000);
		add_pspemail.clear();
		add_psppassword.clear();
		
		add_pspemail.sendKeys(regusername);
		add_psppassword.sendKeys(regpassword);
		Thread.sleep(2000);
		btn_psplogin.click();
		Thread.sleep(4000);
		softAssert.assertEquals(header_welcome.getText(),"Welcome");
		log.info("Student login successfull");
	}
	
	
@FindBy(xpath="//h1[text()='Welcome, ']")
private WebElement odheader_welcome;



public void loginToODAdmin(String pmicusername, String pmicpassword) throws Exception {
	log.info("we are inside loginToODAdmin method of login_page");
	//odtab = driver.getWindowHandle();
	//admin_tab.click();
	//log.info("ADMIN tab on login screen selected");
	Thread.sleep(1000);
	add_pmicemail.clear();
	add_pmicpassword.clear();
	
	add_pmicemail.sendKeys(pmicusername);
	add_pmicpassword.sendKeys(pmicpassword);
	btn_pmiclogin.click();
	log.info("LOGIN button clicked");
	Thread.sleep(4000);
	//Assert.assertEquals(pageheader_examdashbaord.getText(), "LMS");
	log.info("After login admin user landed in "+odheader_welcome.getText()+" page");
}
	

public void loginToODStudent(String regusername, String regpassword) throws Exception {
	
	log.info("I am inside 'loginTo On Demand Student' method");
	Thread.sleep(5000);
	add_pmicemail.clear();
	add_pmicpassword.clear();
	
	add_pmicemail.sendKeys(regusername);
	log.info("student username added");
	Thread.sleep(1000);
	add_pmicpassword.sendKeys(regpassword);
	log.info("student Password added");
	Thread.sleep(2000);
	btn_pmiclogin.click();
	log.info("Login button clicked");
	Thread.sleep(4000);
	//Assert.assertEquals(driver.getTitle(),"Library");
	//log.info("After login student  landed in "+driver.getTitle()+" page");
}

	public void logout() throws Exception {
		link_myaccount.click();
		Thread.sleep(2000);
		if(link_signout.isDisplayed())
		{link_signout.click();
		String msg=msg_logout.getText();
		System.out.println("Logout success msg is: "+msg);
		}
		else
		log.info(link_signout);
				
	}

	@FindBy(xpath="//a[@class='BottomLogout']")
	private WebElement link_logout;	
	
	public void pmiclogout() throws Exception {
		link_logout.click();
		Thread.sleep(1000);
		Assert.assertEquals(driver.getTitle(), "Welcome to Learning Management System");
		log.info("User logout successfully");
		
				
	}
}
