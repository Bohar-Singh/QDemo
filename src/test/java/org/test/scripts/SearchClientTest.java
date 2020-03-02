package org.test.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.test.utilities.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SearchClientTest extends TestBase {
	
	@BeforeSuite
	public void setup() throws IOException
	{
		init();
	}
	
	@BeforeMethod
	public void browserLaunch()
	{
		launchBrowser();
		//reports.createTest("Search Cor");
	}
	
	@Test
	public void searchClient() throws Exception
	{
		 ArrayList<String> data = getRowData("TestData", 7);
		   
		    //System.out.println(corName);
		    
		    getWebElement("CompID").sendKeys(data.get(1));
		    getWebElement("UserName").sendKeys(data.get(2));
			getWebElement("pass").sendKeys(data.get(3));
			getWebElement("loginBtn").click();
			Thread.sleep(2000);
			
			 TestBase.frames("login");
			 TestBase.frames("leftbar");
			 
			 TestBase.click("ClientMngmnt");
				TestBase.click("SearchClient");
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
				 TestBase.frames("login");
				 
				 driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='mainfrmset']/frame[2]")));
				 TestBase.frames("frm2");
			    
			    
			    TestBase.inputData("FNameSearchClient", data.get(4));
				TestBase.click("SearchBtnClient");
	}

}