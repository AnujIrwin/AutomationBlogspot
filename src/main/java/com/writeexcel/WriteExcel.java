package com.writeexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

	public void writeExcel(String sheetName, String toWrite, int rowNum, int columnNum)
			throws InvalidFormatException, IOException {

		String excelPath = "C:\\Users\\anujs\\OneDrive\\Documents\\NewWorkSpaceSelenium\\New\\practice\\TestData\\TestDataFile.xlsx";
		File file = new File(excelPath);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		sheet.createRow(rowNum).createCell(columnNum).setCellValue(toWrite);
		FileOutputStream fos = new FileOutputStream(new File(excelPath));
		wb.write(fos);
		wb.close();

	}
}