package com.Ninja.QATestcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Ninja.qa.Base.BaseClass;
import com.Ninja.qa.Pages.HomePage;
import com.Ninja.qa.Pages.SearchPage;
import com.Ninja_qa.Utils_BaseTest.ResuableUtilities;

public class tC_TestScripts_SearchTestCasetest extends BaseClass {

	
	public SearchPage SP ;
	public tC_TestScripts_SearchTestCasetest() throws Exception {
		super();
		
	}
	
	//Driver
	public WebDriver Driver;

	@BeforeMethod()
	public void TakeMetoHomePage() throws Exception {

		Driver = IntializeDriver(GloablProp.getProperty("BroswerName"));
		HomePage HP = new HomePage(Driver);	
		SP = new SearchPage(Driver);
		HP.ClickOnMyAccount();
		HP.ClickOnLogin();


	}
	
	@Test(priority=1)
	public void Verify_Searchingwith_a_existing_Product_Name() {

		SP.EnterTextOnSearchBox("iMac");
		SP.ClickOnSearch();
		
		ResuableUtilities.ScrollUptoElement(By.xpath("//*[text()='iMac']"), Driver);
		
//		JavascriptExecutor JS = (JavascriptExecutor)Driver;	
//		WebElement Product  =  Driver.findElement(By.xpath("//*[text()='iMac']"));
//		
//		JS.executeScript("arguments[0].scrollIntoView(true);",Product);
			
		String Actual_Product = SP.Validate_Product();
	
		Assert.assertEquals(Actual_Product, "iMac",
				"Not getting the Success Msg for Existing Product");
	}
	
	@Test(priority = 2)
	public void Verify_Searchingwith_a_non_existing_Product_Name() {
		
		SP.EnterTextOnSearchBox("fitbit");
		SP.ClickOnSearch();

		WebDriverWait EW = new WebDriverWait(Driver, Duration.ofSeconds(15));

		EW.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@class='col-sm-12']/p/following-sibling::p")));
		String ErrorMsg = Driver.findElement(By.xpath("//*[@class='col-sm-12']/p/following-sibling::p")).getText();

		Assert.assertEquals(ErrorMsg, "There is no product that matches the search criteria.",
				"Not getting the Error Msg for Non Existing Product");

	}
	//Changed here 
	@Test(priority = 3)
	public void Verify_Searchingwithout_giving_Product_Name() {


		SP.ClickOnSearch();

		WebDriverWait EW = new WebDriverWait(Driver, Duration.ofSeconds(15));

		EW.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@class='col-sm-12']/p/following-sibling::p")));
		String ErrorMsg = Driver.findElement(By.xpath("//*[@class='col-sm-12']/p/following-sibling::p")).getText();

		Assert.assertEquals(ErrorMsg, "There is no product that matches the search criteria.",
				"Not getting the Error Msg for Non Existing Product");

	}

	@AfterMethod()
	public void TearDownCloseBrowsers()

	{
		Driver.quit();
	}
//There is no product that matches the search criteria.
}
