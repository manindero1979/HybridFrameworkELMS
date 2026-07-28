package com.pages;

import java.time.Duration;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.utility.PropertyUtils;

public class cart_page extends BaseClass {

	private WebDriverWait wait;
	public String oldtab;

	// constructor
	public cart_page(WebDriver driver) {

		this.driver = driver;
		//wait = new WebDriverWait(driver, 50, 50);
		//WebDriverWait wait = new WebDriverWait(
		//        driver,
		//        Duration.ofSeconds(50)
		//);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		PageFactory.initElements(driver, this);
	}

	// javascript for scrolling
	JavascriptExecutor js = (JavascriptExecutor) driver;

	public String cartregistrant1_email;

	String cartregistrant_fnamel_data;
	String cartregistrant_lnamel_data;

	//@FindBy(xpath = "//a[@text='View Info']")
	@FindBy(xpath = "//*[text()='Foundations On Demand']//following::div[@class='event__card--front__body__secondary__info'] //a[@class='view-info']")
	//private WebElement click_product_link;
	private WebElement click_viewinfofod_link;

	@FindBy(xpath = "//*[@class='course-info-details-wrap']//*[@class='course-info-cta']//child::*[@class='btn-add-to-cart addtocartsimple']")
	//private WebElement btn_register_fod;
	private WebElement btn_addtocart_fod;
	
	@FindBy(xpath = "//a[text()='Proceed to Register']")
	private WebElement btn_proceedtoregister_fod;

	
	
	@FindBy(xpath = "//div[@class='control qty']//select")
	private WebElement select_qty;

	@FindBy(xpath = "//button[@title='Register']")
	private WebElement btn_register;

