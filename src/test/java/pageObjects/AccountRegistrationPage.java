package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	WebDriver driver;

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "input-firstname")
	WebElement txt_FirstName;
	@FindBy(id = "input-lastname")
	WebElement txt_LastName;
	@FindBy(id = "input-email")
	WebElement txt_EmailID;
	@FindBy(id = "input-telephone")
	WebElement txt_TelePhone;
	@FindBy(id = "input-password")
	WebElement txt_Password;
	@FindBy(id = "input-confirm")
	WebElement txt_ConfirmPassword;
	@FindBy(xpath = "//input[@name='agree']")
	WebElement checkBox;
	@FindBy(xpath = "//*[@id=\"content\"]/form/div/div/input[2]")////*[@id="content"]/form/div/div/input[2]
	WebElement continueBtn;

	@FindBy(xpath = "//div[@id='content']//h1")
	WebElement confrimationMsg;

	public void setFirstName(String firstName) {
		txt_FirstName.sendKeys(firstName);
	}

	public void setLastName(String lastName) {
		txt_LastName.sendKeys(lastName);
	}

	public void setEmailID(String emailId) {
		txt_EmailID.sendKeys(emailId);
	}

	public void setTelePhoneNumber(String telePhone) {
		txt_TelePhone.sendKeys(telePhone);
	}

	public void setPassword(String password) {
		txt_Password.sendKeys(password);
	}

	public void setConfirmPassword(String confirm_pwd) {
		txt_ConfirmPassword.sendKeys(confirm_pwd);
	}

	public void selectCheckBox() {
		checkBox.click();
	}

	public void clickOnContinue() {
		continueBtn.click();
		//sol2 
		//continueBtn.submit();
		
		//sol3
		//Actions act=new Actions(driver);
		//act.moveToElement(continueBtn).click().perform();
					
		//sol4
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();", continueBtn);
		
		//Sol5
		//continueBtn.sendKeys(Keys.RETURN);
		
		//Sol6  
		//WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();

	}

	public String setConfirmationMsg() {
		try {
			return (confrimationMsg.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

}
