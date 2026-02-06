package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseTest {

	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven")
	public void verify_loginDD(String email, String password, String expResult) {
		
		logger.info("****Starting TC003_LoginDDT****"); //mentioned logs comments for log report
		
		try {
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.loginClick();
		
		MyAccountPage account= new MyAccountPage(driver);
		boolean targetpage= account.isMyAccountPageExists();
		
		/*
		 DDT scenario 1: valid data--- logged in(test pass)---logout
		 				 valid data---not logged in(test fail)  -logout is not required
		 
		 DDT scenario 2: invalid data--- logged in(test fail)---logout		
		 				 invalid data---not logged in(test pass)  -logout is not required
		
		*/
		
		//ddt scenario 1
		if(expResult.equalsIgnoreCase("Pass")) 
		{
			if(targetpage==true) {
			account.clickLogout();
			Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}
			//ddt scenario 2
		if(expResult.equalsIgnoreCase("Fail")) 
		{
			if(targetpage==true) {
			account.clickLogout();
			Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
			
		}
		catch(Throwable e) {
			System.err.println(e.getMessage());
			Assert.fail();
		}
		finally {
			logger.info("****Finished Test-Case****");
			}

				
	}
}