	public void selectFODregistration() throws Exception {
		click_viewinfofod_link.click();
		log.info("view info link selected for fod course");
		Thread.sleep(4000);
		btn_addtocart_fod.click();
		log.info("in course details page add to cart button clicked");
		Thread.sleep(3000);
		btn_proceedtoregister_fod.click();
		log.info("in course details page proceed to register button clicked");
		Thread.sleep(3000);
		//btn_register.click();
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

	@FindBy(xpath = "//*[@placeholder='Phone/Mobile'][contains(@id,'1_home_phone')]")
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

	public void generateReg1Email() throws Exception {
		System.out.println("----------generate regustrant 1 email---------");
		String reg1fname = PropertyUtils.readProperty("odcartregistrant_fnamel");
		cartregistrant1_email = reg1fname + "-" + RandomStringUtils.randomNumeric(4) + "@flipick.com";

		log.info("Random registrant-1 email generated is: " + cartregistrant1_email);
		registrant = cartregistrant1_email;// this is for end to end flow
	}

	@FindBy(xpath = "//label[contains(@id,'1_US_Canada_message')]")
	private WebElement msg1_us_canada;

	public void setRegistrantONEInfo() throws Exception {
		reg1_email.clear();
		reg1_email.sendKeys(cartregistrant1_email);
		log.info("registrant1 email id added");

		reg1_firstname.clear();
		cartregistrant_fnamel_data = PropertyUtils.readProperty("odcartregistrant_fnamel");
		reg1_firstname.sendKeys(cartregistrant_fnamel_data);
		log.info("registrant1 first name added");
		Thread.sleep(1000);

		reg1_lastname.clear();
		cartregistrant_lnamel_data = PropertyUtils.readProperty("odcartregistrant_lname1");
		reg1_lastname.sendKeys(cartregistrant_lnamel_data);
		log.info("registrant1 lastname added");
		Thread.sleep(1000);

		reg1_jobtitle.clear();
		String cartregistrant_jtitle1_data = PropertyUtils.readProperty("odcartregistrant_jtitle1");
		reg1_jobtitle.sendKeys(cartregistrant_jtitle1_data);
		log.info("registrant1 job title added");
		Thread.sleep(1000);

		reg1_companyname.clear();
		String cartregistrant_company1_data = PropertyUtils.readProperty("odcartregistrant_company1");
		reg1_companyname.sendKeys(cartregistrant_company1_data);
		log.info("registrant1 company name added");
		Thread.sleep(1000);

		String cartregistrant_phoneoption1_data = PropertyUtils.readProperty("odcartregistrant_phoneoption1");
		Select mobileoption = new Select(reg1_drpdwn_phoneoption);
		mobileoption.selectByVisibleText(cartregistrant_phoneoption1_data);
		log.info("registrant1 phoneoption value selected as: " + cartregistrant_phoneoption1_data);
		Thread.sleep(3000);

		reg1_phone.clear();
		String cartregistrant_mobile1_data = PropertyUtils.readProperty("odcartregistrant_mobile1");
		reg1_phone.sendKeys(cartregistrant_mobile1_data);
		log.info("registrant1 phone data added");
		Thread.sleep(1000);

		reg1_address1.clear();
		String cartregistrant_address1_1_data = PropertyUtils.readProperty("odcartregistrant_address1_1");
		reg1_address1.sendKeys(cartregistrant_address1_1_data);
		log.info("registrant1 address1 added");
		Thread.sleep(1000);

		reg1_address2.clear();
		String cartregistrant_address1_2_data = PropertyUtils.readProperty("odcartregistrant_address1_2");
		reg1_address2.sendKeys(cartregistrant_address1_2_data);
		log.info("registrant1 address2 added");
		Thread.sleep(1000);

		reg1_city.clear();
		String cartregistrant_city1_data = PropertyUtils.readProperty("odcartregistrant_city1");
		reg1_city.sendKeys(cartregistrant_city1_data);
		log.info("registrant1 city added");
		Thread.sleep(1000);

		reg1_zipcode.clear();
		String cartregistrant_zipcode1_data = PropertyUtils.readProperty("odcartregistrant_zipcode1");
		reg1_zipcode.sendKeys(cartregistrant_zipcode1_data);
		log.info("registrant1 zipcode added");
		Thread.sleep(1000);

		String cartregistrant_country1_data = PropertyUtils.readProperty("odcartregistrant_country1");
		Select reg1country = new Select(reg1_country);
		reg1country.selectByVisibleText(cartregistrant_country1_data);
		log.info("registrant1 country selected");
		Thread.sleep(1000);

		String cartregistrant_state1_data = PropertyUtils.readProperty("odcartregistrant_state1");
		Select reg1state = new Select(reg1_state);
		reg1state.selectByVisibleText(cartregistrant_state1_data);
		log.info("registrant1 state selected");
		Thread.sleep(1000);
		try // add this try-catch so tht if elemtn not displayed then ELSE condition will
			// work
		{
			if (msg1_us_canada.isDisplayed()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", msg1_us_canada);

				File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				try {
					FileUtils.copyFile(screenshot,
							new File("target/screenshots/FirstRegistrant-" + cartregistrant_country1_data + "-"
									+ cartregistrant_phoneoption1_data + "-DisclaimerMsg.jpg"));
					log.info("screenshot captured");
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}

			}

		}

		catch (Exception e)

		{
			log.info("Disclaimer msg is not available for this product and registrant");
		}

	}

	@FindBy(xpath = "//div[@class='proceed-btn-wrap']//button[@id='btnCheckout' and @title='Proceed to Payment']")
	private WebElement btn_proceed_to_checkout;
	
	@FindBy(xpath = "//span[text()='Promo Code']")
	private WebElement label_promocode;

	public void clickProceedToCheckoutBtn() throws Exception {
		
		
		log.info("I am in clickProceedToCheckoutBtn method");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
				label_promocode);
		Thread.sleep(4000);//updated on 8 aug 23
		btn_proceed_to_checkout.click();
		//wait.until(ExpectedConditions.elementToBeClickable(btn_proceed_to_checkout)).click();

		
		/*
		 * JavascriptExecutor jse = (JavascriptExecutor) driver;
		 * jse.executeScript("arguments[0].click()", btn_proceed_to_checkout);
		 * btn_proceed_to_checkout.click();
		 */
		log.info("PROCEED TO CHECKOUT button clicked");
		
		
		
		
		
		
		/*log.info("I am in clickProceedToCheckoutBtn method");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", btn_proceed_to_checkout);

		wait.until(ExpectedConditions.elementToBeClickable(btn_proceed_to_checkout)).click();

		
		log.info("PROCEED TO CHECKOUT button clicked");*/
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

	public void setBillingInfo() throws Exception {
		log.info("I am in setBillingInfo method");
		billing_email.sendKeys(cartregistrant1_email);
		Thread.sleep(4000);	
		Select copyinfo = new Select(drpdwn_copy_info);

		String drpdownvaluetobeselected = cartregistrant_fnamel_data + " " + cartregistrant_lnamel_data + "("
				+ cartregistrant1_email + ")";
		copyinfo.selectByVisibleText(drpdownvaluetobeselected);
		log.info("biller info added");
		
	}

	@FindBy(xpath = "//div[@class='payment-method-title field choice']//label[@for='authnetcim']")
	private WebElement radio_selectbillingoption;
	
	@FindBy(xpath = "//span[text()='Credit Card']")
	private WebElement label_creditcard;
	
public void setCardDetails() throws Exception {
		
		log.info(" I am inside setCardDetails method");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
				label_creditcard); 
		 // Thread.sleep(5000);
		 // radio_selectbillingoption.click();
		  //log.info(" CC radio button selected");
		 
		String cardnumber_data = PropertyUtils.readProperty("cardnumber");
		//wait.until(ExpectedConditions.elementToBeClickable(radio_selectbillingoption)).click();
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(card_number)).sendKeys(cardnumber_data);
		
				
		
		//radio_selectbillingoption.click();
		//wait.until(ExpectedConditions.elementToBeClickable(card_number));
		//card_number.sendKeys(cardnumber_data);

