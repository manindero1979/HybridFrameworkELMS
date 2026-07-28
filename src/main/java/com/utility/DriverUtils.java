package com.utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.base.BaseClass;

public class DriverUtils extends BaseClass {

	public static String getScreenshot_me(String name)  {// this screenshot code we will call in listener
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		//String path = System.getProperty("user.dir") + "/screenshots/" + name + ".jpg";
		String path = System.getProperty("user.dir")
		        + "/target/screenshots/" + name + ".png";
		File dest = new File(path);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public static String getScreenshot(String testName) {
	    System.out.println("******** getScreenshot() executed ********");
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File src = ts.getScreenshotAs(OutputType.FILE);

	    // Create target/screenshots folder if it doesn't exist
	    String folderPath = System.getProperty("user.dir") + "/target/screenshots";
	    File folder = new File(folderPath);

	    if (!folder.exists()) {
	        folder.mkdirs();
	    }

	    // Create a unique file name using current timestamp
	    String timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss")
	            .format(new java.util.Date());

	    String fileName = testName + "_" + timeStamp + ".png";

	    String path = folderPath + "/" + fileName;

	    File dest = new File(path);

	    try {
	        FileUtils.copyFile(src, dest);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return "screenshots/" + fileName;
	}
}
