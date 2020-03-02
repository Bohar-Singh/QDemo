package org.test.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.test.utilities.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SearchCorporateTest extends TestBase{
	
	@BeforeSuite
	public void setUp() throws IOException
	{
		init();
		TestBase.setUpExtent();
	}

	@BeforeMethod
	public void browserLaunch()
	{
		launchBrowser();
		reports.createTest("Search Corporate");
	}
	
	@Test
	public static void searchByName() throws Exception
	{
		
		    ArrayList<String> data = getRowData("TestData", 5);
		   
		    //System.out.println(corName);
		    
		    getWebElement("CompID").sendKeys(data.get(1));
 		    getWebElement("UserName").sendKeys(data.get(2));
			getWebElement("pass").sendKeys(data.get(3));
			getWebElement("loginBtn").click();
			Thread.sleep(2000);
			
			 TestBase.frames("login");
			 TestBase.frames("leftbar");

			TestBase.click("CorpMangeMent");
			TestBase.click("SearchCorpLink");
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			 TestBase.frames("login");
			/*TestBase.frames("main");
			//TestBase.frames("frame[2]");
*/			driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='mainfrmset']/frame[2]")));

		    TestBase.frames("frm2");
		    driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		  
			TestBase.inputData("CorpName", data.get(4));
			TestBase.click("CorSearchSubmBtn");
			TestBase.verifyTableDataByColum();
	}
	
	@Test
	public static void searchByAccNo() throws Exception
	{
		    ArrayList<String> data = getRowData("TestData", 6);
		    
		    getWebElement("CompID").sendKeys(data.get(1));
		    getWebElement("UserName").sendKeys(data.get(2));
			getWebElement("pass").sendKeys(data.get(3));
			getWebElement("loginBtn").click();
			Thread.sleep(2000);
			TestBase.frames("login");
			TestBase.frames("leftbar");
			
			TestBase.click("CorpMangeMent");
			TestBase.click("SearchCorpLink");
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			TestBase.frames("login");
			driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='mainfrmset']/frame[2]")));
			 TestBase.frames("frm2");
			 driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
			
			TestBase.inputData("CorpAccNo", data.get(4));
			
			TestBase.click("CorSearchSubmBtn");
			
			
	}
	
	@AfterMethod()
	public void ShutBrowser()
	{
		repoStatus();
		reports.flush();
		driver.close();
	}

}
