package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class accountCreationPage {

	
	@FindBy(xpath="//div[contains(text(),'Sign In')]")
	public WebElement signIn;
	
	@FindBy(linkText="Create a New Account")
	public WebElement btn_createAccount;
	
	@FindBy(id="ap_customer_name")
	public WebElement txtbox_customerName;
	
	@FindBy(id="ap_email")
	public WebElement txtbox_email;
	
	@FindBy(id="ap_password")
	public WebElement txtbox_emailPassword;
	
	@FindBy(id="ap_password_check")
	public WebElement txtbox_emailPasswordRecheck;
	
	@FindBy(id="continue")
	public WebElement btn_createImdb;
		
	@FindBy(id="cvf-input-code")
	public WebElement txtbox_otp;
		
	@FindBy(css="input[aria-labelledby='cvf-submit-otp-button-announce']")
	public WebElement btn_submitOtp;
	
	
	
	public accountCreationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
