package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	
	public AccountRegistrationPage(WebDriver driver){
		super(driver);
	}
	
	
	//locators
	@FindBy(xpath="//input[@name='firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@name='lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@name='telephone']")
	WebElement txtPhone;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@name='confirm']")
	WebElement txtCnf_password;
	
	
	@FindBy(xpath="//input[@type='checkbox']")
	WebElement chkPolicy;
			
	@FindBy(xpath="//input[@type='submit']")
	WebElement btnSubmit;
	
	@FindBy(xpath="//div[@id='content']//h1")
	WebElement msgConfirmation;
	
	
	//action_methods
	
	public void setfirstname(String fname)
	{
		txtFirstname.sendKeys(fname);
	}
	
	public void setlastname(String lname)
	{
		txtLastname.sendKeys(lname);
	}
	
	public void setemail(String user_email)
	{
		txtEmail.sendKeys(user_email);
	}
	
	public void setphone(String tel)
	{
		txtPhone.sendKeys(tel);
	}
	
	public void setpassword(String pass)
	{
		txtPassword.sendKeys(pass);
	}
	
	public void setcnfpassword(String pass)
	{
		txtCnf_password.sendKeys(pass);
	}
	
	public void chkboxclick()
	{
		chkPolicy.click();
	}
	public void continuebtn()
	{
		btnSubmit.click();
	}
	
	public String getConfirmationMsg() {
		
		try{
			return (msgConfirmation.getText());
		}
		catch(Exception e) 
		{
			return e.getMessage();
		}
		
		
	}


}
