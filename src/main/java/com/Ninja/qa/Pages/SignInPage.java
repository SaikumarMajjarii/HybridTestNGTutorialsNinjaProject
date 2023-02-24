package com.Ninja.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

	public WebDriver Driver;

	@FindBy(css = "input#input-email")
	private WebElement Email;
	
	
	@FindBy(css = "input#input-password")
	private WebElement Pasword;
	
	@FindBy(css = "input[value='Login']")
	private WebElement LoginButton;
	
	@FindBy(xpath = "//div[contains(@class,'alert alert-danger')]")
	private WebElement WarningError;

	
	public SignInPage(WebDriver Driver)
	{	
		
		this.Driver = Driver ;
		PageFactory.initElements(Driver, this);
		
	}

	
	public void EnterEmail(String EmailAddress)
	{
		
		Email.sendKeys(EmailAddress);
	}
	
	public void EnterPassword(String Password)
	{
		
		Pasword.sendKeys(Password);
	}
	
	public void ClickOnLogin()
	{
		
		LoginButton.click();
	}
	
	
	public String CheckWarningMessage_isDisplayed()
	{
		
		String WarningMsgOnScreen = WarningError.getText();
		return WarningMsgOnScreen;
	}
}
