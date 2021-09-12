package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import selenium.base.methods.BaseSeleniumMethods;
import utils.ReadExcel;

public class BaseClass extends BaseSeleniumMethods {

	
	public String excelFilename;
	public static WebDriverWait wait;
	public static Properties prop;
	public static Actions builder;
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public String testName, description, author, category;
	
	
	@BeforeSuite
	public void startReport() {
		
		reporter = new ExtentHtmlReporter("./reports/report.html");
		reporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(reporter);

	}
	
	@BeforeClass
	public void testDetails() {
		
		test = extent.createTest(testName, description);
		test.assignAuthor(author);
		test.assignCategory(category);

	}
	
	
	@BeforeMethod
	public void preCondition() throws IOException {
		
		FileInputStream fis = new FileInputStream("./src/main/resources/files.properties");
		FileInputStream locaters = new FileInputStream("./src/main/resources/locaters.properties");
		prop = new Properties();
		prop.load(fis);
		prop.load(locaters);
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		driver = new ChromeDriver(option);
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		js = (JavascriptExecutor) driver;
		builder = new Actions(driver);
		parentWindow = driver.getWindowHandle();
		

	}
	
	@DataProvider(indices=0)
	public String[][] sendData() throws IOException {
		
		ReadExcel re = new ReadExcel();
		return re.excelRead(excelFilename);

	}
	
	
	
	@AfterMethod
	public void postCondition() {
		
		driver.close();

	}
	
	public void reportStep(String msg, String status) {
		
		if (status.equalsIgnoreCase("pass")) {
			test.pass(msg);
		} else if(status.equalsIgnoreCase("fail")){
			test.fail(msg);
		}
		

	}
	
	public void reportStepWithSnap(String msg, String status,long snapId) throws IOException {
		
		if (status.equalsIgnoreCase("pass")) {
			test.pass(msg,MediaEntityBuilder.createScreenCaptureFromPath(".././snaps/"+snapId+".jpg").build());
		} else if(status.equalsIgnoreCase("fail")){
			test.fail(msg,MediaEntityBuilder.createScreenCaptureFromPath(".././snaps/"+snapId+".jpg").build());
		}
		

	}
	
	public long takeSnap() {
		
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
		try {
			FileUtils.copyFile((((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE)), new File("./snaps/"+number+".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;

	}
	
	@AfterSuite
	public void stopReport() {
		
		extent.flush();

	}
	

}
