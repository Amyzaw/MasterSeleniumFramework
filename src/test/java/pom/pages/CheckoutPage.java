package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pom.base.BasePage;
import pom.objects.BillingAddress;
import pom.objects.User;


public class CheckoutPage extends BasePage {

	private final By checkoutTitle = By.xpath("//h1[normalize-space()='Checkout']");
	private final By firstnameFld = By.xpath("//input[@id='billing_first_name']");
	private final By lastnameFld = By.xpath("//input[@id='billing_last_name']");
	private final By addressLineOneFld = By.xpath("(//input[@id='billing_address_1'])[1]");
	private final By billingCompanyFld = By.xpath("//input[@id='billing_company']");
	private final By billingCityFld = By.xpath("(//input[@id='billing_city'])[1]");
	private final By billingPostCodeFld = By.xpath("//input[@id='billing_postcode']");
	private final By billingEmailFld = By.xpath("(//input[@id='billing_email'])[1]");
	private final By placeOrderBtn = By.xpath("//button[@id='place_order']");
	private final By successNotice = By.cssSelector(".woocommerce-notice.woocommerce-notice--success.woocommerce-thankyou-order-received");
	private final By LoinError = By.cssSelector("woocommerce-error");
	
	private final By userNameFld = By.xpath("//input[@id='username']");
	private final By passwordFld = By.xpath("//input[@id='password']");
	private final By loginBtn = By.name("login");
	
	private final By overLay = By.cssSelector(".blockUI.blockOverlay");
	private final By countryDropDown = By.id("billing_country");
	private final By stateDropDown = By.id("billing_state");
	private final By directBankTransferRadioBtn = By.id("payment_method_bacs");
	private final By cashOnDeliveryTransferRadioBtn = By.id("payment_method_cod");

	private final By alternateCountryDropDown = By.id("select2-billing_country-container");
	private final By alternateStateDropDown = By.id("select2-billing_state-container");
	
	private final By productName = By.cssSelector("td[class='product-name']");
	private final By clickHereToLoginLink = By.className("showlogin");

	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public CheckoutPage load() {
		load("/checkout/");
		return this;
	}

	public String getCheckoutTitle() {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutTitle)).getText();
	}

	public CheckoutPage enterFirstName(String firstName) {
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(firstnameFld));
		e.clear();
		e.sendKeys(firstName);
		return this;
	}

	public CheckoutPage enterLastName(String lastName) {
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(lastnameFld));
		e.clear();
		e.sendKeys(lastName);
		return this;
	}

	public CheckoutPage selectCountry(String countryName) {

		wait.until(ExpectedConditions.elementToBeClickable(alternateCountryDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[text()='" + countryName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;

	}

	public CheckoutPage enterAddressLineOne(String addressLineOne) {
		
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(addressLineOneFld));
		e.clear();
		e.sendKeys(addressLineOne);
		return this;
	}

	public CheckoutPage enterBillingCompany(String billingCompany) {
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingCompanyFld));
		e.clear();
		e.sendKeys(billingCompany);
		return this;
	}

	public CheckoutPage enterBillingCity(String billingCity) {
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingCityFld));
		e.clear();
		e.sendKeys(billingCity);
		return this;
	}

	public CheckoutPage selectState(String stateName) {
		
		wait.until(ExpectedConditions.elementToBeClickable(alternateStateDropDown)).click();
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[text()='" + stateName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
		return this;
	}

	public CheckoutPage enterBillingPostCode(String billingPostCode) {
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingPostCodeFld));
		e.clear();
		e.sendKeys(billingPostCode);
		return this;
	}

	public CheckoutPage enterBillingEmail(String billingEmail) {

		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingEmailFld));
		e.clear();
		e.sendKeys(billingEmail);
		return this;
	}

	public CheckoutPage setBillingAddress(BillingAddress billingAddress) {
		return enterFirstName(billingAddress.getFirstName()).
		enterLastName(billingAddress.getLastName()).
		selectCountry(billingAddress.getCountry()).
		enterBillingCompany(billingAddress.getCompanyName()).
		enterAddressLineOne(billingAddress.getAddressLineOne()).
		enterBillingCity(billingAddress.getCity()).
		selectState(billingAddress.getState()).
		enterBillingPostCode(billingAddress.getPostalCode()).
		enterBillingEmail(billingAddress.getEmail());
	}


	public CheckoutPage clickPlaceOrderBtn() {
		waitForOverLaysToDisappear(overLay);
		wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderBtn)).click();
		return this;
	}
	

	public String getSuccessNotice() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
		
	}
	
	public CheckoutPage clickHereToLoginLink(){
        wait.until(ExpectedConditions.elementToBeClickable(clickHereToLoginLink)).click();
        return this;
    }

	public String getLoginError() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(LoinError)).getText();
	}
	public CheckoutPage clickShowLoginBtn() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("showlogin"))).click();
		return this;

	}
	
	public CheckoutPage waitForLoginBtnToDisappear() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loginBtn));
		return this;
	}
	
	

	public CheckoutPage login(User user){
        return enterUserName(user.getUsername()).
                enterPassword(user.getPassword()).
                clickLogin().waitForLoginBtnToDisappear();
    }
	
	public CheckoutPage selectDirectBankTransfer() {
		waitForOverLaysToDisappear(overLay);
		WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
		if (e.isSelected()) {
			e.click();
		}
		return this;
	}
	
	public CheckoutPage enterUserName(String userName) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(userNameFld)).sendKeys(userName);
		return this;
	}

	public CheckoutPage enterPassword(String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFld)).sendKeys(password);
		return this;
	}

	public CheckoutPage clickLogin() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn)).click();
		return this;
	}

	public CheckoutPage login(String username, String password) {
		return enterUserName(username).enterPassword(password).clickLogin().waitForLoginBtnToDisappear();
		
	}
	


	 public CheckoutPage selectCashOnDeliveryTransfer(){
	        wait.until(ExpectedConditions.elementToBeClickable(cashOnDeliveryTransferRadioBtn)).click();
	        return this;
	    } 
	 
	 public String getProductName() throws Exception {
	        int i = 5;
	        while(i > 0){
	            try {
	                return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
	            }catch (StaleElementReferenceException e){
	                System.out.println("NOT FOUND. TRYING AGAIN" + e);
	            }
	            Thread.sleep(5000);
	            i--;
	        }
	        throw new Exception("Element not found");
	    }
}
