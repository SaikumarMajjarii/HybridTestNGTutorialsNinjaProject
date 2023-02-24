package com.Ninja_qa.Utils_BaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResuableUtilities {
	
	static DataFormatter DF = new DataFormatter();
	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_LOAD_TIME = 10;

	public static String GenerateTimeStamp() {

		Date TimeStamp = new Date();

		String GenrateRandomEmail = TimeStamp.toString().replace(" ", "_").replace(":", "_");
		return GenrateRandomEmail;

	}

	public static Object[][] GetTestDataFromExcel(String InputSheet) throws Exception {

		FileInputStream FKS = new FileInputStream(
				"C:\\Users\\saiku\\Downloads\\Automation_Selenium_Journey\\InputTestData.xlsx");

		XSSFWorkbook WorkBook = new XSSFWorkbook(FKS);

		int TotalSheets = WorkBook.getNumberOfSheets();

		// one Way of dng Iteration
		for (int S = 0; S < TotalSheets; S++) {

			if (WorkBook.getSheetName(S).equalsIgnoreCase(InputSheet)) {

				XSSFSheet CurrentSheet = WorkBook.getSheetAt(S);

				int TotalRows = CurrentSheet.getPhysicalNumberOfRows();

				int TotalColumns = CurrentSheet.getRow(0).getLastCellNum();

				Object Data[][] = new Object[TotalRows-1][TotalColumns];

				for (int r = 0; r < TotalRows-1; r++) {

					XSSFRow CurrentRow = CurrentSheet.getRow(r + 1);

					for (int c = 0; c < TotalColumns; c++) {

						XSSFCell CurrentCell = CurrentRow.getCell(c);

						Data[r][c] = DF.formatCellValue(CurrentCell);

					}
				}

				return Data;

			}

		}
		return null;

	}
	
	
	
	public static void ScrollUptoElement(By To_View ,WebDriver Driver)
	{
			
		JavascriptExecutor JS = (JavascriptExecutor)Driver;	
		WebElement Element_to_beScrolled  = Driver.findElement(To_View);
		
		JS.executeScript("arguments[0].scrollIntoView(true);",Element_to_beScrolled);
	}
	
	
	public static String GetScreenshot(WebDriver Driver,String TestCaseName) throws IOException {
		
		
		TakesScreenshot TS = (TakesScreenshot) Driver;

		File Source = TS.getScreenshotAs(OutputType.FILE);

		String DesPath = System.getProperty("user.dir")+"\\Screenshots\\"+TestCaseName+".png";

		FileUtils.copyFile(Source, new File(DesPath));

		return System.getProperty("user.dir")+"\\Screenshots\\"+TestCaseName+".png";
	}

	
	
}
