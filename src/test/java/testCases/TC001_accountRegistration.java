package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseTest;

public class TC001_accountRegistration extends BaseTest {
	
		
	@Test(groups={"Regression", "Master"})
	void accountRegistration() {
		
		//calling logger here since it is already initialized in basetest(parent class)
		logger.info("****Starting Test-Case****"); //mentioned logs comments for log report
		
		try {
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		logger.info("****Opened homepage and clicked on register button****");
		
		
		AccountRegistrationPage register= new AccountRegistrationPage(driver);
		
		//getting random string and numbers data from base test and send it in fields
		
		register.setfirstname(randomString().toString());
		register.setlastname(randomString().toString());
		register.setemail(randomString()+"@test.com");
		register.setphone(randomNumber());
		
		String pass= randomAlphaNumber();
		System.out.println(pass);
		//getting random password from base test and storing in local var to send it in pass and cnfpass fields
		register.setpassword(pass);
		register.setcnfpassword(pass);
		register.chkboxclick();
		register.continuebtn();
		logger.info("****Sent user details for registration****");
		
		
		String cnfmsg= register.getConfirmationMsg();
		Assert.assertEquals(cnfmsg, "Your Account Has Been Created!");
		logger.info("Login passed and account has been created");
		}
		
		catch(Throwable e) {
			logger.error("Login failed due to:"+e.getMessage());
			Assert.assertFalse(true);
		}
	
		finally {
		logger.info("****Finished Test-Case****");
		}
	}
			
}
