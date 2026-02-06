package utilities;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseTest;

public class ExtentReportUtility implements ITestListener {
	
	ExtentSparkReporter sparkReporter; 
	ExtentReports extent;
	ExtentTest test;
	String repName;
	
	public void onStart(ITestContext context) {
		
		//we need to create timestamp and add it in file name to make name dynamic and to keep the history
		
		SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); //need to use SimpleDateFormat to create timestamp format
		Date dt= new Date(); //using date class of class to create the date
		String timeStamp= df.format(dt);
		repName= "Test_Report"+timeStamp+".html";
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName); //location of report
		sparkReporter.config().setDocumentTitle("AutomationReport"); //title of report
		sparkReporter.config().setReportName("Functional test"); //report name
		sparkReporter.config().setTheme(Theme.DARK); //theme
		
		extent= new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "QAfox");
		extent.setSystemInfo("Username", System.getProperty("user.name"));
		
		String os= context.getCurrentXmlTest().getParameter("OS"); //context is coming from on start method variable and it has xml file information
		extent.setSystemInfo("Operating System", os);
		
		String br= context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Operating System", br);
		
		List<String> inlcudedGroups= context.getCurrentXmlTest().getIncludedGroups();
		if(!inlcudedGroups.isEmpty()) {
			extent.setSystemInfo("Included Groups", inlcudedGroups.toString()); //if xml has groups then it will work
		}
		
	}

	public void onTestSuccess(ITestResult result) {
		
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); //to display group in report for pass case
		test.log(Status.PASS, result.getName()+" got passed");
		
	}

	
	public void onTestFailure(ITestResult result) {
		
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); //to display group in report for fail case
		test.log(Status.FAIL, result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try{
			String imgPath= new BaseTest().captureScreen(result.getName());
		test.addScreenCaptureFromPath(imgPath);
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		
	}

	
	public void onTestSkipped(ITestResult result) {
		
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); //to display group in report for skip case
		test.log(Status.SKIP, result.getName()+" got skipped");
		
		
	}


	public void onFinish(ITestContext context) {
		extent.flush();
		
	}

}
