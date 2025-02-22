package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext)
	{
		/*
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String currentdatetimestamp = df.format(dt);
		*/
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());		//TimeStamp
		
		repName = "Test Report-"+ timestamp +".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);	// Specify the location of the file
		
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");	// Title of the Report
		sparkReporter.config().setReportName("Opencart Functional Testing");	// Name of the Report
		sparkReporter.config().setTheme(Theme.DARK);		// Sets theme of the report
		
		// Setting some data for the report most of them Hardcoded
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub-Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		
		//Setting data for the report (Dynamic)
		String os = testContext.getCurrentXmlTest().getParameter("os");		// Fetching Operating system details from XML parameters
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());	//
		test.assignCategory(result.getMethod().getGroups());		//to display groups in report
		test.log(Status.PASS, result.getName() + " got successfully executed");
		
	}
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());	//
		test.assignCategory(result.getMethod().getGroups());		//to display groups in report
		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test = extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+ " got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext testContext)
	{
		//Writes data into Report
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\" + repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
		Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		//For sending email with report
		try {
			URL url = new URL("file:///"+System.getProperty("userr.dir")+"\\reports\\"+ repName);
			
			//Create email message
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("dagnavin@gmail.com", "Daggula@86"));
			email.setSSLOnConnect(true);
			email.setFrom("dagnavin@gmail.com");	// Sender
			email.setSubject("Test Results");
			email.setMsg("Please Find Attached Report..");
			email.addTo("navindaggula86@gmail.com");	// Receiver
			email.attach(url, "extent report", "Please check report");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		
	}

}
