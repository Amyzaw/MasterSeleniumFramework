package pom.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pom.base.BasePage;
import pom.objects.Product;
import pom.pages.components.ProductThumbnail;

public class StorePage extends BasePage{

	private final By searchFld = By.id("woocommerce-product-search-field-0");
	private final By searchBtn = By.cssSelector("button[value='Search']");
	private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");
	private ProductThumbnail productThumbnail;
	private final By infoTxt = By.cssSelector(".woocommerce-info");


	public ProductThumbnail getProductThumbnail() {
		return productThumbnail;
	}

	public StorePage(WebDriver driver) {

		super(driver);
		productThumbnail = new ProductThumbnail(driver);
	}
	
	public StorePage load() {
		load("/store");
		return this;
	}

	//Structural Page Object
	private StorePage enterTextInSearchFld(String txt) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchFld)).sendKeys(txt);
		return this;
	}

	private StorePage clickSearchBtn() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchBtn)).click();
		return this;
	}

	//Functional Page Object
	public StorePage search(String txt) {
		enterTextInSearchFld(txt).clickSearchBtn();
		return this;
	}

	public String getTitle() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
	}

	//Handle UI Element dynamically
	
    public ProductPage searchExactMatch(String txt){
        enterTextInSearchFld(txt).clickSearchBtn();
        return new ProductPage(driver);
    }

    public ProductPage navigateToTheProduct(Integer id) throws IOException {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[normalize-space()='"+ new Product(id).getName() + "']"))).click();
        return new ProductPage(driver);
}
    public String getInfo(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(infoTxt)).getText();
    }
}
