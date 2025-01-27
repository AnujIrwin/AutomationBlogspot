package pageclasses;

import java.util.HashMap;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.utils;

public class FirstPage extends utils {
	
	public WebDriver driver;
	
	public FirstPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "name")  WebElement name;
	@FindBy(id = "email") WebElement email;
	@FindBy(id = "phone") WebElement phone;
	@FindBy(id = "productTable") WebElement productTable;
	@FindBy(xpath = "//*[@id='productTable']//tbody//tr") List<WebElement> tableColumn;
	@FindBy(xpath = "//*[@id='productTable']//tbody//tr//td[2]") List<WebElement> nameColumn;
	@FindBy(xpath = "//*[@id='pagination']/li" ) List<WebElement> pages;
	@FindBy(xpath = "//div[@id='shadow_host']") WebElement shadowHost;
	
	public void fillDetails(HashMap<String,String> map) {
		name.sendKeys(map.get("name"));
		email.sendKeys(map.get("email"));
		phone.sendKeys(map.get("number"));
	}

	public String getValue(String objectName) {
		String fetchedValue = "";
		if(objectName.equalsIgnoreCase("name")) {
			fetchedValue = name.getDomProperty("value");
		}
		else if(objectName.equalsIgnoreCase("email")) {
			fetchedValue = email.getDomProperty("value");
		}
		else if(objectName.equalsIgnoreCase("number")) {
			fetchedValue = phone.getDomProperty("value");
		}
		return fetchedValue;
	}
	
	public String fetchValue(String productName) {
		waitforElementtoVisible(driver,productTable);
		String price = "";
		boolean flag = false;
		for(int i=0;i<pages.size();i++) {
			pages.get(i).click();
			for(int j=0;j<nameColumn.size();j++) {
				String currentText  = nameColumn.get(j).getText();
				if(currentText.equalsIgnoreCase(productName)) {
					WebElement priceElement = nameColumn.get(j).findElement(By.xpath("following-sibling::td"));
					price = priceElement.getText();			
					flag = true;
					break;
				}			
			}
			if(flag == true) {
				break;
			}
			
		}
		return price;
	}
	
	public void checkProduct(String productName) {
		waitforElementtoVisible(driver,productTable);
		boolean flag = false;
		for(int i=0;i<pages.size();i++) {
			pages.get(i).click();
			for(int j=0;j<nameColumn.size();j++) {
				String currentText  = nameColumn.get(j).getText();
				if(currentText.equalsIgnoreCase(productName)) {
					WebElement price =nameColumn.get(j).findElement(By.xpath("following-sibling::td"));
					WebElement checkbox = price.findElement(By.xpath("following-sibling::td//input"));
					checkbox.isDisplayed();
					waitforElementtoVisible(driver,checkbox);
					clickUsingJavaScript(driver,checkbox);
//					checkbox.click();
					flag = true;
					break;
				}			
			}
			if(flag == true) {
				break;
			}
			
		}
	}
	
	public  @Nullable String workWithShadowDom(String name) throws InterruptedException {
		shadowHost.click();
		SearchContext shadowRoot = shadowHost.getShadowRoot();
		Thread.sleep(2000);
		WebElement innerElement = shadowRoot.findElement(By.cssSelector("#shadow_content"));
		WebElement textBox = shadowRoot.findElement(By.cssSelector("input[type='text']"));
//		WebElement shadowRoot = getElementusindJavaScript(driver,shadowHost);
//		WebElement innerElement = shadowRoot.findElement(By.xpath("//span[@id='shadow_content']"));
		textBox.sendKeys(name);
		return textBox.getDomProperty("value");
	}
}
