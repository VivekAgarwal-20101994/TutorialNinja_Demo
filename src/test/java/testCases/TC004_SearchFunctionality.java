package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.HomePage;
import testBase.BaseTest;

public class TC004_SearchFunctionality extends BaseTest {

	@Test
	public void verifyEmpty_Incorrect_Search() {

		HomePage hp = new HomePage(driver);
		hp.searchProduct("none");
		hp.clickSearchButton();
		
		String actMsg= hp.actAlertMsg();
		String expMsg = "There is no product that matches the search criteria.";

		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, expMsg, "Message is not same as expected"); // string tabhi display hogi jb assertion fail hoga
		
		boolean msgDisplayed= hp.AlertMsgdisplays();
		sa.assertTrue(msgDisplayed, "Element not displayed"); // string tabhi display hogi jb assertion fail hoga
		sa.assertAll();
	}

	@Test
	public void verifyProdSearch() {

		HomePage hp = new HomePage(driver);
		hp.clr_searchProduct();
		hp.searchProduct("Mac");
		hp.clickSearchButton();

		int totalprd= hp.totalSearchedProduct();
		
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(totalprd>0, "Search failed! No products found for searched product."); 
		sa.assertAll();
	}
	
	@Test
	public void verifyProdSearchName() {

		HomePage hp = new HomePage(driver);
		hp.clr_searchProduct();
		hp.searchProduct("none");
		hp.clickSearchButton();
		
		
		int totalprd= hp.totalSearchedProduct();
		
		if(totalprd>0) 
		{
		boolean containedName= hp.searchProductName(0).contains("Mac");	//checking the name is available in the searched product list by sending 1st index
		Assert.assertTrue(containedName, "Found product name is: "+hp.searchProductName(0));
		}		
		else{
			Assert.assertFalse(true, "No product found with the seach criteria");
		}

	}
}
