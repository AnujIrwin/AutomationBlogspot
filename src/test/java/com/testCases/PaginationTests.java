package com.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import pageclasses.FirstPage;

public class PaginationTests extends BaseTest{

	@Test
	public void getPriceofProduct() throws IOException {
		WebDriver driver = getWebDriver();
		FirstPage fp = new FirstPage(driver);
		String price = fp.fetchValue("Desktop Computer");
		Assert.assertEquals(price, "$2.99");
	}
	
	@Test
	public void checkProduct() throws IOException, InterruptedException {
		WebDriver driver = getWebDriver();
		FirstPage fp = new FirstPage(driver);
		fp.checkProduct("Desktop Computer");
		Thread.sleep(5000);
	}
	
	@Test
	public void shadowDom() throws IOException, InterruptedException {
		WebDriver driver = getWebDriver();
		FirstPage fp = new FirstPage(driver);
		String name = "Anuj";
		String value =  fp.workWithShadowDom(name);
		Assert.assertEquals(value, name);
		
	}
}
