package com.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.readExcel.ExcelLibrary;

public class ReadExcelTest {
	
	@Test
	public void readExcelTest() throws IOException {
		ExcelLibrary el = new ExcelLibrary();
		System.out.println(el.readData("Test", 0, 0));
	}

}
