package org.check;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Check {
	
	@BeforeTest
	public void m1()
	{
		System.out.println("@BeforeTest Called");
	}
	
	@Test
	public void m2()
	{
		System.out.println("@Test 1st Called");
	}
	
	@Test
	public void m4()
	{
		System.out.println("@Test 2nd Called");
	}
	
	@BeforeMethod
	public void m3()
	{
		System.out.println("@BeforeMethod Called");
	}

}
