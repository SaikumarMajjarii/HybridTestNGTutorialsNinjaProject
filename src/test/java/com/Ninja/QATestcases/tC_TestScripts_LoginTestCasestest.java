package com.Ninja.QATestcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Ninja.qa.Base.BaseClass;
import com.Ninja.qa.Pages.AccountPage;
import com.Ninja.qa.Pages.HomePage;
import com.Ninja.qa.Pages.SignInPage;
import com.Ninja_qa.Utils_BaseTest.ResuableUtilities;


//This is kumar Had done changes to the existing Login testvCases

public class tC_TestScripts_LoginTestCasestest extends BaseClass {


	public WebDriver Driver;
	public SignInPage SIP  ;
	
	public tC_TestScripts_LoginTestCasestest() throws Exception {
		super();
		
	}

	@BeforeMethod()
	public void TakeMetoHomePage() throws Exception {

		Driver = IntializeDriver(GloablProp.getProperty("BroswerName"));
		HomePage HP = new HomePage(Driver);	
		SIP = new SignInPage(Driver);
		
		HP.ClickOnMyAccount();
		HP.ClickOnLogin();

	}

	@AfterMethod()
	public void TearDownCloseBrowsers()

	{
		Driver.quit();
	}

	
	
	@Test(enabled = false, priority = 1,dataProvider="DriveDatatoTest")
	public void LoginUsingValidCredentials(String ValidEmail,String ValidPass) {

		//SignIn Page
//		 SIP = new SignInPage(Driver);	
		SIP.EnterEmail(ValidEmail);
		SIP.EnterPassword(ValidPass);
		SIP.ClickOnLogin();
	
		
		//Account Page
		AccountPage AP = new AccountPage(Driver);	
		String ActualMsg = AP.CheckSuccessfulLogin();
		Assert.assertEquals(ActualMsg, "Edit your account information","Login with Valid Credentials not Successfull");
		
		
//		Driver.findElement(By.cssSelector("input#input-email")).sendKeys(GloablProp.getProperty("ValidUserName"));
//		Driver.findElement(By.cssSelector("input#input-password")).sendKeys(GloablProp.getProperty("ValidPassword"));
		
	}
	

	@Test(priority = 2,enabled = true)
	public void LoginUsingInvalidCredentials() {
		
		
		SIP.EnterEmail("sai"+ResuableUtilities.GenerateTimeStamp()+"@gmail.com");
		SIP.EnterPassword("sai1234");
		SIP.ClickOnLogin();	
		String Warning_Msg =  SIP.CheckWarningMessage_isDisplayed();
		Assert.assertEquals(Warning_Msg, "Warning: No match for E-Mail Address and/or Password.","MistMatch in Warning Msg When Invalid Credentials Entered");

	}

	@Test(priority = 3,enabled = true)
	public void ExceededAllowedAttempts() {
		
		
		SIP.EnterEmail("sai"+ResuableUtilities.GenerateTimeStamp()+"@gmail.com");
		SIP.EnterPassword("sai12314");

		for (int i = 1; i <= 6; i++) {

			SIP.ClickOnLogin();

		}

		String Warning_Msg =  SIP.CheckWarningMessage_isDisplayed();
		Assert.assertEquals(Warning_Msg,
				"Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.","Account has been Exceeded No of Attempts Text Mismatch");

	}

	

	
	@DataProvider(name ="DriveDatatoTest")
	public Object[][] DriveDate() throws Exception
	{
		
//		Object[][] Data = {{"Saikumar.majjari@gmail.com","sai@123"},
//				{"Saikumar123.majjari@gmail.com","sai1233"},
//				{"Saikumar123.majjari@gmail.com","sai2345"}
//				};
//		return Data;
//		
		Object[][] Data = ResuableUtilities.GetTestDataFromExcel("InputData");
//		
		return Data;
	}
	
	
	
	
}
