package com.utility;


import java.io.FileInputStream;


import org.testng.annotations.DataProvider;

import jxl.Sheet;
import jxl.Workbook;
//import org.apache.poi.ss.usermodel.Workbook;

public class ReadExcelTest {
  @DataProvider
  public Object[][] logindata() throws Exception {
	  System.out.println("we are inside read excel class-login method");
	  FileInputStream fis= new FileInputStream("src/test/resources/logindata_jbk.xls");
		Workbook wb = Workbook.getWorkbook(fis);
		Sheet sh = wb.getSheet("Sheet1");
		
		int rows = sh.getRows();
		int cols = sh.getColumns();
		String[][] logindata = new String[rows][cols];
		for (int i = 0; i < rows; i++)// rows
		{
			for (int j = 0; j < cols; j++) {// columns
				logindata[i][j] = sh.getCell(j, i).getContents();
			}
		}
		return logindata;
	  	  
  }
}
