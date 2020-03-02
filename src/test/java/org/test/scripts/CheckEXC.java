package org.test.scripts;

import java.io.IOException;

import org.test.utilities.TestBase;
import org.testng.annotations.Test;

public class CheckEXC extends TestBase {
	
	@Test
	public void d2() throws IOException
	{
		TestBase.gettRowData("TestData", 1);
		TestBase.gettCollData("TestData", 0);
	}
	
	@Test
	public void d3() throws IOException
	{
		TestBase.gettRowData("TestData", 2);
		TestBase.gettCollData("TestData", 1);
	}
	
	

}
