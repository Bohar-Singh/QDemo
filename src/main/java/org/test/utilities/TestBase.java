package org.test.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties p;
	public static FileInputStream fis;
	public static File file;
	public static Pattern pattern;
	public static boolean matcher;
	public static ExtentHtmlReporter reporter;
	public static ExtentReports reports;
	public static ExtentTest test;
	
    public static Actions a;
	
	public static void init() throws IOException
	{
		p=new Properties();
		 fis= new FileInputStream("D:\\TestProjects\\TestFramework\\src\\main\\resources\\org\\reqd\\file\\loc.properties");
		p.load(fis);
		
		fis= new FileInputStream("D:\\TestProjects\\TestFramework\\src\\main\\resources\\org\\reqd\\file\\config.properties");
		p.load(fis);
		
		/*fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\org\\reqd\\file\\login.properties");
			p.load(fis);*/
		
	 }
	
    public static void launchBrowser()
	{
		if(p.getProperty("browser").equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "D:\\TestProjects\\TestFramework\\src\\test\\resources\\drivers\\geckodriver.exe");
			driver= new FirefoxDriver();
		}
		
		else
		{
			System.setProperty("webdriver.chrome.driver", "D:\\TestProjects\\TestFramework\\src\\test\\resources\\drivers\\chromedriver.exe");
			driver= new ChromeDriver();
		}
		
		driver.get(p.getProperty("url"));
	}
    
    public static void setUpExtent()
    {
    	reporter= new ExtentHtmlReporter("D:\\TestProjects\\TestFramework\\reports\\getReport.html");
    	reports= new ExtentReports();
    	reports.attachReporter(reporter);
    }
    
    public static void repoStatus()
    {
       test.log(Status.PASS, "Passed");
    }
    
    public static WebElement getLocator(String loc) throws Exception
	{
		String locType= loc.split(":")[0];
		String locValue= loc.split(":")[1];
		
		/*System.out.println("Locator    : "+loc);*/
		
		if(locType.equalsIgnoreCase("xpath")){
			//System.out.println("Locator    : "+locType+"  Loc value "+locValue); 
			return driver.findElement(By.xpath(locValue));}
		else if(locType.equalsIgnoreCase("id"))
			return driver.findElement(By.id(locValue));
		else
			throw new Exception("Unknown Locator Type" + locValue);
		
	 }
	
	public static WebElement getWebElement(String loc) throws Exception {
		return getLocator(p.getProperty(loc));
	}
	
	public static void readData(String sheetName, String testCaseNAme ) throws IOException  
	{
		fis= new FileInputStream("D:\\TestProjects\\TestFramework\\excelfiles\\Demo.xlsx");
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		
		int ttlSheets = wb.getNumberOfSheets();
		for(int i=0; i<ttlSheets; i++)
		{
			if(wb.getSheetName(i).equalsIgnoreCase(sheetName))
			{
				XSSFSheet reqdsheet = wb.getSheetAt(i);
				Iterator<Row> rows = reqdsheet.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> cells = firstRow.cellIterator();
				int k=0;
				int column=0;
				while(cells.hasNext())
				{
					Cell value = cells.next();
					if(value.getStringCellValue().equalsIgnoreCase("TestCase"))
					{
						column=k;
					}
					k++;
				}
				System.out.println(column);
				
				while(rows.hasNext())
				{
					Row r = rows.next();
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseNAme))
					{
						Iterator<Cell> reqdTestCaseData = r.cellIterator();
						while(reqdTestCaseData.hasNext())
						{
							Cell readTestCaseValues = reqdTestCaseData.next();
							System.out.println(readTestCaseValues);
						}
						
					}
				}
				
				
				
				
			}
			
		}
		
		
	}
	
	public static void getColumnData(String SheetName, int columnIndex) throws IOException
	{
		fis= new FileInputStream("D:\\TestProjects\\TestFramework\\excelfiles\\Demo.xlsx");
		XSSFWorkbook w= new XSSFWorkbook(fis);
		XSSFSheet reqdSheet = w.getSheet(SheetName);
		int noOfRows = reqdSheet.getLastRowNum();
		//System.out.println(noOfRows);
		for(int i=0; i<=noOfRows; i++)
		{
			String reqdValue = reqdSheet.getRow(i).getCell(columnIndex).getStringCellValue();
			System.out.println(reqdValue);
		}
		
	 }
	
	/*
	 * 
	 */
	
	public static ArrayList<String> getRowData(String SheetName, int rowIndex) throws IOException
	{
		ArrayList<String> a= new ArrayList<>();
		fis= new FileInputStream("D:\\TestProjects\\TestFramework\\excelfiles\\Demo.xlsx");
		XSSFWorkbook w= new XSSFWorkbook(fis);
		XSSFSheet reqdSheet = w.getSheet(SheetName);
		short reqdCellIndex = reqdSheet.getRow(rowIndex).getLastCellNum();
		//System.out.println(reqdCellIndex);
		for(int i=0; i<reqdCellIndex; i++)
		{
			String reqdValue = reqdSheet.getRow(rowIndex).getCell(i).getStringCellValue();
			//System.out.println(reqdValue);
			a.add(reqdValue);
			
		}
		
		return a;
	 }
	
	public static void scrollByLocator(String loc) throws Exception
	{
		
		String jsCode= "arguments[0].scrollIntoView(true)";
		JavascriptExecutor je= (JavascriptExecutor)driver;
		je.executeScript(jsCode, getWebElement(loc));
	}
	
	public static void getScreenshotOnFailure(String result) throws IOException
	{
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("D:\\TestProjects\\TestFramework\\screenshots\\"+result+"Shot.png"));
	}
	
	public static void getScreenshot(String results) throws IOException
	{
	
	File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(srcFile, new File("D:\\TestProjects\\TestFramework\\screenshots\\"+results+"Shot.png"));
	}
	
	public static void click(String loc) throws Exception
	{
		getWebElement(loc).click();
	}
	
     public static void frames(String frame) throws Exception {
		
		driver.switchTo().frame(frame);
	
	}
	
	public static void inputData(String loc, String value) throws Exception
	{
		getWebElement(loc).sendKeys(value);
	}
	
	public static void moveToElement(String loc) throws Exception
	{
		a= new Actions(driver);
		a.moveToElement(getWebElement(loc));
	}
	
	public static void getPageLoadTime()
	{
		long start = System.currentTimeMillis();
		   driver.get("http://release117/backoffice/");
		   long finish = System.currentTimeMillis();
		   long totalTime = finish - start; 
		   System.out.println("Total Time for page load:" +totalTime);

	}
	
	public static void verifyTableDataByColum()
	{
		 String corName= "Ashu Corporate";
	
		 List<WebElement> firstColValue = driver.findElements(By.xpath("//td[@id='trGrid']//tr//td[1]"));
	 
		 for(WebElement value:firstColValue)
		 {
			  String dataFC = value.getText();
			  System.out.println(dataFC);
			  
			  if(dataFC.equals(corName))
			  {
				  System.out.println("Data matched");
			  }
			  else
			  {
				  System.out.println("Not Matched");
			  }
		 }
		 
		 
		 
		 
	}
	
	public static void gettRowData(String sheett, int roNum) throws IOException
	{
		fis= new FileInputStream("D:\\TestProjects\\TestFramework\\excelfiles\\Demo.xlsx");
		XSSFWorkbook xf= new XSSFWorkbook(fis);
		XSSFSheet reqddSheet = xf.getSheet(sheett);
		 short reqddRo = reqddSheet.getRow(roNum).getLastCellNum();
		
		
		for(int i=0; i<reqddRo; i++)
		{
			String val = reqddSheet.getRow(roNum).getCell(i).getStringCellValue();
			
			System.out.println(val);
		}
		
		
		
	}
	
	public static void gettCollData(String sheeeeet, int coll) throws IOException
	{
		fis= new FileInputStream("D:\\TestProjects\\TestFramework\\excelfiles\\Demo.xlsx");
		XSSFWorkbook cf= new XSSFWorkbook(fis);
		XSSFSheet reqdddSheet = cf.getSheet(sheeeeet);
		int totRow = reqdddSheet.getLastRowNum();
		
		for(int i=0; i<totRow; i++)
		{
			String val = reqdddSheet.getRow(i).getCell(coll).getStringCellValue();
			System.out.println(val);
		}
	}

	
	
	
	

}



