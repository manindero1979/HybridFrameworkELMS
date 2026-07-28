package com.listener;

import org.testng.ITestContext;

import org.testng.ITestResult;
import org.testng.ITestListener;
import com.aventstack.extentreports.Status;
import com.base.BaseClass;
import com.utility.DriverUtils;


public class MyListener extends BaseClass implements ITestListener {

	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test case passed: " + result.getName());
	}

	public void onTestFailure_me(ITestResult result) {
		test.log(Status.FAIL, "Test case failed: " + result.getName());
		String path = DriverUtils.getScreenshot(result.getName());
		test.addScreenCaptureFromPath(path);
	}
	public void onTestFailure(ITestResult result) {

	    System.out.println("******** onTestFailure() executed ********");

	    test.log(Status.FAIL, "Test case failed: " + result.getName());

	    String path = DriverUtils.getScreenshot(result.getName());

	    // Convert Windows '\' to '/'
	    path = path.replace("\\", "/");

	    System.out.println("Screenshot Path: " + path);

	    test.addScreenCaptureFromPath(path);
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test case skipped: " + result.getName());

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {
		reportInit();
	}

	public void onFinish(ITestContext context) {
		report.flush();
	}

}
