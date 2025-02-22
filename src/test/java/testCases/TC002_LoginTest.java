package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups ={ "Master", "Sanity"})
	public void verify_login() throws InterruptedException
	{
		logger.info("***** Starting TC002_LoginTest *****");
		
		try {
		//Homepage
		HomePage hp = new HomePage(driver);
		Thread.sleep(500);
		hp.clickMyAccount();
		hp.clickLogin();
		
		logger.info("***** Adding Login page  details *****");
		//LoginPage
		Thread.sleep(500);
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		Thread.sleep(2000);
		
		logger.info("***** My Account page Validations *****");
		//MyAccountPage
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetpage = macc.isMyAccountPageExists();
		
	//	Assert.assertTrue(targetpage);	//Assert.assertEquals(targetpage, true,)
		
		Assert.assertEquals(targetpage, true, "Login Test failed.."); 
		}
		catch (Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***** Finished TC002_LoginTest *****");
	}

}
