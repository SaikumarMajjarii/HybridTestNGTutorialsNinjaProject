package com.Ninja_qa.Utils_BaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class CustomExtentReports {

	public static ExtentReports GenerateExtentReport() throws IOException {

		File ReporterPath = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReport\\ResultReport.html");

		ExtentSparkReporter SparkReporter = new ExtentSparkReporter(ReporterPath);
		SparkReporter.config().setTheme(Theme.DARK);
		SparkReporter.config().setReportName("Tutorials Ninja Project Test Results");
		SparkReporter.config().setDocumentTitle("Automation Test Results");
		SparkReporter.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss");

		ExtentReports Extent = new ExtentReports();
		Extent.attachReporter(SparkReporter);

		FileInputStream FIS = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\Ninja\\qa\\Config\\Config.properties");

		Properties GP = new Properties();
		
		GP.load(FIS);

		Extent.setSystemInfo("Application URL", GP.getProperty("LoginUrl"));
		Extent.setSystemInfo("Browser Name", GP.getProperty("BroswerName"));
		Extent.setSystemInfo("Browser Name",System.getProperty("user.name"));
		Extent.setSystemInfo("Operating system",System.getProperty("os.name"));
		
		return Extent;

	}

}
