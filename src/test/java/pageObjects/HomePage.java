package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{
	
	WebDriver driver;
	//Constructor is to initialize object
	public HomePage(WebDriver driver)
	{
		
		super(driver);//Super is the calss above this is passing the driver.
		
	}
	
	
	
	//Locators
	
	@FindBy(xpath="//i[@class='fa-solid fa-user']")
	WebElement link_MyAccount;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement link_Login;

	
	
	//Action Methods
	public void clickMyAccount()
	{
		link_MyAccount.click();
	}

	public void goToLogin()
	{
		link_Login.click();
	}
}
