package org.test.scripts;

import java.io.IOException;
import java.util.ArrayList;

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
		reports.createTest("Search Corporate");
	}
	
	@Test
	public void searchClient() throws Exception
	{
		 ArrayList<String> data = getRowData("TestData", 6);
		   
		    //System.out.println(corName);
		    
		    getWebElement("CompID").sendKeys(data.get(1));
		    getWebElement("UserName").sendKeys(data.get(2));
			getWebElement("pass").sendKeys(data.get(3));
			getWebElement("loginBtn").click();
			Thread.sleep(2000);
			
			 TestBase.frames("login");
			 TestBase.frames("leftbar");
	}

}
