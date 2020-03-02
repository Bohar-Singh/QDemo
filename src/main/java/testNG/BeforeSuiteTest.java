package testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BeforeSuiteTest {
	
	@BeforeSuite
	public void beforeSuite()
	{
		System.out.println("@BeforeSuite Called");
	}
	
	@BeforeTest
	public void beforeTest()
	{
		System.out.println("@BeforeTest Called");
	}
	
	@BeforeClass
	public void beforeClass()
	{
		System.out.println("@BeforeClass Called");
	}
	
	
	
	@BeforeMethod
	public void beforeMethod()
	{
		System.out.println("@BeforeMethod Called");
	}


	
	@Test
	public void method1()
	
	{
		System.out.println("Method1 Called");
	}
	
	@Test
	public void method2()
	{
		System.out.println("Method2 Called");
	}
	
	@AfterMethod
	public void afterMethod()
	{
		System.out.println("@AfterMethod Called");
	}
	
	@AfterClass
	public void afterClass()
	{
		System.out.println("@AfterClass Called");
	}
	
	@AfterTest
	public void afterTest()
	{
		System.out.println("@AfterTest Called");
	}
	
	@AfterSuite
	public void afterSuite()
	{
		System.out.println("@AfterSuite Called");
	}
}
