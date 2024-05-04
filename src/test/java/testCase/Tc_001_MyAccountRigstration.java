package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;
import pageObjects.AccountRegistrationPage;

public class Tc_001_MyAccountRigstration extends BaseClass {
	AccountRegistrationPage arp;
	HomePage hp;

	@Test(groups = {"sanity","master"})
	public void Tc_001_MyAccountRigstrationTest() throws InterruptedException {
		logger.info("**** Starting Tc_001_MyAccountRigstrationTest ****");

		logger.debug("Application log started");
		
		try {

			hp = new HomePage(driver);

			hp.ClickOnMyAccountElement();
			logger.info("clicked on MyAccount Link ");
			hp.clickOnRegistorElement();
			logger.info("clicked on Register Link ");

			logger.info("validating expecting  Title");
			Assert.assertEquals(driver.getTitle(), "Register Account");

			arp = new AccountRegistrationPage(driver);
			logger.info("On Page of Registration");

			logger.info("Entering customer details..");
			arp.setFirstName(randomString().toLowerCase());
			arp.setLastName(randomString().toLowerCase());
			arp.setEmailID(randomString() + "@gmail.com");
			arp.setTelePhoneNumber(randomNumber());
			String passwordnew = randomAlphaNumeric();
			arp.setPassword(passwordnew);
			arp.setConfirmPassword(passwordnew);
			arp.selectCheckBox();
			arp.clickOnContinue();
			logger.info("clicked on continue button");
			Thread.sleep(5000);
			String confimMsg = arp.setConfirmationMsg();
			logger.info("validating Expecting Message");
			Assert.assertEquals(confimMsg, "Your Account Has Been Created!");
		} catch (Exception e) {
			logger.error("test failed..");
			logger.debug("debug logs....");
			Assert.fail();
		}
		logger.debug("application logs end.......");
		logger.info("**** finished TC_001_AccountRegistrationTest  *****");

	}

}
