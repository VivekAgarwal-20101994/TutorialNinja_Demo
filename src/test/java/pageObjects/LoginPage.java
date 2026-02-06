package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver){
		super(driver);
	}
	
	
	//locators
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmailAddress;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement btnSubmit;
	
		
	//action_methods
	
	public void setEmail(String email)
	{
		txtEmailAddress.sendKeys(email);
	}
	
	public void setPassword(String pass)
	{
		txtPassword.sendKeys(pass);
	}
	
	public void loginClick()
	{
		btnSubmit.click();
	}
	
//	public String getConfirmationMsg() {
//		
//		try{
//			return (msgConfirmation.getText());
//		}
//		catch(Exception e) 
//		{
//			return e.getMessage();
//		}
//		
//		
//	}


}
