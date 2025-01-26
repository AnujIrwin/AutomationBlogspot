package com.testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageclasses.FirstPage;

public class AutomationBlogSpot extends BaseTest {

	WebDriver driver;
	@Test(dataProvider = "fillingDetails")
	public void firstTestCase(HashMap<String,String> map) throws IOException {
		driver = getWebDriver();
		FirstPage fp  = new FirstPage(driver);
		fp.fillDetails(map);	
		Assert.assertEquals(fp.getValue("name"), map.get("name"));
		Assert.assertEquals(fp.getValue("email"), map.get("email"));
		Assert.assertEquals(fp.getValue("number"), map.get("number"));
	}
	
	@DataProvider(name = "fillingDetails")
	public Object[][] fillDetailsData() throws IOException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdataJsons\\data1.json";
		List<HashMap<String,String>> map = getJsonDatatoMap(filePath);
		int size = map.size();
		return new Object[][] {{map.get(0)},{map.get(1)}};
}
	
}

