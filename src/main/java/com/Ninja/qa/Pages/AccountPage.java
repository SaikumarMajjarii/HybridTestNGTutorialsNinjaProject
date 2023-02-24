package com.Ninja.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	public WebDriver Driver;

	@FindBy(xpath = "//a[contains(normalize-space(),'Edit your account information')]")
	private WebElement SuccessfulLogin;

	public AccountPage(WebDriver Driver)
	{	
		
		this.Driver = Driver ;
		PageFactory.initElements(Driver, this);
		
	}
	
	
	public String CheckSuccessfulLogin()
	{
		
		String ActualSuccessMsgOnScreen = SuccessfulLogin.getText();
		
		return ActualSuccessMsgOnScreen;
		
	}
	
}
