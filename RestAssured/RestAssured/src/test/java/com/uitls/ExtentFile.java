package com.uitls;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;





public class ExtentFile {
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	public static ExtentHtmlReporter htmlReporter;
	public static String reportpath=System.getProperty("user.dir")+"\\Reports\\Booking_Apis_";

	public static void setExtent(){
		 
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			htmlReporter = new ExtentHtmlReporter(reportpath+timeStamp+".html");
			
			//htmlReporter.setAppendExisting(true);
		 	extent=new ExtentReports();
		 	extent.attachReporter(htmlReporter);
			extent.setSystemInfo("Host Name", "Pranay Windows");
			extent.setSystemInfo("User Name", "Pranay");
			extent.setSystemInfo("Environment", "QA");
	        htmlReporter.config().setDocumentTitle("ApiTest Demo Report");
	        htmlReporter.config().setReportName("Booking Test Execution Report");
	        htmlReporter.config().setTheme(Theme.DARK);

	}
	
	public static ExtentTest getTest(String name)
	{
		if(extent==null)
		{
			ExtentFile.setExtent();
			System.out.println("Here");
			return extent.createTest(name);
		}
			return extent.createTest(name);	

	}
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
/*	public static String captureScreenshot()
	{
		return null;
	}*/
	
/*	
public static void getResult(ITestResult result,WebDriver driver) throws Exception{
		if(result.getStatus() == ITestResult.FAILURE){
			
			extentTest.log(Status.FAIL, "Test Case Failed is "+result.getName());
			extentTest.log(Status.FAIL, "Test Case Failed is "+result.getThrowable());
			
			   String screenshotPath = ExtentFile.getScreenshot(driver, result.getName());
			   extentTest.log(Status.FAIL, (Markup) MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath));
		
		}
		else if(result.getStatus() == ITestResult.SKIP){
			extentTest.log(Status.SKIP, "Test Case Skipped is "+result.getName());
		}
		else if(result.getStatus() == ITestResult.SUCCESS){
			extentTest.log(Status.PASS, "Test Case passed is "+result.getName());
		}	
	
	} */
	public static void endReport() throws Exception{
		
		extent.flush();
	
	}	


}
