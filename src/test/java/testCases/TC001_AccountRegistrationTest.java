package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups ={ "Master", "Regression"})
	public void verifyAccountRegistration() throws InterruptedException
	{
		
		logger.info("***** Starting TC001_AccountRegistrationTest ******");
		
		try {
		HomePage hp = new HomePage(driver);
		Thread.sleep(500);
		hp.clickMyAccount();
		logger.info("Clicked on My Account link..");
		hp.clickRegister();
		logger.info("Clicked on Register link..");
		
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("Adding Customer details for Registration..");
		Thread.sleep(500);
		regpage.setFirstname(randomString());
		regpage.setLastname(randomString());
		regpage.setEmail(randomString() + "@gmail.com");
		regpage.setTelephone(randomNumber());
		
		String password = randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating the registration completion message..");
		String cnfmsg= regpage.getConfirmationmsg();
		if(cnfmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed..");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}
			
		
	//	Assert.assertEquals(cnfmsg, "Your Account Has Been Created!!");
		} 
		catch (Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***** Finished TC001_AccountRegistrationTest ******");
		
	}
	
}
