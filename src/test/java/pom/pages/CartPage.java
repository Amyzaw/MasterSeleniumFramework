package pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pom.base.BasePage;

public class CartPage extends BasePage{

	@FindBy(css = "td[class='product-name'] a") private WebElement productName ;
	@FindBy(css =".checkout-button") @CacheLookup private WebElement checkoutBtn;

	public CartPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,this);
	}


	public String getProductName() {
		return wait.until(ExpectedConditions.visibilityOf(productName)).getText();
		
	}

	public CheckoutPage clickCheckoutBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
		return new CheckoutPage(driver);
	}
}
