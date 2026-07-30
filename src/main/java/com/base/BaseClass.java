package com.base;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.utility.PropertyUtils;

public class BaseClass {
	public static WebDriver driver=null;
	public static ExtentReports report;
	public static ExtentSparkReporter sparc=null;
	public static ExtentTest test=null;
	public String pmictab;
	public String odtab;	
	public static Logger log=Logger.getLogger(BaseClass.class);//log4g initialization
	public static String orderid;
	public static String invoiceid;
	public static String oldTab;//--added on 10th jan 2023
	
	public static String psp2tab;
	
	public  static ArrayList<String> tabs;//--added on 10th jan 2023
	public  static ArrayList<String> newtab;
	
	
	//private ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	
	//common variables required for one complete workflow is defined here
	public static String eventname;
	public static String coursename;
	public static String registrant;
	public static String newBiller_email;

	
	public static String updatedorderaccount;
	public void intialization() throws Exception {
		String brow_name = PropertyUtils.readProperty("browser");
		if (brow_name.equals("chrome")) {
			log.info("log: chrome browser launched");
			 //System.setProperty("WebDriver.chrome.driver", "chromedriver.exe");
			 
				 
			driver = new ChromeDriver();
			
	
			}

		if (brow_name.equals("firefox")) {
			System.setProperty("WebDriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		
		  driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		  driver.manage().timeouts().pageLoadTimeout(150, TimeUnit.SECONDS);
		 
		driver.get(PropertyUtils.readProperty("elmsurl"));
		}
	
	public void intializationpmic() throws Exception {
		String brow_name = PropertyUtils.readProperty("browser");
		//pmictab = driver.getWindowHandle();
		if (brow_name.equals("chrome")) {
			System.setProperty("webdriver.chrome.silentOutput","true");// setting up property to suppress the warning*******to avoid renderer error*******added on 27th march 23

			
			log.info("log: chrome browser launched");
			 System.setProperty("WebDriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		}

		if (brow_name.equals("firefox")) {
			System.setProperty("WebDriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(PropertyUtils.readProperty("pmicurl"));
		}
	
	public void waitForLoaderToDisappear_old() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

	    wait.until(ExpectedConditions.invisibilityOfElementLocated(
	            By.cssSelector("div.loading-mask")));
	}
	public void waitForLoaderToDisappear() {

	    By loader = By.cssSelector("div.loading-mask");

	    List<WebElement> loaders = driver.findElements(loader);

	    if (!loaders.isEmpty()) {

	        new WebDriverWait(driver, Duration.ofSeconds(15))
	                .until(ExpectedConditions.invisibilityOfElementLocated(loader));
	    }
	}
	
	public void intializationpsp() throws Exception {
		String brow_name = PropertyUtils.readProperty("browser");
		if (brow_name.equals("chrome")) {
			log.info("log: chrome browser launched");
			 System.setProperty("WebDriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		}

		if (brow_name.equals("firefox")) {
			System.setProperty("WebDriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(PropertyUtils.readProperty("pspurl"));
		}
	

	
	public void intializationondemand() throws Exception {
		String brow_name = PropertyUtils.readProperty("browser");
		//pmictab = driver.getWindowHandle();
		if (brow_name.equals("chrome")) {
			log.info("log: chrome browser launched");
			 System.setProperty("WebDriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		}

		if (brow_name.equals("firefox")) {
			System.setProperty("WebDriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(PropertyUtils.readProperty("ondemandurl"));
		}
	
	@SuppressWarnings("deprecation")
	public void intializationcart() throws Exception {
		String brow_name = PropertyUtils.readProperty("browser");
		//pmictab = driver.getWindowHandle();
		if (brow_name.equals("chrome")) {
			log.info("log: chrome browser launched");
			 System.setProperty("WebDriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		}

		if (brow_name.equals("firefox")) {
			System.setProperty("WebDriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(PropertyUtils.readProperty("carturl"));
		}

//below method to initialize first 2 reports
	public void reportInit() {

	    report = new ExtentReports();

	    sparc = new ExtentSparkReporter(
	            System.getProperty("user.dir") + "/target/ExtentReport.html");

	    // Report Title
	    sparc.config().setDocumentTitle("Automation Test Report");

	    // Report Name
	    sparc.config().setReportName("Hybrid Framework Execution");

	    report.attachReporter(sparc);

	    // System Information
	    report.setSystemInfo("Tester", "Maninder Oberoi");
	    report.setSystemInfo("Framework", "Selenium + TestNG");
	    report.setSystemInfo("Browser", "Chrome");
	    report.setSystemInfo("OS", System.getProperty("os.name"));
	    report.setSystemInfo("Java Version", System.getProperty("java.version"));
	    report.setSystemInfo("Browser", "Chrome");
	}

	

}
