package baseTest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {

	public WebDriver driver;
	

	public WebDriver getWebDriver() throws IOException {
		
		Properties prop = readConfigFile();
		String browser =prop.getProperty("browser");
		String webSite = prop.getProperty("website");
		
		if (browser.equalsIgnoreCase("chrome")) {
			driver =  new ChromeDriver();
			driver.get(webSite);
			
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			driver =  new FirefoxDriver();
			driver.get(webSite);
		}
		
		driver.manage().window().maximize();	
		return driver;
		
	}
	
	public static Properties readConfigFile() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		Properties prop = new Properties();
		prop.load(fis);
		prop.get(prop);
		return prop;
		
	}
	
	public List<HashMap<String, String>> getJsonDatatoMap(String jsonFilePath) throws IOException {	
		String jsonData = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);		
		ObjectMapper mapper = new ObjectMapper();		
		List<HashMap<String,String>> map = mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>(){});
		return map;	
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	public ExtentReports createExtentReport() {

		ExtentHtmlReporter reporter  = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\report.html");
		reporter.config().setReportName("AutomationBlogSpotReport");
		reporter.config().setDocumentTitle("AutomationofWebSite");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Anuj");
		extent.setSystemInfo("OperatingSystem", "Windows11");
		extent.setSystemInfo("Environment", "SE27");		
		return extent;	
	}
	
	public String takeScreenshot(WebDriver driver,String pathtoSave) throws IOException {
		String newFilePath = pathtoSave.replace('.', '_').replace(':', '_');
		String filepath = System.getProperty("user.dir")+"\\screenshots\\"+newFilePath+".png";		
		System.out.println(filepath);
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(filepath));
		return filepath;
	}
}
