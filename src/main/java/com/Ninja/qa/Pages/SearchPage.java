package com.Ninja.qa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	public WebDriver Driver;
	
	
	@FindBy(xpath = "//*[@placeholder='Search']")
	private WebElement EnterText;


	@FindBy(xpath = "//*[starts-with(@class,'btn btn-default btn-lg')]")
	private WebElement SearchClick;

	@FindBy(xpath = "//*[text()='iMac']")
	private WebElement ValidateMac;
	
	public SearchPage(WebDriver Driver)
	{	
		
		this.Driver = Driver ;
		PageFactory.initElements(Driver, this);
		
	}
	
	
	public String Validate_Product()
	{
		String Actual_Product = ValidateMac.getText();
		return Actual_Product;
		
		
	}
	public void EnterTextOnSearchBox(String TexttoEntered)
	{
		EnterText.sendKeys(TexttoEntered);
		
	}

	
	public void ClickOnSearch()
	{
		SearchClick.click();
		
	}
}
