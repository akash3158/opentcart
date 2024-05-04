package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class Tc_002_LoginTest extends BaseClass {
	HomePage hp;
	MyAccountPage map;
	LoginPage lp;

	@Test(groups = {"regression","master"})
	public void verify_Login() {

		logger.info("**** starting Tc_002_LoginTest... ****");
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
			lp.setEmailId(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickOnLoginBtn();
			logger.info("clicked on login Button");

			map = new MyAccountPage(driver);
			logger.info("enterd MyAccount page");

			boolean targ_page = map.myaccountTxt();

			if (targ_page == true) {
				logger.info("Login Test passed");
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}

			logger.info("Validating My account text is Displyed ");

			map.clickOnLogOutBtn();
			logger.info("clicked on logOut link");
		} catch (Exception e) {
			Assert.fail();

		}
		logger.info("**** Finished Tc_002_LoginTest ****");

	}

}
