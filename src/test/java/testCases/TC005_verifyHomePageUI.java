package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.HomePage;
import testBase.BaseTest;

public class TC005_verifyHomePageUI extends BaseTest {

	@Test
	public void verify_isCartDisplayed() {

		HomePage hp = new HomePage(driver);
		boolean cartTab= hp.isCartButtonDisplayed();
		Assert.assertTrue(cartTab, "cart tab does not displayed"); // string tabhi display hogi jb assertion fail hoga
		
	}

	@Test
	public void verify_isLogoDisplayed() {

		HomePage hp = new HomePage(driver);
		boolean logoDisplay= hp.isLogoDisplayed();
		Assert.assertTrue(logoDisplay, "logo not displayed");
		
	}
	
	@Test
	public void verify_isMenubarDisplayed() {

		HomePage hp = new HomePage(driver);
		boolean menuBar= hp.isMenubarDisplayed();
		Assert.assertTrue(menuBar, "menubar not displayed");
		
	}
	
//	@Test
//	public void verify_HoverOnMenubar() {
//
//		HomePage hp = new HomePage(driver);
//		hp.isMenubarHovered();
//		
//		
//	}
	
	@Test
	public void verify_homePageCurrURL() {

		HomePage hp = new HomePage(driver);
		String currURL= hp.homePageURL();
		Assert.assertEquals(currURL, property.getProperty("appURL"), "Current URL mismatched");
		
	}
	
	@Test
	public void verify_homePageTitle() {

		HomePage hp = new HomePage(driver);
		String title= hp.homePageTitle();
		Assert.assertEquals(title,"Your Store", "Title mismatched");
		
	}
	
	@Test
	public void verify_isFeaturedProdDisplayded() {
		HomePage hp= new HomePage(driver);
		Assert.assertTrue(hp.isFeaturedProductsDisplayed(), "featured product list not displayed");
		
	}
	

}
