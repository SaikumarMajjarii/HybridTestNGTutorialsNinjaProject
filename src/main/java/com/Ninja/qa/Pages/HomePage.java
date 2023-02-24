package com.Ninja.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public WebDriver Driver;

	@FindBy(xpath = "//span[normalize-space()='My Account']")
	private WebElement MyAccount;
	
	@FindBy(xpath = "//a[contains(normalize-space(),'Login')]")
	private WebElement Login;
	
	public HomePage(WebDriver Driver)
	{	
		
		this.Driver = Driver ;
		PageFactory.initElements(Driver, this);
		
	}
	
	
	public void ClickOnMyAccount()
	{
		
		MyAccount.click();
		
	}
	
	public void ClickOnLogin()
	{
		
		Login.click();
		
	}
}
