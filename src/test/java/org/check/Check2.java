package org.check;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Check2 {
	
	@BeforeClass
	public void m5()
	{
		System.out.println("@BeforeClass 2nd class Called");
	}
	
	@BeforeTest
	public void m1()
	{
		System.out.println("@BeforeTest 2nd class Called");
	}
	
	@Test
	public void m2()
	{
		System.out.println("@Test 1st(2nd class) Called");
	}
	
	@Test
	public void m4()
	{
		System.out.println("@Test 2nd(2nd class) Called");
	}
	
	@BeforeMethod
	public void m3()
	{
		System.out.println("@BeforeMethod (2nd class) Called");
	}


}
