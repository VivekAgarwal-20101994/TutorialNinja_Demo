package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
	/*
		Hybrid Framework create karte waqt Base Test (ya BaseClass) aapke poore automation project ka foundation hota hai.

		Sochiye, agar aap 100 tests likh rahe hain, toh aap har test mein browser open karne ya login karne ka code baar-baar nahi likhenge. Base Test wahi jagah hai jahan aap common tasks ko ek baar likhte hain taaki har test case use "Inherit" (use) kar sake.

		Base Test ke Main Kaam (Responsibilities)
		Ek standard Base Test class mein ye cheezein hoti hain:
		
		WebDriver Initialization: Browser (Chrome, Firefox, etc.) ko setup karna.
		
		Configuration Loading: config.properties file se URL, Username, ya Browser name read karna.
		
		Hooks Management: * @BeforeMethod / @BeforeSuite: Test shuru hone se pehle browser open karna.
		
		@AfterMethod / @AfterSuite: Test khatam hone ke baad browser close karna (driver.quit()).
		
		Common Utilities: Screenshots lena, Reports (Extent Reports) setup karna, ya Logs generate karna.
	 */

	public static WebDriver driver;	//need to make it static as we are using this in capture_screen function as well
	public Logger logger; //for log4j 
	public Properties property; 

	@BeforeClass(groups= {"Sanity", "Regression", "Master", "DataDriven"})
	@Parameters({"OS", "browser"})
	public void setUp(String os, String br) throws IOException {
			
			property= new Properties();//created properties object to read properties file
		//we can use filereader as well instead of fileinputstream to read file
			
			FileReader file= new FileReader("./src//test//resources//config.properties");
			property.load(file);
			
		
			//creating log methods to use the log4j2 xml file as it will be commonly used so creating here-			
			logger = LogManager.getLogger(this.getClass());
			//log.info("Logger initialized for " + this.getClass().getName());	
			
			//using switch to decide which browser will invoke based on string br value in testcase
			switch(br.toLowerCase()) 
			{
			case "chrome": 
	            driver = new ChromeDriver(); 
	            break;
	            
	        case "edge": 
	            // Edge ke liye issue aa rha h so adding exe file path for edge driver manually
	        	System.setProperty("webdriver.edge.driver", "C:\\Users\\vivek\\Downloads\\edgedriver_win32\\msedgedriver.exe");
	        	
//	            EdgeOptions edgeOptions = new EdgeOptions();
//	            edgeOptions.addArguments("--remote-allow-origins=*");
	            driver = new EdgeDriver(); 
	            break;
	            
	        case "firefox": 
	            driver = new FirefoxDriver(); 
	            break;
	            
			default: System.out.println("Invalid browser name.."); return;
			}
			
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(property.getProperty("appURL"));
			driver.manage().window().maximize();
		}
		
	@AfterClass(groups= {"Sanity", "Regression", "Master", "DataDriven"})
	public void tearDown() {
			driver.quit();
		}
	
	/*
	Hybrid framework mein Apache Commons Lang library ek game-changer hoti hai. Iska use hum tab karte hain jab humein String manipulation, random data generation, ya objects ke saath kaam karna ho bina lambe-chaude loops likhe.

	Ye library automation mein data handling ko bahut asan bana deti hai. Yahan kuch sabse zyada use hone wale functions hain:

	1. StringUtils (String Handling ke liye)
	Ye sabse zyada use hone wali class hai. Iska fayda ye hai ki ye null values ko handle kar leti hai aur NullPointerException nahi aane deti.

	StringUtils.isEmpty(str): Check karta hai ki string khali hai ya null.

	StringUtils.isBlank(str): Ye isEmpty se behtar hai kyunki ye whitespace (space) ko bhi check karta hai.

	StringUtils.substringBetween(str, "start", "end"): Agar aapko kisi bade text ke beech se koi value nikalni ho (jaise Order ID), toh ye best hai.

	StringUtils.isNumeric(str): Check karne ke liye ki kya text sirf numbers se bana hai.

	2. RandomStringUtils (Test Data Generation)
	Automation mein humein har baar naya email ya username chahiye hota hai. Uske liye ye best hai:

	RandomStringUtils.randomAlphabetic(8): 8 characters ka random naam generate karega (e.g., "jKlsPqRz").

	RandomStringUtils.randomNumeric(10): Random mobile number generate karne ke liye.

	RandomStringUtils.randomAlphanumeric(12): Random password ya unique ID generate karne ke liye.

	3. SystemUtils (Environment Info)
	Jab aapko check karna ho ki test kaunse OS par chal raha hai:

	SystemUtils.IS_OS_WINDOWS: Returns true agar windows hai.

	SystemUtils.JAVA_VERSION: Aapke system ki Java version batata hai.
	*/
	
	//created ramdom data creation methods and we can use them as per requirement in any test case since they are common so kept it in basetest
	public String randomString() {
		String randomAlpha= RandomStringUtils.randomAlphabetic(5);
		return randomAlpha;
	}
	
	public String randomNumber() {
		String randomNum= RandomStringUtils.randomNumeric(10);
		return randomNum;
	}
	
	public String randomAlphaNumber() {
		String randomAlpha = RandomStringUtils.randomAlphabetic(3);
		String randomNum = RandomStringUtils.randomNumeric(3);
		return (randomAlpha+"@"+randomNum);
	}
	
	public String captureScreen(String tname) {
		
		SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); //need to use SimpleDateFormat to create timestamp format
		Date dt= new Date(); //using date class of class to create the date
		String timeStamp= df.format(dt);
		
		TakesScreenshot ts= (TakesScreenshot) driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		
		String filepath= System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
				
		File target= new File(filepath);
		
		source.renameTo(target);
		return filepath;
		}
	
}
