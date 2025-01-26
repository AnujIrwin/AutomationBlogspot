package com.readExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLibrary {

	XSSFWorkbook wb;
	XSSFSheet sheet;
	
	public ExcelLibrary() throws IOException {
		String filePath = "C:\\Users\\anujs\\OneDrive\\Documents\\NewWorkSpaceSelenium\\New\\practice\\TestData\\TestDataFile.xlsx";
		File file = new File(filePath);
		FileInputStream fis  = new FileInputStream(file);
		 wb = new XSSFWorkbook(fis);
	}
	
	public String readData(String sheetName, int row, int column) {
		
		sheet = wb.getSheet(sheetName);
		return sheet.getRow(row).getCell(column).getStringCellValue();
	}
}
