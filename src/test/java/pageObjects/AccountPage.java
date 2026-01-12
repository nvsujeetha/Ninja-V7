package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {

	WebDriver driver;
	//constructor
	
	public AccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//h1[normalize-space()='My Account']")
	WebElement confirmationText_MyAccount;
	@FindBy(xpath="//span[normalize-space()='My Account']")
    WebElement dropDown_MyAccount;
	@FindBy(xpath="//a[@class='dropdown-item'][normalize-space()='Logout']")
	WebElement link_Logout;
	
	//ActionMethods
	public WebElement getMyAccountConfirmation()
	{
		return confirmationText_MyAccount;
	}
	public void clickMyAccountDropDown()
	{
		dropDown_MyAccount.click();
	}
	public void clickLogout()
	{
		link_Logout.click();
	}
	
	


}
