package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[@title='My Account']//span[text()='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']") 
	WebElement lnkLogin;
	
	public void clickMyAccount()
	{
		//lnkMyaccount.clear();			// was not working hence used Actions class
		Actions act = new Actions(driver);
		act.moveToElement(lnkMyaccount).click().perform();
	}
	
	public void clickRegister()
	{
		//lnkRegister.click();			// was not working hence used Actions class
		Actions act = new Actions(driver);
		act.moveToElement(lnkRegister).click().perform();
	}
	
	public void clickLogin()
	{
		Actions act = new Actions(driver);
		act.moveToElement(lnkLogin).click().perform();
	}
	

}
