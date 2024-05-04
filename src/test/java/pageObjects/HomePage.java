package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {

		super(driver);

	}

	@FindBy(xpath = "//a[@title='My Account']")
	WebElement myaccountElement;

	@FindBy(xpath = "//a[text()='Register']")
	WebElement registerElement;
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//li[2]//a")
	WebElement loginElement;

	public void ClickOnMyAccountElement() {
		myaccountElement.click();
	}

	public void clickOnRegistorElement() {
		registerElement.click();
	}
	
	public void clickOnLoginLink() {
		loginElement.click();
	}
}
