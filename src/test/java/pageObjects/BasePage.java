package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	//constructor
	WebDriver driver;
	//Constructor is to initialize object
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		//the passed driver is assigned to the instance variable driver. This allows the 
		//class and its subclasses to use its browser interactions.
		PageFactory.initElements(driver, this);
		//The above line initializes the web elements defined in the class using Selenium's page factory.
		// PageFactory.initElements() tells Selenium to scan the current class(this) for anu @FindBy annotations
		//and connect them to actual elements on the page using the provided driver.
		
		
	}
	
	
}
