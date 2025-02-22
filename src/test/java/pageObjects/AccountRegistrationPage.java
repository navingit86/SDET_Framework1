package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstname;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastname;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;

	public void setFirstname(String fname) {
	//	WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
	//	mywait.until(ExpectedConditions.elementToBeClickable(txtFirstname));
		txtFirstname.sendKeys(fname);
	}

	public void setLastname(String lname) {
		txtLastname.sendKeys(lname);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setTelephone(String tele) {
		txtTelephone.sendKeys(tele);
	}

	public void setPassword(String pass) {
		txtPassword.sendKeys(pass);
	}

	public void setConfirmPassword(String cnfPass) {
		txtConfirmPassword.sendKeys(cnfPass);
	}

	public void setPrivacyPolicy() {
		chkPolicy.click();
	}

	public void clickContinue() {
		// Sol1
		btnContinue.click();

	//	//Sol2
	//	btnContinue.submit();
	//	
	//	//Sol3
	//	Actions act = new Actions(driver);
	//	act.moveToElement(btnContinue).click().perform();
	//	
	//	//Sol4
	//	JavascriptExecutor js =  (JavascriptExecutor)driver;
	//	js.executeScript("arguments[0].click;", btnContinue);
	//	
	//	//Sol5
	//	btnContinue.sendKeys(Keys.RETURN);
	//	
	//	//Sol6
	//	WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
	//	mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();

	}

	public String getConfirmationmsg()
	{
		try
		{
		return (msgConfirmation.getText());
		}
		catch (Exception e)
		{
			return (e.getMessage());
		}
	}
	
}
