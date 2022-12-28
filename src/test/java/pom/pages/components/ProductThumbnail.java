package pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pom.base.BasePage;
import pom.pages.CartPage;
import pom.pages.HomePage;

public class ProductThumbnail extends BasePage {
	public ProductThumbnail(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private final By viewCartLink = By.cssSelector("a[title='View cart']");
	
	 private By getAddToCartBtnElement(String productName) {
		  return
			  //By.cssSelector("a[aria-label='Add �"+ productName +"� to your cart']");
			//a[aria-label='Add �Blue Shoes� to your cart']�Blue Shoes� “Blue Shoes”
				  By.cssSelector("a[aria-label='Add “"+ productName +"” to your cart']"); 
		  }


	public ProductThumbnail clickAddToCartBtn(String productName) {

		By addToCartBtn = getAddToCartBtnElement(productName);
		wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartBtn)).click();
		return this;
	}

	public CartPage clickViewCart() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartLink)).click();
		return new CartPage(driver);

}

}
