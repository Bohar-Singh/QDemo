package org.test.scripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.test.utilities.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoTest extends TestBase {
	
	public static DataFormatter formatter= new DataFormatter();
	
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

	
	@DataProvider(name="Authentication")
    public static ArrayList<String> ReadVariant() throws IOException
    {
		
		ArrayList<String> data = new ArrayList<>();
		fis= new FileInputStream("D:\\TestProjects\\TestFramework\\excelfiles\\Demo.xlsx");
		XSSFWorkbook w= new XSSFWorkbook(fis);
		XSSFSheet reqdSheet = w.getSheet("TestData");
		short reqdCellIndex = reqdSheet.getRow(1).getLastCellNum();
		//System.out.println(reqdCellIndex);
		for(int i=0; i<reqdCellIndex; i++)
		{
			String reqdValue = reqdSheet.getRow(1).getCell(i).getStringCellValue();
			//System.out.println(reqdValue);
			data.add(reqdValue);
			
		}
		return data;
		
   
 
        
    }
	@Test(dataProvider="Authentication")
	public void validLogin() throws Exception
	{
		    ArrayList<String> data = getRowData("TestData", 1);
		
		    getWebElement("CompID").sendKeys(data.get(1));
		    getWebElement("UserName").sendKeys(data.get(2));
			getWebElement("pass").sendKeys(data.get(3));
			getWebElement("loginBtn").click();
			Thread.sleep(2000);
			
			
			
			
		
	}
}


