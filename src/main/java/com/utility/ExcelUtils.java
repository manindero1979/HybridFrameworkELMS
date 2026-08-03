package com.utility;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.models.Credentials;

public class ExcelUtils {

    public static Credentials getCredentials(String application, String role) throws Exception {
        String excelFile = PropertyUtils.readProperty("excelFile");
        String excelPath = System.getProperty("user.dir")
                + "/src/test/resources/" + excelFile;

        FileInputStream fis = new FileInputStream(excelPath);

        Workbook workbook = new HSSFWorkbook(fis);

        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);

            String app = row.getCell(0).getStringCellValue().trim();
            String userRole = row.getCell(1).getStringCellValue().trim();

            if (app.equalsIgnoreCase(application)
                    && userRole.equalsIgnoreCase(role)) {

                String username = row.getCell(2).getStringCellValue().trim();
                String password = row.getCell(3).getStringCellValue().trim();

                workbook.close();
                fis.close();

                return new Credentials(username, password);
            }
        }

        workbook.close();
        fis.close();

        throw new Exception("Credentials not found.");
    }
}