		String exp_month_data = PropertyUtils.readProperty("exp_month");
		Select emonth = new Select(drpdwn_exp_month);
		emonth.selectByVisibleText(exp_month_data);
		log.info("month selected");
		Thread.sleep(1000);
		String exp_year_data = PropertyUtils.readProperty("exp_year");
		Select eyear = new Select(drpdwn_exp_year);
		eyear.selectByVisibleText(exp_year_data);
		log.info("year selected");
		Thread.sleep(1000);
		String cvv_data = PropertyUtils.readProperty("cvv");
		card_cvv.sendKeys(cvv_data);
		log.info("cvv added");
		Thread.sleep(1000);
	}

	public void oldsetCardDetails() throws Exception {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", radio_selectbillingoption);

		radio_selectbillingoption.click();
		// wait.until(ExpectedConditions.elementToBeClickable(card_number));
		String cardnumber_data = PropertyUtils.readProperty("cardnumber");
		card_number.sendKeys(cardnumber_data);
		log.info("card number added");

		String exp_month_data = PropertyUtils.readProperty("exp_month");
		Select emonth = new Select(drpdwn_exp_month);
		emonth.selectByVisibleText(exp_month_data);
		log.info("month selected");

		String exp_year_data = PropertyUtils.readProperty("exp_year");
		Select eyear = new Select(drpdwn_exp_year);
		eyear.selectByVisibleText(exp_year_data);
		log.info("year selected");

		String cvv_data = PropertyUtils.readProperty("cvv");
		card_cvv.sendKeys(cvv_data);
		log.info("cvv added");
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
		
		//wait.until(ExpectedConditions.elementToBeClickable(btn_placeorder)).click();

		
		  wait.until(ExpectedConditions.elementToBeClickable(btn_placeorder));
		 // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
		 // btn_placeorder);
		  
		  
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
		driver.switchTo().window(oldtab);

	}


	public void oldplaceOrder() throws Exception {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", chkbox_agree);

		// wait.until(ExpectedConditions.elementToBeClickable(chkbox_agree)).click();
		// chkbox_agree.click();
		log.info("I AGREE checkbox selected");

		// wait.until(ExpectedConditions.elementToBeClickable(btn_placeorder)).click();

		wait.until(ExpectedConditions.elementToBeClickable(btn_placeorder));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", btn_placeorder);

		btn_placeorder.click();

		log.info("PLACE ORDER button clicked");
		Thread.sleep(20000);

		if (msg_order_confirmation.isDisplayed()) {
			log.info("order confirmation msg is: " + msg_order_confirmation.getText());
			orderid = order_id.getText();
			log.info("order confirmed and order id is: " + orderid);
			// *********************String newbillercountry_data = PropertyUtils.
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File("target/screenshots/odfrontendordercreated-" + orderid + ".jpg"));
			log.info("screenshot captured");
		} else {
			log.info("order failed");
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshot, new File("target/screenshots/odfrontendorder_failed.jpg"));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		Thread.sleep(2000);
		// driver.switchTo().window(oldtab);
	}
	
	@FindBy(xpath = "//a[@id='product']")
	private WebElement product_tile;
	
	
	public void selectProductTile() throws Exception {
		product_tile.click();
		Thread.sleep(3000);
		log.info("I am at PRODUCT listing page");
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/" +  "ProductListingPage.jpg"));
			log.info("screenshot captured");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		}
	
	
	@FindBy(xpath = "//span[text()='Start here>>']")
	private WebElement starthere_link;
	
	public void selectStartHereLink() throws Exception {
		starthere_link.click();
		Thread.sleep(3000);
		log.info("I am at new banner-picker listing page");
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/" +  "NewBannerPickerListingPage.jpg"));
			log.info("screenshot captured");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		}
	
	@FindBy(xpath = "//a[text()='CONTACT SALES' and @class='desk']")
	private WebElement privatetraining_contactsales_button;
	
	public void selectPrivateTraining() throws Exception {
		oldtab = driver.getWindowHandle();
		privatetraining_contactsales_button.click();
		Thread.sleep(3000);
		log.info("I am at Private trainig linked page");
		ArrayList<?> newtab = new ArrayList<Object>(driver.getWindowHandles());
		newtab.remove(oldtab);
		// change focus to new tab
		driver.switchTo().window((String) newtab.get(0));
		
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/" +  "PrivateListing_redirectionPage.jpg"));
			log.info("screenshot captured");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	
	
	driver.switchTo().window(oldtab);
}
	
	
	@FindBy(xpath = "//h2[text()='Certified Product Manager']//parent::div//following-sibling::div//button//span[text()='GET STARTED']")
	private WebElement certifiedproductmanager_getstarted_link;
	
	public void certifiedProductManager_page() throws Exception {
		certifiedproductmanager_getstarted_link.click();
		Thread.sleep(3000);
		log.info("I am at Ceritified  Product Manager picker page");
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/" +  "CertifiedProductManagerPickerPage.jpg"));
			log.info("screenshot captured");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		}
	
	@FindBy(xpath = "//div[text()='Foundations On Demand']//ancestor::div[@class='event__card--front__header']//following-sibling::div[@class='event__card--front__body']//descendant::div//button[text()='ADD TO CART']")
	private WebElement fod_addtocart;
	
	public void fodAddToCart() throws Exception {
		fod_addtocart.click();
}

}

