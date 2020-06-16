package utility;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.Constant;
import utilities.ExcelUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

 



public class Autotest {
	WebDriver driver;
	public ExtentReports report;
	
	public static ExtentHtmlReporter htmlReports;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest parentTest;
	String fileName = System.getProperty("user.dir")+"/test-output/HTMLtestresults.html";
	
	@BeforeSuite
	public void setup() {
		htmlReports = new ExtentHtmlReporter(fileName);
		extent = new ExtentReports();
		extent.attachReporter(htmlReports);
		htmlReports.config().setReportName("Biba Shopping Website Automation Testing ");
		htmlReports.config().setTheme(Theme.STANDARD);
	    htmlReports.config().setTestViewChartLocation(ChartLocation.TOP);
	    htmlReports.config().setDocumentTitle("HtmlReportsTestResults");
	    
	}


	@BeforeTest
	public void sample() throws Exception {

		Reporter.log("Testcases Exceution Report For Website", true);
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.biba.in/"); 
		driver.manage().window().maximize();
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");


	}
	@Test(priority = 0)//this test verifies user is able to sign in the website or not
	public void signin() throws Exception {
		parentTest = extent.createTest("TC001--signin--This Testcase Verifies that User is able to Login with  Valid Credentials which are taken from excel sheet");
		parentTest.info("Sign in Test is started");
		WebElement sclick = driver.findElement(By.xpath("//*[@id=\"myaccount\"]/div/div/a[1]"));
		sclick.click();
		//Code for entering data from excel
		String sUserName = ExcelUtils.getCellData(1, 1);
		WebElement uname = driver.findElement(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_ctl00_ctl01_Login1_UserName\"]")); 
		if(uname.isDisplayed())
			uname.click();
		uname.sendKeys(sUserName);
		String sPassword = ExcelUtils.getCellData(1, 2);
		WebElement passwordTxtBox = driver.findElement(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_ctl00_ctl01_Login1_Password\"]"));  
		if(passwordTxtBox.isDisplayed())
			passwordTxtBox.sendKeys(sPassword);
		WebElement sub = driver.findElement(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_ctl00_ctl01_Login1_LoginImageButton\"]"));
		if(sub.isDisplayed())
		{
			sub.click();
			parentTest.info("-----Sign Testcase completed successfully-----");
			assertTrue(true);
		}
		else
		{
			assertTrue(false);
			parentTest.info("testcase is failed.....");
		}




}
  
  @Test(priority = 1)//this test verifies user able to search particular item from excel or not
  public void searchItem() throws Exception {
	  parentTest = extent.createTest("TC002--searchItem--This Testcase Verifies User is able to search the particular item which is taken from Excel sheet. ");
	  parentTest.info("searchItem Test started successfully");
	  String serch = ExcelUtils.getCellData(1, 3);
		  WebElement srch = driver.findElement(By.xpath("//*[@id=\"txtSearch\"]"));
		  WebElement ic =driver.findElement(By.xpath("//*[@id=\"btnSearch\"]"));
		  if(srch.isDisplayed())
		  {
				srch.click();
			   srch.sendKeys(serch);
			   ic.click();
			   parentTest.info("-----SearchItem Testcase completed successfully-----");
			   assertTrue(true);
		  }
		  else
		  {
			  assertTrue(false);
			  parentTest.info("testcase searchItem is failed.....");
		  }
			
  }
	
		  @Test(priority = 2)//this test verifies user able to add the item into the cart or not.
  public void addToCart() throws Exception{
	  parentTest = extent.createTest("TC003--addToCart--This Testcase Verifies User is able to add the searched item to Cart");
	  parentTest.info("addTocart Test started successfully");
	  WebElement ck =driver.findElement(By.xpath("//*[@id=\"3241494\"]/div[2]/div/div[2]/div/h2/a"));
	  ck.click();
	  WebElement size = driver.findElement(By.xpath("//*[@id=\"236992\"]/span"));
	  size.click();
	  WebElement cart = driver.findElement(By.xpath("//*[@id=\"pdmainDiv\"]/div/div/div[1]/div/div[2]/div[5]/div[2]/div[2]/div/div[3]/div[1]/span[2]/input"));
	  cart.click();
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	 WebElement hm= driver.findElement(By.xpath("//*[@id=\"QuickCart\"]/div[1]/a"));
	 if(hm.isDisplayed())
	 {
	 hm.click();
	  parentTest.info("-----addTocart Testcase completed successfully-----");
	   assertTrue(true);
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	 }
	 else
	 {
		 assertTrue(false);
		 parentTest.info("testcase addTocart is failed.....");
		 
	 }
	  }

 
  @Test(priority = 3)//this testcase veifies user able to logout.
  
  public void logout() throws Exception{
	  parentTest = extent.createTest("TC004--logout--This Testcase Verifies User is able to logout from website");
	  
	  WebElement lg = driver.findElement(By.xpath("//*[@id=\"lblusrn\"]"));
	  lg.click();
	  WebElement out = driver.findElement(By.xpath("//*[@id=\"lnkLogout1\"]"));
	  if(out.isDisplayed())
	  {
		  out.click();
	  	 driver.quit();
	  	  parentTest.info("-----logout Testcase completed successfully-----");
		   assertTrue(true);
	  }
	  else
	  {
		  assertTrue(false);
			 parentTest.info("testcase logout is failed.....");
	  }
	  
	  
  }
  
  @AfterTest
  public void tearDown()
  {
	  extent.flush();
	  System.out.println("Testing execution is done");
  }
  
}


