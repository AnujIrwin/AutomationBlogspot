package utility;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class utils  {

	public void waitforElementtoVisible(WebDriver driver, WebElement element) {
		WebDriverWait wb = new WebDriverWait(driver,Duration.ofSeconds(10));
		wb.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void clickUsingJavaScript(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public WebElement getElementusindJavaScript(WebDriver driver,WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement shadowRoot = (WebElement)js.executeScript("return arguments[0].shadowRoot", element);
		return shadowRoot;
	}
	
}
