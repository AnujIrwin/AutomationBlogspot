package com.readExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadExcel {

	@Test
	public void readExcelData() throws IOException {
		String filePath = "C:\\Users\\anujs\\OneDrive\\Documents\\NewWorkSpaceSelenium\\New\\practice\\TestData\\TestDataFile.xlsx";
		String fileName = "TestDataFile";
		String sheetName = "Test";
		File file = new File(filePath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		System.out.println(rowCount);
		for(int i = 0;i<=rowCount;i++) {
			XSSFRow row = sheet.getRow(i);
			int columCount =row.getLastCellNum();
			for(int j=0;j<columCount;j++) {
				XSSFCell cell = row.getCell(j);
				System.out.print(cell.getStringCellValue()+" | ");
				
			}
			System.out.println("");
			
		}
		wb.close();
	}
	
}
