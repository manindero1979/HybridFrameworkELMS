package com.pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

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

public class odstudent_page extends BaseClass {

	private WebDriverWait wait;

	// constructor
	public odstudent_page(WebDriver driver) {

		this.driver = driver;
		//wait = new WebDriverWait(driver, 50, 50);
		WebDriverWait wait = new WebDriverWait(
				driver,
				Duration.ofSeconds(50)
				);
		//wait = new WebDriverWait(driver, Duration.ofSeconds(50)); 
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//p[text()='Foundations']//parent::div//following-sibling::div//a")
	private WebElement btn_startcourse;

	@FindBy(xpath = "//a[text()='Content ']")
	private WebElement tab_content_bookreader;

	@FindBy(xpath = "//input[@title='Next']")
	private WebElement icon_next_bookreader;

	@FindBy(xpath = "//div[contains(@class,'selected')]//descendant::input//preceding-sibling::span")
	private WebElement label_selectedtopic;

	@FindBy(xpath = "//div[@class='btnSelect']//following::iframe")
	private WebElement play_video;

	@FindBy(xpath = "//*[@id='mtt_p1f14331983_inner']")
	private WebElement btn_startexam;

	@FindBy(xpath = "//button[@title='Go to course']")
	private WebElement btn_gotocourse;

	public void clickGotToCourse() throws Exception {
		psp2tab = driver.getWindowHandle();


		btn_gotocourse.click();
		newtab = new ArrayList<String>(driver.getWindowHandles());
		log.info("go to course button clicked");
		Thread.sleep(8000);
		//newtab.remove(psp2tab);
		//log.info("removed psptab");
		//driver.switchTo().window((String) newtab.get(1));
		//driver.switchTo().window(newtab.get(0));
		//log.info("moved to new tab");
	}

	@FindBy(xpath = "//div[@class='BookReaderWrapper']//iframe[@id='TopIframe']")
	private WebElement framepath;

	@FindBy(xpath = "//div[@class='overview-content']")
	private WebElement coursedetailstext;

	public void attemptFODCourseViaPSP() throws Exception {
		log.info("I am inside attemptFODCourseViaPSPmethod" );
		Thread.sleep(3000);
		WebElement frame1 = driver.findElement(By.id("TopIframe"));
		driver.switchTo().frame(frame1);
		log.info("I am inside TopIframe" );
		Thread.sleep(5000);

		tab_content_bookreader.click();
		log.info("CONTENT tab selected in book reader");
		Thread.sleep(3000);
		log.info("Topic: " + label_selectedtopic.getText());

		for (int i = 1; i <= 40; i++) {
			if (label_selectedtopic.getText().equals("Certification Exam")) {
				log.info("I reached to last topic of the Foundation on demand course");
				break;
			} else {
				icon_next_bookreader.click();
				log.info("NEXT topic button is clicked");
				Thread.sleep(5000);
				log.info("Selected Topic: " + i + " is =" + label_selectedtopic.getText());
			}

		}
		driver.switchTo().defaultContent();

		// written this 6 line below code for replacing next 3 line code for optimizing
		// the code of switching between frames faster
		WebElement frame2 = driver.findElement(By.id("TopIframe"));
		driver.switchTo().frame(frame2);
		WebElement frame3 = driver.findElement(By.id("PageTypeHolderFrame"));
		driver.switchTo().frame(frame3);
		WebElement frame4 = driver.findElement(By.id("frmvideo"));
		driver.switchTo().frame(frame4);

		// driver.switchTo().frame("TopIframe");
		// driver.switchTo().frame("PageTypeHolderFrame");
		// driver.switchTo().frame("frmvideo");
		play_video.click();
		log.info("video played");

		WebElement iframe = driver.findElement(By.xpath("//div[@id='txt_11']//following::iframe[1]"));/// well done
		/// maninder
		driver.switchTo().frame(iframe);
		//WebDriverWait wait = new WebDriverWait(driver, 100);
		WebDriverWait wait = new WebDriverWait(
				driver,
				Duration.ofSeconds(100)
				);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100)); 

