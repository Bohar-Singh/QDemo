package org.test.scripts;

import java.io.IOException;

import org.test.utilities.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class SearchFlight extends TestBase {
	
	@BeforeSuite
	public void setUp() throws IOException
	{
		init();
	}

	@BeforeMethod
	public void browserLaunch()
	{
		launchBrowser();
	}

}
