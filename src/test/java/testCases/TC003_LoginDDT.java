package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;


public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups = "DataDriven")
	public void verify_loginDDT(String email, String pass, String res) throws InterruptedException {
		logger.info("***** Starting TC003_LoginDDT *****");
		
		try {
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage
		Thread.sleep(500);
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pass);
		lp.clickLogin();
		
		Thread.sleep(500);
		
		//MyAccountPage
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetpage = macc.isMyAccountPageExists();
		

/*
Data is valid  - login success - test pass 	- logout
Data is valid -	login failed - test fail

Data is invalid	- login success - test fail - logout
Data is invalid - login failed	- test pass
*/
		
		if(res.equalsIgnoreCase("Valid"))
		{
			if(targetpage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(res.equalsIgnoreCase("Invalid"))
		{
			if(targetpage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		} catch(Exception e)
		{
			Assert.fail();
		}

		logger.info("***** Finished TC003_LoginDDT *****");
	}
	
	

}
