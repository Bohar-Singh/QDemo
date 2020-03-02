package org.test.scripts;


import java.io.IOException;
import java.util.ArrayList;
import org.test.utilities.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
	
	static int count=0;
	
@BeforeSuite
public void setUp() throws IOException
{
	init();
	setUpExtent();
}

@BeforeMethod
public void browserLaunch()
{
	launchBrowser();
	reports.createTest("Login");
}

@Test()
public void validLogin() throws Exception
{
	    ArrayList<String> data = getRowData("TestData", 1);
	
	    getWebElement("CompID").sendKeys(data.get(1));
	    getWebElement("UserName").sendKeys(data.get(2));
		getWebElement("pass").sendKeys(data.get(3));
		getWebElement("loginBtn").click();
		Thread.sleep(2000);
		
		
		
		
	
}

@Test()
public void invalidLogin() throws Exception
{
	ArrayList<String> data1 = getRowData("TestData", 2);
	
    getWebElement("CompID").sendKeys(data1.get(1));
    getWebElement("UserName").sendKeys(data1.get(2));
	getWebElement("pass").sendKeys(data1.get(3));
	getWebElement("loginBtn").click();
	
	Thread.sleep(2000);
	
	

}

/*@Test(enabled=false)
public void forgetPassword() throws Exception
{
	    ArrayList<String> data = getRowData("TestData", 3);
	
	    getWebElement("CompID").sendKeys(data.get(1));
	    getWebElement("UserName").sendKeys(data.get(2));
		TestBase.click("ForgetPassLink");
		Thread.sleep(2000);
		TestBase.click("RetrvPassBtn");
		

}

@Test(priority=3)
public void changePassword() throws Exception
{
	    ArrayList<String> data = getRowData("TestData", 4);
	
	    getWebElement("CompID").sendKeys(data.get(1));
	    getWebElement("UserName").sendKeys(data.get(2));
		TestBase.click("ChangePassLink");
		Thread.sleep(2000);
		TestBase.inputData("OldPassword", data.get(3));
		TestBase.inputData("NewPassword", data.get(4));
		TestBase.inputData("ConfirmPassword", data.get(5));
		TestBase.click("SubmitBtn");
		Thread.sleep(3000);
		TestBase.getScreenshot("changePassword"+count++);

}
*/



@AfterMethod()
public void ShutBrowser()
{
	
	reports.flush();
	driver.close();
}


	
	
	
	
	
	
	
	
	
	
	
	
}
	
	
	