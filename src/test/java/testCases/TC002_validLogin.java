package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;

public class TC002_validLogin extends BaseTest {

	@Test(groups={"Sanity", "Master"})
	public void verify_login() {
		
		logger.info("****Starting Test-Case****"); //mentioned logs comments for log report
		try {
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(property.getProperty("email"));
		lp.setPassword(property.getProperty("password"));
		lp.loginClick();
		
		MyAccountPage account= new MyAccountPage(driver);
		boolean targetpage= account.isMyAccountPageExists();
		
		Assert.assertEquals(targetpage, true);
		logger.info("Login test passed");
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
