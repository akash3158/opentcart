package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class Tc_003_LoginDDT extends BaseClass {
	HomePage hp;
	MyAccountPage map;
	LoginPage lp;

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void verify_LoginDDT(String email, String password, String exp) {

		logger.info("**** starting Tc_002_LoginDDT... ****");
		logger.debug(" capturing application debug log....");
		try {

			hp = new HomePage(driver);
			hp.ClickOnMyAccountElement();
			logger.info("Clicked on myAccount link");
			hp.clickOnLoginLink();
			logger.info("clicked on login link");

			lp = new LoginPage(driver);
			logger.info("entering login page ");
			logger.info("Entering email and password");
			lp.setEmailId(email);
			lp.setPassword(password);
			lp.clickOnLoginBtn();
			logger.info("clicked on login Button");

			map = new MyAccountPage(driver);
			logger.info("enterd MyAccount page");

			boolean targ_page = map.myaccountTxt();

			if (exp.equalsIgnoreCase("Valid")) {

				if (targ_page == true) {

					logger.info("Login Test passed");
					map.clickOnLogOutBtn();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			} 
			if (exp.equalsIgnoreCase("Invalid")) {
				if (targ_page == true) {
					map.clickOnLogOutBtn();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}

		} catch (Exception e) {
			Assert.fail("An exception occurred: " + e.getMessage());

		}
		logger.info("**** Finished Tc_002_LoginDDT ****");

	}
}
