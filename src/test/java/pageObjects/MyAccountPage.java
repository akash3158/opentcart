package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	WebDriver driver;

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement txt_myAccount;

	@FindBy(xpath = "//aside[@id='column-right']//div//a[13]")
	WebElement logOutlink;

	public boolean myaccountTxt() {
		try {
			
			return (txt_myAccount.isDisplayed());
		} catch (Exception e) {

			return (false);
		}
		
	}
	
	public void clickOnLogOutBtn() {
		logOutlink.click();
	}

}
