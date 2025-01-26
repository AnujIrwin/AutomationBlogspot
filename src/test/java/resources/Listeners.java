package resources;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseTest.BaseTest;

public class Listeners extends BaseTest implements ITestListener{
	ExtentReports extent = createExtentReport();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
//		test.log(Status.PASS,result.getMethod().getMethodName()+" got passed" );
		extentTest.get().log(Status.PASS,result.getMethod().getMethodName()+" got passed" );
		
		String filePath = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		String filePath2 = null;
		
		try {
			
			filePath2 = takeScreenshot( driver,"Passed_"+result.getMethod().getMethodName()+"_"+LocalDateTime.now());
		}
		catch(Exception e){
			e.printStackTrace();		
		}
		try {
//			test.addScreenCaptureFromPath(filePath2);
			extentTest.get().addScreenCaptureFromPath(filePath2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
//		test.log(Status.FAIL,result.getMethod().getMethodName()+" got Failed" );
		extentTest.get().log(Status.FAIL,result.getMethod().getMethodName()+" got Failed" );
//		test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());

		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		String filePath = null;
		
		try {
			
			filePath = takeScreenshot( driver,"Failed"+result.getMethod().getMethodName()+"_"+LocalDateTime.now());
		}
		catch(Exception e){
			e.printStackTrace();		
		}
		try {
//			test.addScreenCaptureFromPath(filePath);
			extentTest.get().addScreenCaptureFromPath(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.SKIP,result.getMethod().getMethodName()+" got Skipped" );
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.WARNING,result.getMethod().getMethodName()+" failed with within success percenatge" );
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
