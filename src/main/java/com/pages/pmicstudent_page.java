package com.pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.BaseClass;
import com.utility.PropertyUtils;
public class pmicstudent_page extends BaseClass {

	private WebDriverWait wait;

	// constructor
	public pmicstudent_page(WebDriver driver) {

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
	@FindBy(xpath = "//a[@id='courses']")
	private WebElement link_mylibrary;

	@FindBy(xpath = "//a[@id='account']")
	private WebElement link_myprofile;

	@FindBy(xpath = "//a[@id='support']")
	private WebElement link_contactus;

	@FindBy(xpath = "//a[@id='logoutItem']")
	private WebElement link_logout;

	public String initialstring = "//p[@title='";
	public String middlestring = "']//parent::div//following-sibling::div";

	public String startexam = "//a[text()='Start Exam']";
	public String seeresult = "//a[text()='See Result']";
	public String inactivestartexam = "///label[text()='Start Exam']";
	public String resumeexam = "//a[text()='Resume Exam']";

	public void startCourseExam(String course) {

		// p[@title='Launch']//parent::div//following-sibling::div//a[text()='Start
		// Exam']

		// p[@title='Design']//parent::div//following-sibling::div//a[text()='See
		// Result']

		// p[@title='Business Strategy &
		// Design']//parent::div//following-sibling::div//label[text()='Start Exam']

		/*
		 * By locatecourse = By .xpath("//p[@title='" + course +
		 * "']//parent::div//following-sibling::div//a[text()='Start Exam']");
		 */

		WebElement coursestart = driver.findElement(By.xpath(initialstring + course + middlestring + startexam));
		coursestart.click();
	}
	
	public void startCourseExam() {//this method for end to end flow

	

		WebElement coursestart = driver.findElement(By.xpath(initialstring + coursename + middlestring + startexam));
		coursestart.click();
	}

	public void seeResultCourseExam(String course) {
		WebElement courseseeresult = driver.findElement(By.xpath(initialstring + course + middlestring + seeresult));
		courseseeresult.click();
	}
	
	public void seeResultCourseExam() {//this method for end to end flow
		WebElement courseseeresult = driver.findElement(By.xpath(initialstring + coursename + middlestring + seeresult));
		courseseeresult.click();
	}

	public void resumeCourseExam(String course) {

		WebElement courseresume = driver.findElement(By.xpath(initialstring + course + middlestring + resumeexam));
		courseresume.click();
	}
	
	public void resumeCourseExam() {//this method for end to end flow

		WebElement courseresume = driver.findElement(By.xpath(initialstring + coursename + middlestring + resumeexam));
		courseresume.click();
	}

	//@FindBy(xpath = "//span[@class='optionNo']//parent::label[@id='lbloptionA']//parent::li")
	@FindBy(xpath = "//span[text()='A.']//following-sibling::span")
	private WebElement answeroption_a;

	@FindBy(xpath = "//*[@class='optionNo']//parent::label[@id='lbloptionB']")
	private WebElement answeroption_b;

	@FindBy(xpath = "//*[@class='optionNo']//parent::label[@id='lbloptionC']")
	private WebElement answeroption_c;

	@FindBy(xpath = "//*[@class='optionNo']//parent::label[@id='lbloptionD']")
	private WebElement answeroption_d;

	@FindBy(xpath = "//*[@id='btnSaveAndNext' and @class='next-question']")
	private WebElement btn_nextquestion;

	@FindBy(xpath = "//*[@id='btnSkipCaption' and @class='btn-skip-cpation']")
	private WebElement btn_skipquestion;

	@FindBy(xpath = "//*[@id='btnsubmit' and @class='btn-submit-test']")
	private WebElement btn_submitexam;

	@FindBy(xpath = "//*[@id='alertDecription']")
	private WebElement alert_examsubmission;

	@FindBy(xpath = "//*[@id='btn_Ok']")
	private WebElement ok_alert_examsubmission;

	@FindBy(xpath = "//*[@id='btn_Cancel']")
	private WebElement cancel_alert_examsubmission;

	@FindBy(xpath = "//input[@id='reviewAnswerSheetCnt' and @class='submit btn-reviewAns btn btn-primary']")
	private WebElement btn_checkanswer_examresult;

	@FindBy(xpath = "//input[@id='btnCloseResultDashboard']")
	private WebElement btn_exit_examresult;

	@FindBy(xpath = "//div[@id='anscheknextbtn' and @class='btn-next']")
	private WebElement btn_next_checkanswer;

	@FindBy(xpath = "//div[@id='anscheckprevbtn' and @class='btn-prev']")
	private WebElement btn_prev_checkanswer;

	@FindBy(xpath = "//div[@id='btnResults' and @class='btn-test-result btn btn-primary']")
	private WebElement btn_examresults_checkanswer;

	@FindBy(xpath = "//*[@class='total-score-detail']")
	private WebElement label_score_examresults;

	@FindBy(xpath = "//*[@id='PspnPassFailStatusText']")
	private WebElement label_status_examresults;
	
	
	@FindBy(xpath = "//div[@class='Instructions-container' and @id='divquestionpassageholder']//following-sibling::div[@id='qustionRightpanel']//div[@id='divquestiontext']")
	private WebElement label_question;
	
	@FindBy(xpath = "//h2[@id='h2CourseName']")
	private WebElement label_coursename;
	
	//JavascriptExecutor jse = (JavascriptExecutor) driver;

	public void submitCourseExam() throws Exception {
		log.info("I am inside submitCourseExam method");
		
		WebElement frame1 = driver.findElement(By.id("IframeMCQContainer"));
		driver.switchTo().frame(frame1);
		log.info("swicthed into IframeMCQContainer frame");
		WebElement frame2 = driver.findElement(By.id("mcqFrameInner"));
		driver.switchTo().frame(frame2);
		log.info("swicthed into mcqFrameInner frame");
		
		/*
		 * driver.switchTo().frame("IframeMCQContainer");
		 * log.info("swicthed into IframeMCQContainer frame");
		 * driver.switchTo().frame("mcqFrameInner");
		 * log.info("swicthed into mcqFrameInner frame");
		 */
		
		String NoOfQuestions_data = PropertyUtils.readProperty("NoOfQuestions");
		Integer NoOfQuestions_data_int = Integer.parseInt(NoOfQuestions_data);//convert string valu of no. of questions to int
		for (int i = 1; i <= NoOfQuestions_data_int; i++) {
			
			log.info("I am inside question-"+i);
			  JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", answeroption_a);
		

			log.info("option A clicked");
			Thread.sleep(1000);
			btn_nextquestion.click();
		}
		driver.switchTo().defaultContent();
		
		WebElement frame3 = driver.findElement(By.id("IframeMCQContainer"));
		driver.switchTo().frame(frame3);
		log.info("swicthed into IframeMCQContainer frame");
		
		//driver.switchTo().frame("IframeMCQContainer");
	
		log.info("exam submission alert msg is: " + alert_examsubmission.getText());
		ok_alert_examsubmission.click();
		log.info("Alert OK clciked");
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		
	}

	public void examresult() {
		
		WebElement frame1 = driver.findElement(By.id("IframeMCQContainer"));
		driver.switchTo().frame(frame1);
		log.info("swicthed into IframeMCQContainer frame");
		
		WebElement frame2 = driver.findElement(By.id("mcqFrameInner"));
		driver.switchTo().frame(frame2);
		log.info("swicthed into mcqFrameInner frame");
		
		
		/*
		 * driver.switchTo().frame("IframeMCQContainer");
		 * driver.switchTo().frame("mcqFrameInner");
		 */
		log.info("Now we are inside examresult method");
		log.info("earned exam score: " + label_score_examresults.getText());
		log.info("earned exam status: " + label_status_examresults.getText());
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/examresultpage.jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		btn_exit_examresult.click();

	}

	public void checkResumeExam() {

	}
}
