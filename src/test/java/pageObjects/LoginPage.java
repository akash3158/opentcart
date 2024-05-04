package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "input-email")
	WebElement txt_emailID;
	@FindBy(id = "input-password")
	WebElement txt_password;
	@FindBy(xpath = "//input[@class='btn btn-primary']")
	WebElement loginBtn;


	public void setEmailId(String emailID) {
		txt_emailID.sendKeys(emailID);
	}
	
	public void setPassword(String pwd) {
		txt_password.sendKeys(pwd);
	}
	
	public void clickOnLoginBtn() {
		loginBtn.click();
	}
	
	
}
