package com.Ninja.qa.ITTestListeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Ninja_qa.Utils_BaseTest.CustomExtentReports;
import com.Ninja_qa.Utils_BaseTest.ResuableUtilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListeners implements ITestListener  {

	public ExtentReports Extent ;
	public ExtentTest Test ;
	

	@Override
	public void onStart(ITestContext context) {
		
			try {
				Extent = CustomExtentReports.GenerateExtentReport();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
	}
	
	@Override
	public void onTestStart(ITestResult result){
	
	
		Test = Extent.createTest(result.getName());
		Test.log(Status.INFO,result.getName()+"Started Executing");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		WebDriver Driver = null;
		
		try {
			 Driver = (WebDriver) result.getTestClass().getRealClass().getField("Driver").get(result.getInstance());
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		String DesPath = null;
		try {
			
			DesPath = ResuableUtilities.GetScreenshot(Driver, result.getName());
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		Test.addScreenCaptureFromPath(DesPath);
		
		Test.log(Status.PASS,result.getName()+"Executed Successfully");

	}

	@Override
	public void onTestFailure(ITestResult result) {
			
		WebDriver Driver = null;
		
		try {
			 Driver = (WebDriver) result.getTestClass().getRealClass().getField("Driver").get(result.getInstance());
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		String DesPath = null;
		try {
			
			DesPath = ResuableUtilities.GetScreenshot(Driver, result.getName());
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		Test.addScreenCaptureFromPath(DesPath);
		Test.log(Status.INFO,result.getThrowable());
		Test.log(Status.FAIL,result.getName()+"is Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		Test.log(Status.INFO,result.getThrowable());
		Test.log(Status.SKIP,result.getName()+"is skipped");
		
	}


	@Override
	public void onFinish(ITestContext context) {
		
		
		Extent.flush();		
		try {
			Desktop.getDesktop().browse(new File(System.getProperty("user.dir")+"\\test-output\\ExtentReport\\ResultReport.html").toURI());
		
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
