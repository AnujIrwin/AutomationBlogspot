package com.testCases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.writeexcel.WriteExcel;

public class WriteToExcel {
	
	@Test
	public void writeToExcel() throws InvalidFormatException, IOException{
		WriteExcel objwriteExcel = new WriteExcel();
		objwriteExcel.writeExcel("Test", "This is written through Selenium", 12, 1);		
	}

}
