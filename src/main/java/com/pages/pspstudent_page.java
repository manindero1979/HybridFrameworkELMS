package com.pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.BaseClass;
import com.utility.PropertyUtils;
import java.time.Duration;
public class pspstudent_page extends BaseClass{
	
	private WebDriverWait wait;

	// constructor
	public pspstudent_page(WebDriver driver) {

		this.driver = driver;
		//wait = new WebDriverWait(driver, 50, 50);
		wait = new WebDriverWait(driver, Duration.ofSeconds(50), Duration.ofMillis(50));
		PageFactory.initElements(driver, this);
	}

	
	// main links
		@FindBy(xpath = "//a[@id='courses']")
		private WebElement link_mylibrary;
		
		public String initialstring = "//*[@class='course-title' and text()=' ";
		public String secondstring = " ']//parent::div//parent::div//following-sibling::div//button[@title='";
		public String takeexamendstring = "Take Exam']";
				
		//*[@class='course-title' and text()=' Foundations ']//parent::div//parent::div//following-sibling::div//button[@title='Take Exam']
		
		public void takeCourseExam() {
		
			WebElement takecourseexam = driver.findElement(By.xpath(initialstring + coursename + secondstring+takeexamendstring));
			takecourseexam.click();
		}
		
			
		@FindBy(xpath = "//span[text()='A.']//following-sibling::span")
		private WebElement answeroption_a;

		@FindBy(xpath = "//*[@class='optionNo']//parent::label[@id='lbloptionB']")
		private WebElement answeroption_b;

		@FindBy(xpath = "//*[@class='optionNo']//parent::label[@id='lbloptionC']")
		private WebElement answeroption_c;

		@FindBy(xpath = "//*[@class='optionNo']//parent::label[@id='lbloptionD']")
		private WebElement answeroption_d;

		//@FindBy(xpath = "//*[@id='btnSaveAndNext' and @class='next-question']")
		@FindBy(xpath = "//span[text()='Next']")
		private WebElement btn_nextquestion;

		@FindBy(xpath = "//*[@id='btnSkipCaption' and @class='btn-skip-cpation']")
		private WebElement btn_skipquestion;

		@FindBy(xpath = "//*[@id='btnsubmit' and @class='btn-submit-test']")
		private WebElement btn_submitexam;

		//@FindBy(xpath = "//*[@id='alertDecription']")
		@FindBy(xpath = "//div[@class='modal-body']")
		private WebElement alert_examsubmission;

		//@FindBy(xpath = "//*[@id='btn_Ok']")
		@FindBy(xpath = "//button[text()=' Yes ']")
		private WebElement ok_alert_examsubmission;

		//@FindBy(xpath = "//*[@id='btn_Cancel']")
		@FindBy(xpath = "//button[text()=' No ']")
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
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		public void submitCourseExam() throws Exception {
			log.info("I am inside submitCourseExam method");
			
			
			
			String NoOfQuestions_data = PropertyUtils.readProperty("NoOfQuestions");
			Integer NoOfQuestions_data_int = Integer.parseInt(NoOfQuestions_data);//convert string valu of no. of questions to int
			for (int i = 1; i <= NoOfQuestions_data_int; i++) {
				
				log.info("I am inside question-"+i);
				
				jse.executeScript("arguments[0].click()", answeroption_a);
			

				log.info("option A clicked");
				Thread.sleep(1000);
				btn_nextquestion.click();
			}
			
			Thread.sleep(1000);
			log.info("exam submission alert msg is: " + alert_examsubmission.getText());
			waitForLoaderToDisappear();
			wait.until(ExpectedConditions.elementToBeClickable(	ok_alert_examsubmission)).click();
	
			log.info("Alert OK clciked");
			Thread.sleep(2000);
			
			
		}

		public void examresult() throws Exception {
		
			log.info("Now we are inside examresult method");
			Thread.sleep(2000);
			log.info("earned exam score: " + label_score_examresults.getText());
			log.info("earned exam status: " + label_status_examresults.getText());
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshot, new File("target/screenshots/examresultpage.jpg"));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			btn_exit_examresult.click();
			log.info("EXIT exam result page");
			Thread.sleep(2000);

		}

		public void checkResumeExam() {

		}
		
		
}
