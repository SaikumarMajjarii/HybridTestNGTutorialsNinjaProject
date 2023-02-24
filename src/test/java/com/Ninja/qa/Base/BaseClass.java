package com.Ninja.qa.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.Ninja_qa.Utils_BaseTest.ResuableUtilities;

public class BaseClass {

	public ChromeOptions options;
	public EdgeOptions EOptions;
	public WebDriver Driver;

	public Properties GloablProp;

	public BaseClass() throws Exception {

		FileInputStream FIS = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\Ninja\\qa\\Config\\Config.properties");

		GloablProp = new Properties();
		GloablProp.load(FIS);

	}

	public WebDriver IntializeDriver(String BrowserName) throws IOException {

		switch (BrowserName.toLowerCase()) {

		case "edge":

			EOptions = new EdgeOptions();
			EOptions.addArguments("start-maximized");
			Driver = new EdgeDriver(EOptions);
			break;

		case "chrome":

			options = new ChromeOptions();
			options.addArguments("start-maximized");
			Driver = new ChromeDriver(options);

			break;

		default:
			System.out.println("browser : " + BrowserName + " is invalid, Launching Chrome as browser of choice..");
			options = new ChromeOptions();
			options.addArguments("start-maximized");

			Driver = new ChromeDriver(options);

		}

		Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ResuableUtilities.IMPLICIT_WAIT_TIME));
		Driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ResuableUtilities.PAGE_LOAD_TIME));
		Driver.get(GloablProp.getProperty("LoginUrl"));

		return Driver;

	}


}