		wait.until(ExpectedConditions.elementToBeClickable(btn_startexam)).click();
		log.info("START EXAM button of certification exam clciked");

	}

	public void attemptFODCourse() throws Exception {

		btn_startcourse.click();
		log.info("Course START button clicked");
		Thread.sleep(4000);
		WebElement frame1 = driver.findElement(By.id("TopIframe"));
		driver.switchTo().frame(frame1);

		// driver.switchTo().frame("TopIframe");

		tab_content_bookreader.click();
		log.info("CONTENT tab selected in book reader");
		Thread.sleep(3000);
		log.info("Topic: " + label_selectedtopic.getText());

		for (int i = 1; i <= 40; i++) {
			if (label_selectedtopic.getText().equals("Certification Exam")) {
				log.info("I reached to last topic of the Foundation on demand course");
				break;
			} else {
				icon_next_bookreader.click();
				log.info("NEXT topic button is clicked");
				Thread.sleep(3000);
				log.info("Selected Topic: " + i + " is =" + label_selectedtopic.getText());
			}

		}
		driver.switchTo().defaultContent();

		// written this 6 line below code for replacing next 3 line code for optimizing
		// the code of switching between frames faster
		WebElement frame2 = driver.findElement(By.id("TopIframe"));
		driver.switchTo().frame(frame2);
		WebElement frame3 = driver.findElement(By.id("PageTypeHolderFrame"));
		driver.switchTo().frame(frame3);
		WebElement frame4 = driver.findElement(By.id("frmvideo"));
		driver.switchTo().frame(frame4);

		// driver.switchTo().frame("TopIframe");
		// driver.switchTo().frame("PageTypeHolderFrame");
		// driver.switchTo().frame("frmvideo");
		play_video.click();
		log.info("video played");

		WebElement iframe = driver.findElement(By.xpath("//div[@id='txt_11']//following::iframe[1]"));/// well done
		/// maninder
		driver.switchTo().frame(iframe);
		//WebDriverWait wait = new WebDriverWait(driver, 100);
		WebDriverWait wait = new WebDriverWait(
				driver,
				Duration.ofSeconds(100)
				);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100)); 
		wait.until(ExpectedConditions.elementToBeClickable(btn_startexam)).click();
		log.info("START EXAM button of certification exam clciked");

	}

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

	public void submitCertificationExam() throws Exception {
		log.info("I am inside submitCertificationExam method");

		driver.switchTo().defaultContent();
		log.info("swicthed into default frame");
		// code to switch between iframes faster
		WebElement frame1 = driver.findElement(By.id("IframeMCQContainer"));
		driver.switchTo().frame(frame1);

		// driver.switchTo().frame("IframeMCQContainer");
		log.info("swicthed into IframeMCQContainer frame");

		WebElement frame2 = driver.findElement(By.id("mcqFrameInner"));
		driver.switchTo().frame(frame2);
		// driver.switchTo().frame("mcqFrameInner");
		log.info("swicthed into mcqFrameInner frame");
		String NoOfQuestions_data = PropertyUtils.readProperty("odNoOfQuestions");
		Integer NoOfQuestions_data_int = Integer.parseInt(NoOfQuestions_data);// convert string valu of no. of questions
		// to int
		for (int i = 1; i <= NoOfQuestions_data_int; i++) {

			log.info("I am inside question-" + i);
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click()", answeroption_a);
			// Thread.sleep(1000);
			log.info("option A clicked");
			Thread.sleep(1000);
			btn_nextquestion.click();
		}
		driver.switchTo().defaultContent();

		WebElement frame3 = driver.findElement(By.id("IframeMCQContainer"));
		driver.switchTo().frame(frame3);

		// driver.switchTo().frame("IframeMCQContainer");

		log.info("exam submission alert msg is: " + alert_examsubmission.getText());
		ok_alert_examsubmission.click();
		log.info("Alert OK clciked");
		// Thread.sleep(2000);
		driver.switchTo().defaultContent();

	}

	@FindBy(xpath = "//div[@id='dashboardTabs']//li/a[text()='Results']")
	private WebElement tab_results;

	@FindBy(xpath = "//span[@id='btnClose']")
	private WebElement btn_home;

	public void certificationExamResult() throws Exception {

		WebElement frame1 = driver.findElement(By.id("IframeMCQContainer"));
		driver.switchTo().frame(frame1);
		WebElement frame2 = driver.findElement(By.id("mcqFrameInner"));
		driver.switchTo().frame(frame2);

		// driver.switchTo().frame("IframeMCQContainer");
		// driver.switchTo().frame("mcqFrameInner");
		log.info("Now we are inside examresult method");
		log.info("earned exam score: " + label_score_examresults.getText());
		log.info("earned exam status: " + label_status_examresults.getText());
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("target/screenshots/certificationexamresultpage.jpg"));
			log.info("screenshot of Exam result page captured ");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		Thread.sleep(2000);
		btn_exit_examresult.click();
		log.info("EXIT EXAM RESULT btn clicked");
		Thread.sleep(1000);

		driver.switchTo().defaultContent();

		WebElement frame3 = driver.findElement(By.id("TopIframe"));
		driver.switchTo().frame(frame3);

		// driver.switchTo().frame("TopIframe");
		tab_results.click();
		log.info("RESULTS tab is clicked");
		Thread.sleep(3000);

		File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot1, new File("target/screenshots/RESULTpage.jpg"));
			log.info("screenshot of Result page captured");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		btn_home.click();
		log.info("HOME btn clicked");
		driver.switchTo().defaultContent();
	}

}
