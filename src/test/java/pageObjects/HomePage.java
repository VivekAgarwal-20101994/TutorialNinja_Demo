package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

public class HomePage extends BasePage {
	

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[@title='My Account']") 
	WebElement lnkMyaccount;
	
	@FindBy(xpath="//a[text()='Register']") 
	WebElement lnkRegister;
	
	@FindBy(xpath="//a[text()='Login']") 
	WebElement lnkLogin;
	
	@FindBy(xpath="//input[@name='search']")
	WebElement txtSearch;		//search text box
	
	@FindBy(xpath="//div[@id='search']//button")
	WebElement btnSearchIcon;		//search icon
	
	@FindBy(xpath="//div[@id='cart']")
	WebElement btnCart;		//cart tab on homepage
	
	@FindBy(xpath="//div[@class='row']//div[@class='product-thumb']")
	List<WebElement> foundProducts;		//list of product table after search
	
	@FindBy(xpath="//div[@class='row']//div[@class='product-thumb']//h4/a")
	List<WebElement> searchedProdName;
	
	@FindBy(xpath="//div[@id='content']/p[contains(text(),'that matches the search criteria.')]")
	WebElement alertMessage;		//msg text element when found no product after search
	
	@FindBy(xpath="//div[@id='logo']")
	WebElement logo;		//logo element on homepage
	
	@FindBy(xpath="//ul[@class='nav navbar-nav']")
	WebElement menuBar; //menu bar on homepage
	
	@FindBy(xpath="//ul[@class='nav navbar-nav']/li")
	List<WebElement> menuOptions; //list of menu options tab on homepage
	
	
	@FindBy(xpath="//div[@id='content']//div[@class='row']")
	WebElement featuredProds;
	
	//p[@class='price']
	
	@FindBy(xpath="//p[@class='price']")
	List<WebElement> prodsPrice;
	
	//Action methods
	
	
	public void clickMyAccount() {
		lnkMyaccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();
	}
	
	public void clickSearchButton() {
		btnSearchIcon.click();
	}
	
	public void searchProduct(String itemname) {
		txtSearch.sendKeys(itemname);
	}
	
	public void clr_searchProduct() {
		txtSearch.clear();
	}
	
	
	public int totalSearchedProduct() {
		return (foundProducts.size());
	}
	
	public String searchProductName(int index) {
		return searchedProdName.get(0).getText();
	}
	
	public String actAlertMsg() {
		return (alertMessage.getText());
	}
	
	public boolean AlertMsgdisplays() {
		try{
			return (btnCart.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public boolean isCartButtonDisplayed() {
		try{
			return (btnCart.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public boolean isMenubarDisplayed() {
		try{
			return (menuBar.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public boolean isLogoDisplayed() {
		try{
			return (logo.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void isMenubarHovered() {		
								
			Actions myact= new Actions(driver);
			for(WebElement menu:menuOptions)
			{
				//String menuName = menu.getText();
			
			try{
			myact.moveToElement(menu).perform();
			
			WebElement menuDropdown = menu.findElement(By.xpath(".//div[@class='dropdown-menu']"));
	        
	        // Assertion: Check karein ki menu dropdown display ho raha hai on hover
	        SoftAssert sa = new SoftAssert();
	        sa.assertTrue(menuDropdown.isDisplayed(), "Dropdown display nahi hua for: " + menu.getText());
	        sa.assertAll();
			
			}
		
		catch (Exception e) {
	        // Kuch menus (like 'Phones') mein dropdown nahi hota, use handle karein
	        System.out.println(menu.getText() + " has no dropdown.");
	    	}
		}
	}
	
	public String homePageTitle() {
		return driver.getTitle();
	}
	
	public String homePageURL() {
		return driver.getCurrentUrl();
	}
	
	public boolean isFeaturedProductsDisplayed() {
		try{
			return (featuredProds.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
	
}
